<%@ page import="util.DateUtil" %>
<%@ page import="model.*" %>
<%@ page import="javax.xml.soap.Text" %>
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
      <c:forEach var="type" items="<%=SectionType.values()%>">
          <c:set var="section" value="${resume.getSection(type)}"/>
          <jsp:useBean id="section" type="model.Section"/>
          <h3><a>${type.name()}</a></h3>
          <c:choose>
              <c:when test="${type=='OBJECTIVE'}">
                  <input type="text" name='${type}' size=75 value="<%=((TextSection)section).getContent()%>">
              </c:when>
              <c:when test="${type=='PERSONAL'}">
                  <textarea name='${type}' cols=75 rows=5><%=((TextSection)section).getContent()%></textarea>
              </c:when>
              <c:when test="${type == 'QUALIFICATIONS' || type == 'ACHIEVEMENT'}">
                <textarea name='${type}' cols=75 rows=5><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
              </c:when>
              <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                  <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>" varStatus="counter">
                      <dl>
                          <dt>Наименование учреждения:</dt>
                          <dd><input type="text" name="${type}" size="100" value="${org.homePage.name}"></dd>
                      </dl>
                      <dl>
                          <dt>Сайт учреждения:</dt>
                          <dd><input type="text" name="${type}url" size="100" value="${org.homePage.url}"></dd>
                      </dl>
                      <br>
                      <div style="margin-left: 30px">
                          <c:forEach var="pos" items="${org.positions}">
                              <jsp:useBean id="pos" type="model.Organization.Position"/>
                              <dl>
                                  <dt>Начальная дата:</dt>
                                  <dd>
                                      <input type="text" name="${type}${counter.index}startDate" size="10" value="<%=DateUtil.format(pos.getStartDate())%>" placeholder="MM/yyyy">
                                  </dd>
                              </dl>
                              <dl>
                                  <dt>Конечная дата:</dt>
                                  <dd>
                                      <input type="text" name="${type}${counter.index}endDate" size="10" value="<%=DateUtil.format(pos.getEndDate())%>" placeholder="MM/yyyy">
                                  </dd>
                              </dl>
                              <dl>
                                  <dt>Должность:</dt>
                                  <dd><input type="text" name="${type}${counter.index}title" size="75" value="${pos.title}"></dd>
                              </dl>
                              <dl>
                                  <dt>Описание:</dt>
                                  <dd><textarea type="text" name="${type}${counter.index}description" size="75" value="${pos.description}"></textarea></dd>
                              </dl>
                          </c:forEach>
                      </div>
                  </c:forEach>
              </c:when>

          </c:choose>
      </c:forEach>
      <button type="submit">Сохранить</button>
      <button onclick="window.history.back()">Отменить</button>
  </form>
</section>
</body>
</html>
