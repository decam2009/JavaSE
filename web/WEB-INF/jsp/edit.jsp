<%@ page import="model.ContactType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="model.Resume" scope="request"/>
    <title>Резюме ${resume.uuid}</title>
</head>
<body>
<section>
  <form method="post" action="resume" encrypte="application/x-www-form-urlencoded">
      <input type="hidden" name="uuid" value="${resume.uuid}">
      <dl>
          <dt>Имя: </dt>
          <dd><input type="text" name="fullName" size="50" value="${resume.fullName}"></dd>
      </dl>
      <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values() %>">
          <dl>${type.title}</dl>
          <dd><input type="text" name="${type.name()}" size="50" value="${resume.getContact(type)}"></dd>
        </c:forEach>
      <hr>
      <button type="submit">Сохранить</button>
      <button onclick="window.history.back()">Отменить</button>
  </form>
</section>
</body>
</html>
