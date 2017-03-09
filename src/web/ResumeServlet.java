package web;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

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
		  response.getWriter().print("<td>CONTACT_TYPE</td>");
		  response.getWriter().print("<td>CONTACT_VALUE</td>");
		  response.getWriter().print("</tr>");
		  response.getWriter().print("<tr>");
		  response.getWriter().print("<td>1</td>");
		  response.getWriter().print("<td>A</td>");
		  response.getWriter().print("<td>CT</td>");
		  response.getWriter().print("<td>CV</td>");
		  response.getWriter().print("</tr>");
		  response.getWriter().print("<table>");
		  response.getWriter().print("</table>");
		  response.getWriter().print("</body>");
		  response.getWriter().print("</html>");
		}
	}
  }
