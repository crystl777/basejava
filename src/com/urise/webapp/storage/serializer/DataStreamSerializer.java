package com.urise.webapp.storage.serializer;

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

            //реализация записи имени и uuid резюме
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


            //реализация чтения резюме и контактов (дано было в уроке)
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            //конец чтения контактов (дано было в уроке)


            //реализация чтения секций (мой код)
            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());

                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new StringSection(dis.readUTF()));
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int sizeListSection = dis.readInt();
                        List<String> list = new ArrayList<>(sizeListSection);

                        for (int j = 0; j < sizeListSection; j++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                        break;

                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = new OrganizationSection();

                        int sizeListOrganization = dis.readInt();
                        List<Organization> listOrganization = new ArrayList<>(sizeListOrganization);

                        for (int j = 0; j < sizeListOrganization; j++) {
                            Organization organization = new Organization();
                            organization.setHomePage(new Link(dis.readUTF(), readNullElement(dis)));

                            int sizePosition = dis.readInt();
                            List<Organization.Position> listPosition = new ArrayList<>(sizePosition);

                            for (int k = 0; k < sizePosition; k++) {
                                Organization.Position organizationPosition = new Organization.Position(readDate(dis),
                                        readDate(dis), dis.readUTF(), readNullElement(dis));
                                listPosition.add(organizationPosition);
                            }
                            organization.setPositions(listPosition);
                            listOrganization.add(organization);
                        }
                        organizationSection.setOrganizations(listOrganization);
                        resume.addSection(sectionType, organizationSection);
                        break;
                }
            }
            //конец реализации чтения секций (мой код)
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

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dataOutputStream,
                                        WritableElementOfCollection<T> writable) throws IOException {

        dataOutputStream.writeInt(collection.size());
        for (T element : collection) {
            writable.write(element);
        }
    }
}
