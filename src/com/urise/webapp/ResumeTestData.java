package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.model.type.ContactType;
import com.urise.webapp.model.type.SectionType;

import java.time.YearMonth;

public class ResumeTestData {

    public static void main(String[] args) {

        Resume resume = new Resume("uuid", "James Smith");

        StringSection personalStringSection = new StringSection("personal text");
        StringSection objectiveStringSection = new StringSection("objective text");

        ListSection achievementListSection = new ListSection();
        achievementListSection.getListComponent().add("the first achievement");
        achievementListSection.getListComponent().add("the second achievement");
        achievementListSection.getListComponent().add("the third achievement");

        ListSection qualificationListSection = new ListSection();
        qualificationListSection.getListComponent().add("the first qualification");
        qualificationListSection.getListComponent().add("the second qualification");
        qualificationListSection.getListComponent().add("the third qualification");

        InfoSection experience = new InfoSection("experience",
                YearMonth.of(2013, 9), YearMonth.of(2016, 10),
                "info text");

        InfoSection education = new InfoSection("education",
                YearMonth.of(2016, 10), YearMonth.of(2019, 11),
                "info text 2");

        ListInfoSection listExperience = new ListInfoSection();
        ListInfoSection listEducation = new ListInfoSection();
        listExperience.getInfoComponent().add(experience);
        listEducation.getInfoComponent().add(education);


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
