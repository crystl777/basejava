package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.model.type.ContactType;
import com.urise.webapp.model.type.SectionType;

import java.time.LocalDate;
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


       Organization experience = new Organization(new Link("google", "google.com"), listPositionExperience);
       Organization education = new Organization(new Link("yandex", "yandex.ru"), listPositionEducation);


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
