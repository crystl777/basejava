package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.model.type.ContactType;
import com.urise.webapp.model.type.SectionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void addContactsResume(Resume resume) {
        resume.getContacts().put(ContactType.PHONE_NUMBER, "+7-999-000-11-22");
        resume.getContacts().put(ContactType.SKYPE, "zzz");
        resume.getContacts().put(ContactType.E_MAIL, "zzz@zzz.com");
        resume.getContacts().put(ContactType.LINKEDIN, "linkedin.com/zzz");
        resume.getContacts().put(ContactType.GITHUB, "github.com/zzz");
        resume.getContacts().put(ContactType.STACKOVERFLOW, "stackoverflow.com/zzz");
        resume.getContacts().put(ContactType.HOME_PAGE, "zzz.ru");

    }

    public static void addSectionsResume(Resume resume) {
        StringSection personalStringSection = new StringSection("personal text");
        StringSection objectiveStringSection = new StringSection("objective text");


        List<String> achievements = new ArrayList<>();
        achievements.add("the first achievement");
        achievements.add("the second achievement");
        achievements.add("the third achievement");
        ListSection achievementListSection = new ListSection(achievements);


        List<String> qualifications = new ArrayList<>();
        qualifications.add("the first qualification");
        qualifications.add("the second qualification");
        qualifications.add("the third qualification");
        ListSection qualificationListSection = new ListSection(qualifications);

        List<Organization.Position> listPositionExperience = new ArrayList<>();
        listPositionExperience.add(
                new Organization.Position(LocalDate.of(1987, 9, 1),
                        LocalDate.of(1993, 7, 1),
                        "title experience", "description experience"));

        List<Organization.Position> listPositionEducation = new ArrayList<>();
        listPositionEducation.add(
                new Organization.Position(LocalDate.of(2009, 12, 2),
                        LocalDate.of(2014, 5, 5),
                        "title education", "description education"));


        Organization experience = new Organization(new Link("google","google.com"), listPositionExperience);
        Organization education = new Organization(new Link("yandex", "yandex.ru"), listPositionEducation);


        List<Organization> experienceComponents = new ArrayList<>();
        experienceComponents.add(experience);
        List<Organization> educationComponents = new ArrayList<>();
        educationComponents.add(education);

        OrganizationSection listExperience = new OrganizationSection(experienceComponents);
        OrganizationSection listEducation = new OrganizationSection(educationComponents);

        resume.getSections().put(SectionType.PERSONAL, personalStringSection);
        resume.getSections().put(SectionType.OBJECTIVE, objectiveStringSection);
        resume.getSections().put(SectionType.ACHIEVEMENT, achievementListSection);
        resume.getSections().put(SectionType.QUALIFICATIONS, qualificationListSection);

        resume.getSections().put(SectionType.EXPERIENCE, listExperience);
        resume.getSections().put(SectionType.EDUCATION, listEducation);

    }
}



