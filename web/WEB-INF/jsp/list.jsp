<%@ page import="com.urise.webapp.model.type.ContactType" %>
<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2 align="center">Добавить резюме<br/>
    <a href="resume?action=add"><img src="img/add.png" width="30" height="30" alt="add"></a><br/><br/></h2>
    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th>Удалить</th>
            <th>Изменить</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
            <tr>
                <td align="center"><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td align="center"><%=ContactType.E_MAIL.toHtml(resume.getContact(ContactType.E_MAIL))%></td>
                <td align="center"><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png" width="30" height="30" alt="delete"></a></td>
                <td align="center"><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png" width="30" height="30" alt="edit"></a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>