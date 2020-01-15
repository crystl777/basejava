package com.urise.webapp.storage.serializer;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.model.type.ContactType;
import com.urise.webapp.model.type.SectionType;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void writeResume(Resume resume, OutputStream os) throws IOException {

        try (DataOutputStream dos = new DataOutputStream(os)) {

            //реализация записи uuid и имени резюме
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            //реализация записи контактов через функциональный интерфейс
            Map<ContactType, String> contacts = resume.getContacts();

            writeWithException(contacts.entrySet(), dos, (entryContactType) -> {
                dos.writeUTF(entryContactType.getKey().name());
                dos.writeUTF(entryContactType.getValue());
            });


            //реализация записи секций через функциональный интерфейс
            Map<SectionType, AbstractSection> section = resume.getSections();

            writeWithException(section.entrySet(), dos, (entrySectionType) -> {
                SectionType sectionType = entrySectionType.getKey();
                dos.writeUTF(sectionType.name());

                switch (sectionType) {

                    case PERSONAL:
                    case OBJECTIVE:

                        dos.writeUTF(((StringSection) entrySectionType.getValue()).getText());
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:

                        writeWithException(((ListSection) entrySectionType.getValue()).getListComponent(), dos,
                                dos::writeUTF);
                        break;

                    case EXPERIENCE:
                    case EDUCATION:

                        writeWithException(((OrganizationSection) entrySectionType.getValue())
                                .getOrganizations(), dos, (organization) -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            writeNullElement(organization.getHomePage().getUrl(), dos);

                            writeWithException(organization.getPositions(), dos, (position) -> {
                                writeDate(position.getStartDate(), dos);
                                writeDate(position.getEndDate(), dos);
                                dos.writeUTF(position.getTitle());
                                writeNullElement(position.getDescription(), dos);
                            });
                        });
                }
            });
        }
    }


    @Override
    public Resume readResume(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {


            //реализация чтения uuid и имени резюме
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            //реализация чтения контактов через функциональный интерфейс
            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));


            //реализация чтения секций через функциональный интерфейс

            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });

            return resume;
        }
    }


    //реализация методов для избежания дублирования кода
    private void writeDate(LocalDate localDate, DataOutputStream dos) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
        dos.writeInt(localDate.getDayOfMonth());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    private void writeNullElement(String element, DataOutputStream dos) throws IOException {
        if (element == null) {
            dos.writeUTF("");
        } else dos.writeUTF(element);
    }

    private String readNullElement(DataInputStream dis) throws IOException {
        String text = dis.readUTF();
        if (text.equals("")) {
            return null;
        } else return text;
    }

    @FunctionalInterface
    private interface WritableElementOfCollection<T> {
        void write(T t) throws IOException;
    }

    @FunctionalInterface
    private interface ReadableElementOfCollection {
        void read() throws IOException;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dataOutputStream,
                                        WritableElementOfCollection<T> writable) throws IOException {

        dataOutputStream.writeInt(collection.size());
        for (T element : collection) {
            writable.write(element);
        }
    }

    private void readWithException(DataInputStream dataInputStream,
                                   ReadableElementOfCollection readable) throws IOException {
        int size = dataInputStream.readInt();
        for (int i = 0; i < size; i++) {
            readable.read();
        }
    }

    @FunctionalInterface
    private interface ReadableList<T> {
        T read() throws IOException;
    }


    private <T> List<T> readList(DataInputStream dataInputStream,
                                 ReadableList<T> readable) throws IOException {

        int sizeList = dataInputStream.readInt();
        List<T> list = new ArrayList<>(sizeList);

        for (int i = 0; i < sizeList; i++) {
            list.add(readable.read());
        }
        return list;
    }


    private <T> AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new StringSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationSection(readList(dis, () ->
                        new Organization(new Link(dis.readUTF(), dis.readUTF()), readList(dis, () ->
                                new Organization.Position(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF())))));
            default:
                throw new StorageException("Error of reading " + sectionType.name(), sectionType.name());
        }
    }
}
