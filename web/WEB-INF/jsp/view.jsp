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
    <h1>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png" width="30" height="30" alt="edit"></a></h1>
    <hr>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.type.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br>
        </c:forEach>
    </p>
    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.urise.webapp.model.type.SectionType, com.urise.webapp.model.AbstractSection>"/>
        <c:set var="type" value="${sectionEntry.key}"/>
        <c:set var="section" value="${sectionEntry.value}"/>
        <jsp:useBean id="section"
                     type="com.urise.webapp.model.AbstractSection"/>
        <hr>
        <h2><c:out value="${type.title}"/></h2>
        <c:choose>
            <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
                <br><%=((StringSection)section).getText()%>
            </c:when>
            <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                <c:forEach var="element" items="<%=((ListSection)section).getListComponent()%>">
                    <br>- ${element}
                </c:forEach>
            </c:when>
            <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                <c:forEach var="organization" items="<%=((OrganizationSection)section).getOrganizations()%>">
                    <jsp:useBean id="organization"
                                 type="com.urise.webapp.model.Organization"/>
                    <br><h3>${organization.homePage.name}</h3>
                    <a href="${organization.homePage.url}">Сайт</a>
                    <br><p>Периоды работы:</p>
                    <c:forEach var="position" items="<%=organization.getPositions()%>">
                        <b>Дата начала:</b> ${position.startDate}<br>
                        <b>Дата окончания:</b> ${position.endDate}<br>
                        <b>Должность:</b> ${position.title}<br>
                        <b>Описание:</b> ${position.description}<br>
                    </c:forEach>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
