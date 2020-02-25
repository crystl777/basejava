package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {


    private Storage storage = Config.get().getStorage();

    @Override
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/html; charset = UTF-8");

        resp.getWriter().println("" +
                "<html>" +
                   "<head>" +
                      "<title> РЕЗЮМЕ </title>" +
                   "</head" +
                   "<body>" +
                      "<table border=\"2\">" +
                         "<tr>" +
                            "<th> UUID </th>" +
                            "<th> FULL NAME </th>" +
                             printResumes(storage) +
                      "</table>" +
                   "</body>" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private String printResumes(Storage storage) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Resume resume : storage.getAllSorted()){
            stringBuilder.append("<tr>").append("<td>")
                                               .append(resume.getUuid())
                                        .append("</td>")
                                        .append("<td>")
                                              .append(resume.getFullName()).append("</td>")
                        .append("</tr>");
        }
        return stringBuilder.toString();
    }
}


