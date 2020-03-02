package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.model.type.ContactType;
import com.urise.webapp.model.type.SectionType;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {


    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        Resume resume;

        if (uuid.length() != 0) {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        } else resume = new Resume(fullName);

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(type, new StringSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(type, new ListSection(value.split("\\n")));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        String[] values = request.getParameterValues(type.name());
                        if (values.length >= 2 || values[0].trim().length() != 0) {
                            List<Organization> organizations = new ArrayList<>();
                            String[] orgUrls = request.getParameterValues(type.name() + "url");
                            for (int i = 0; i < values.length; i++) {
                                String orgName = values[i];
                                if (orgName.trim().length() != 0) {
                                    List<Organization.Position> positions = new ArrayList<>();
                                    Link link = new Link(orgName, orgUrls[i]);
                                    String[] startDates = request.getParameterValues(type.name() + "startDate" + i);
                                    String[] endDates = request.getParameterValues(type.name() + "endDate" + i);
                                    String[] titles = request.getParameterValues(type.name() + "title" + i);
                                    String[] descriptions = request.getParameterValues(type.name() + "description" + i);
                                    for (int j = 0; j < titles.length; j++) {
                                        if (titles[j].trim().length() != 0) {
                                            positions.add(new Organization.Position(DateUtil.dateFormatter(startDates[j]),
                                                    DateUtil.dateFormatter(endDates[j]), titles[j], descriptions[j]));
                                        }
                                    }
                                    organizations.add(new Organization(link, positions));
                                }
                            }
                            resume.addSection(type, new OrganizationSection(organizations));
                        } else {
                            resume.getSections().remove(type);
                        }
                        break;
                }
            } else {
                resume.getSections().remove(type);
            }
        }

        if (uuid.length() == 0) {
            storage.save(resume);
        } else storage.update(resume);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                resume = storage.get(uuid);
                break;
            case "edit":
                resume = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = resume.getSection(type);
                    switch (type) {
                        case PERSONAL:
                        case OBJECTIVE:
                            if (section == null) {
                                section = new StringSection("");
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = new ListSection("");
                            }
                            break;
                        case EDUCATION:
                        case EXPERIENCE:
                            if (section == null) {
                                section = new OrganizationSection(new Organization("", "", new Organization.Position()));
                            } else {
                                for (Organization organization : ((OrganizationSection) section).getOrganizations()) {
                                    organization.getPositions().add(new Organization.Position());
                                }
                                ((OrganizationSection) section).getOrganizations().add(new Organization("", "", new Organization.Position()));
                            }
                            break;
                    }
                    resume.addSection(type, section);
                }
                break;
            case "add":
                resume = new Resume();
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = resume.getSection(type);
                    switch (type) {
                        case PERSONAL:
                        case OBJECTIVE:
                            section = new StringSection("");
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            section = new ListSection("");
                            break;
                        case EDUCATION:
                        case EXPERIENCE:
                            section = new OrganizationSection(new Organization("", "", new Organization.Position()));
                            break;
                    }
                    resume.addSection(type, section);
                }
                break;

            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}


