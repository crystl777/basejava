package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.model.type.ContactType;
import com.urise.webapp.model.type.SectionType;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {

        Resume resume = new Resume("uuid", "James Smith");

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


        OrganizationDate dateExperience1 = new OrganizationDate(2016, 9, 2017, 12);
        OrganizationDate dateExperience2 = new OrganizationDate(2018, 3, 2019, 10);
        List<OrganizationDate> listDateExperience = new ArrayList<>();
        listDateExperience.add(dateExperience1);
        listDateExperience.add(dateExperience2);

        OrganizationDate dateEducation1 = new OrganizationDate(1998, 9, 2001, 6);
        List<OrganizationDate> listDateEducation = new ArrayList<>();
        listDateEducation.add(dateEducation1);

        Organization experience = new Organization("google", "google.com", listDateExperience,
                "experience", "info text");

        Organization education = new Organization("yandex", "yandex.ru", listDateEducation,
                "education", "info text 2");


        List<Organization> experienceComponents = new ArrayList<>();
        experienceComponents.add(experience);
        List<Organization> educationComponents = new ArrayList<>();
        educationComponents.add(education);

        OrganizationSection listExperience = new OrganizationSection(experienceComponents);
        OrganizationSection listEducation = new OrganizationSection(educationComponents);

        resume.getContacts().put(ContactType.PHONE_NUMBER, "+7-999-000-11-22");
        resume.getContacts().put(ContactType.SKYPE, "zzz");
        resume.getContacts().put(ContactType.E_MAIL, "zzz@zzz.com");
        resume.getContacts().put(ContactType.LINKEDIN, "linkedin.com/zzz");
        resume.getContacts().put(ContactType.GITHUB, "github.com/zzz");
        resume.getContacts().put(ContactType.STACKOVERFLOW, "stackoverflow.com/zzz");
        resume.getContacts().put(ContactType.HOME_PAGE, "zzz.ru");

        resume.getSections().put(SectionType.PERSONAL, personalStringSection);
        resume.getSections().put(SectionType.OBJECTIVE, objectiveStringSection);
        resume.getSections().put(SectionType.ACHIEVEMENT, achievementListSection);
        resume.getSections().put(SectionType.QUALIFICATIONS, qualificationListSection);
        resume.getSections().put(SectionType.EXPERIENCE, listExperience);
        resume.getSections().put(SectionType.EDUCATION, listEducation);


        System.out.println(resume.getUuid());
        System.out.println(resume.getFullName());

        System.out.println(ContactType.PHONE_NUMBER.getTitle() + " "
                + resume.getContacts().get(ContactType.PHONE_NUMBER));

        System.out.println(ContactType.SKYPE.getTitle() + " "
                + resume.getContacts().get(ContactType.SKYPE));

        System.out.println(ContactType.E_MAIL.getTitle() + " "
                + resume.getContacts().get(ContactType.E_MAIL));

        System.out.println(ContactType.LINKEDIN + " "
                + resume.getContacts().get(ContactType.LINKEDIN));

        System.out.println(ContactType.GITHUB + " "
                + resume.getContacts().get(ContactType.GITHUB));

        System.out.println(ContactType.STACKOVERFLOW + " "
                + resume.getContacts().get(ContactType.STACKOVERFLOW));

        System.out.println(ContactType.HOME_PAGE + " " +
                resume.getContacts().get(ContactType.HOME_PAGE));

        System.out.println("---------------------------------------------");

        System.out.println(SectionType.PERSONAL.getTitle() + " "
                + resume.getSections().get(SectionType.PERSONAL));

        System.out.println(SectionType.OBJECTIVE.getTitle() + " "
                + resume.getSections().get(SectionType.OBJECTIVE));

        System.out.println(SectionType.ACHIEVEMENT.getTitle() + " "
                + resume.getSections().get(SectionType.ACHIEVEMENT));

        System.out.println(SectionType.QUALIFICATIONS.getTitle() + " "
                + resume.getSections().get(SectionType.QUALIFICATIONS));

        System.out.println(SectionType.EXPERIENCE.getTitle() + " "
                + resume.getSections().get(SectionType.EXPERIENCE));

        System.out.println(SectionType.EDUCATION.getTitle() + " "
                + resume.getSections().get(SectionType.EDUCATION));

    }
}
