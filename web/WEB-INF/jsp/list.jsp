<%@ page import="model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<section>
    <table border="1" cellpadding="8" cellspacing="8">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="model.Resume"/>
            <tr>
              <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
              <td><%=ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))%></td>
              <td><a href="resume?uuid=${resume.uuid}&action=delete">Delete</a></td>
              <td><a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
