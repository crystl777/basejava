<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.StringSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png" width="30"
                                                                                      height="30" alt="edit"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.type.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <br/>
    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.urise.webapp.model.type.SectionType, com.urise.webapp.model.AbstractSection>"/>
        <c:set var="type" value="${sectionEntry.key}"/>
        <c:set var="section" value="${sectionEntry.value}"/>
        <jsp:useBean id="section"
                     type="com.urise.webapp.model.AbstractSection"/>
        <b><c:out value="${type.title}"/></b><br/>
        <c:choose>
            <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
                <%=((StringSection)section).getText()%><br/><br/>
            </c:when>
            <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                <c:forEach var="element" items="<%=((ListSection)section).getListComponent()%>">
                    ${element}<br/>
                </c:forEach>
                <br/><br/>
            </c:when>
            <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                <c:forEach var="organization" items="<%=((OrganizationSection)section).getOrganizations()%>">
                    <jsp:useBean id="organization"
                                 type="com.urise.webapp.model.Organization"/>
                    <a href="${organization.homePage.url}">${organization.homePage.name}</a><br/>
                    <c:forEach var="position" items="<%=organization.getPositions()%>">
                        Период:<br/>
                        Дата начала: ${position.startDate}<br/>
                        Дата окончания: ${position.endDate}<br/>
                        Должность: ${position.title}<br/>
                        Описание: ${position.description}<br/><br/>
                    </c:forEach>
                </c:forEach>
                <br/><br/>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

