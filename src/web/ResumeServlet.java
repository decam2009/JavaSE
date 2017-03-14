package web;

import model.ContactType;
import model.Resume;
import storage.Storage;
import util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BORIS on 09.03.17.
 */
public class ResumeServlet extends HttpServlet
  {

    private Storage storage;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

	@Override
	public void init(ServletConfig config) throws ServletException
	  {
	    super.init(config);
	    storage = Config.getInstance().getStorage();
	  }

	protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

	  request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      response.setHeader("Content-type",  "text/html; charset=UTF-8");
      String parameterValue = request.getParameter("p");
	  if (parameterValue == null || !parameterValue.equals("getAll"))
	    {
		  response.getWriter().print("No resumes or wrong parameter. Parameter must be getAll");
		}
	  else if (parameterValue.equals("getAll"))
	    {
	      response.getWriter().print("<html>");
		  response.getWriter().print("<head>");
		  response.getWriter().print("<title>Таблица резюме</title>");
		  response.getWriter().print("</head>");
		  response.getWriter().print("<body>");
		  response.getWriter().print("<table border = 2>");
		  response.getWriter().print("<tr>");
		  response.getWriter().print("<td>UUID_ID</td>");
		  response.getWriter().print("<td>FULL_NAME</td>");
		  response.getWriter().print("<td>MOBILE</td>");
		  response.getWriter().print("<td>MAIL</td>");
		  response.getWriter().print("<td>VK</td>");
		  response.getWriter().print("<td>HOME_PAGE</td>");
		  response.getWriter().print("<td></td>");
		  response.getWriter().print("</tr>");
		  for (Resume r: storage.getAllSorted())
		    {
			  response.getWriter().print("<tr>");
			  response.getWriter().print("<td><a href = resume?uuid=" + r.getUuid() + ">" + r.getUuid() + "</a></td>");
			  response.getWriter().print("<td>" + r.getFullName() + "</td>");
			  response.getWriter().print("<td>" + r.getContact(ContactType.MOBILE) + "</td>");
			  response.getWriter().print("<td>" + r.getContact(ContactType.MAIL) + "</td>");
			  response.getWriter().print("<td><a href = " + r.getContact(ContactType.VK) + ">" + r.getContact(ContactType.VK) + "</a></td>");
			  response.getWriter().print("<td>" + r.getContact(ContactType.HOME_PAGE) + "</td>");
			  response.getWriter().print("</tr>");
		    }
		  response.getWriter().print("<table>");
		  response.getWriter().print("</table>");
		  response.getWriter().print("</body>");
		  response.getWriter().print("</html>");
		}
	}
  }
