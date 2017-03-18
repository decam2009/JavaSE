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
<h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></h2>
    <p>
      <c:forEach var="contactEntry" items="${resume.contacts}">
          <jsp:useBean id="contactEntry" type="java.util.Map.Entry<model.ContactType, java.lang.String>"/>
          <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br>
       </c:forEach>
    </p>
</section>
</body>
</html>
