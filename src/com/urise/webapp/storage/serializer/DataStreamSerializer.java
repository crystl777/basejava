package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;
import com.urise.webapp.model.type.ContactType;
import com.urise.webapp.model.type.SectionType;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void writeResume(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            // TODO implements sections


            Map<SectionType, AbstractSection> section = resume.getSections();
            dos.writeInt(section.size());

            for (Map.Entry<SectionType, AbstractSection> entry : section.entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.getTitle());

                if (sectionType.equals(SectionType.PERSONAL) || sectionType.equals(SectionType.OBJECTIVE)) {
                    dos.writeUTF(entry.getValue().toString());

                } else if (sectionType.equals(SectionType.ACHIEVEMENT)
                        || sectionType.equals(SectionType.QUALIFICATIONS)) {

                    ListSection listSection = (ListSection) entry.getValue();
                    dos.writeInt(listSection.getListComponent().size());
                    for (String s : listSection.getListComponent()) {
                        dos.writeUTF(s);
                    }
                } else if (sectionType.equals(SectionType.EXPERIENCE) || sectionType.equals(SectionType.EDUCATION)) {

                    OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                    dos.writeInt(organizationSection.getOrganizations().size());
                    for (Organization o : organizationSection.getOrganizations()) {
                        dos.writeUTF(o.getHomePage().getName());
                        dos.writeUTF(o.getHomePage().getUrl());

                        dos.writeInt(o.getPositions().size());
                        for (Organization.Position op : o.getPositions()) {
                            dos.writeInt(op.getStartDate().getYear());
                            dos.writeInt(op.getStartDate().getMonth().getValue());
                            dos.writeInt(op.getStartDate().getDayOfMonth());

                            dos.writeInt(op.getEndDate().getYear());
                            dos.writeInt(op.getEndDate().getMonth().getValue());
                            dos.writeInt(op.getEndDate().getDayOfMonth());

                            dos.writeUTF(op.getTitle());
                            dos.writeUTF(op.getDescription());
                        }
                    }
                }
            }
        }
    }


    @Override
    public Resume readResume(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int sizeContacts = dis.readInt();

            for (int i = 0; i < sizeContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }


            int sizeSections = dis.readInt();

            for (int i = 0; i < sizeSections; i++) {

                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                if (sectionType.equals(SectionType.PERSONAL) || sectionType.equals(SectionType.OBJECTIVE)) {
                    resume.addSection(sectionType, new StringSection(dis.readUTF()));

                } else if (sectionType.equals(SectionType.ACHIEVEMENT)
                        || sectionType.equals(SectionType.QUALIFICATIONS)) {

                    ListSection listSection = new ListSection();
                    int sizeListSection = dis.readInt();
                    for (int j = 0; j < sizeListSection; j++) {
                        listSection.getListComponent().add(dis.readUTF());
                    }
                    resume.addSection(sectionType, listSection);

                } else if (sectionType.equals(SectionType.EXPERIENCE) || sectionType.equals(SectionType.EDUCATION)) {

                    OrganizationSection organizationSection = new OrganizationSection();
                    List<Organization> listOrganization = new ArrayList<>();
                    int sizeOrganizationSection = dis.readInt();

                    for (int j = 0; j < sizeOrganizationSection; j++) {
                        Organization organization = new Organization();
                        organization.setHomePage(new Link(dis.readUTF(), dis.readUTF()));
                        List<Organization.Position> listPosition = new ArrayList<>();

                        int sizePosition = dis.readInt();
                        for (int k = 0; k < sizePosition; k++) {
                            Organization.Position organizationPosition = new Organization.Position();
                            organizationPosition
                                    .setDateStart(LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt()));
                            organizationPosition
                                    .setDateEnd(LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt()));
                            organizationPosition
                                    .setTitle(dis.readUTF());
                            organizationPosition
                                    .setDescription(dis.readUTF());
                            listPosition.add(organizationPosition);
                        }
                        organization.setPositions(listPosition);
                        listOrganization.add(organization);
                        organizationSection.setOrganizations(listOrganization);
                        resume.addSection(sectionType, organizationSection);
                    }
                }
            }
            return resume;
        }
    }
}
