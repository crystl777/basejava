<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.type.ContactType" %>
<%@ page import="com.urise.webapp.model.type.SectionType" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h1>Имя:</h1>
        <dl>
            <input type="text" name="fullName" size=50 value="${resume.fullName}">
        </dl>
        <h2>Контакты:</h2>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h2>Секции:</h2>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>
            <h3>${type.title}:</h3><br>
            <c:choose>
                <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
                    <input type="text" name="${type}" size=30 value="<%=section%>"><br><br>
                </c:when>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                    <textarea name="${type}" rows="5" cols="100"><%=String.join("\n", ((ListSection)section).getListComponent())%></textarea><br><br>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="organization" items="<%=((OrganizationSection)section).getOrganizations()%>" varStatus="count">
                        Организация:<br>
                        <input type="text" name="${type}" size="30" value="${organization.homePage.name}"/><br>
                        Домашняя страницы:<br>
                        <input type="text" name="${type}url" size="30" value="${organization.homePage.url}"/><br>

                        <c:forEach var="position" items="${organization.positions}">
                            <jsp:useBean id="position" type="com.urise.webapp.model.Organization.Position"/>
                            Дата начала:<br>
                            <input type="text" name="${type}startDate${count.index}" size="30" value="<%=DateUtil.dateToString(position.getStartDate())%>"/><br>
                            Дата окончания:<br>
                            <input type="text" name="${type}endDate${count.index}" size="30" value="<%=DateUtil.dateToString(position.getEndDate())%>"/><br><br>
                            Позиция:<br>
                            <input type="text" name="${type}title${count.index}" size="30" value="${position.title}"/><br>
                            Описание:<br>
                            <input type="text" name="${type}description${count.index}" size="30" value="${position.description}"/><br><br><br>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="button" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>