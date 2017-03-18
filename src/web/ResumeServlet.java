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
      request.setCharacterEncoding("UTF-8");
      String uuid = request.getParameter("uuid");
      String fullName = request.getParameter("fullName");
      Resume r = storage.get(uuid);
      r.setFullName(fullName);
      for (ContactType type: ContactType.values())
	    {
	      String value = request.getParameter(type.name());
	      if (value!=null && value.trim().length()!=0)
		    {
		      r.addContact(type, value);
			}
		  else
		    {
		      r.getContacts().remove(type);
			}
		}
	  storage.update(r,r);
      response.sendRedirect("resume");
    }

	@Override
	public void init(ServletConfig config) throws ServletException
	  {
	    super.init(config);
	    storage = Config.getInstance().getStorage();
	  }

	protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException
	{
	  String uuid = request.getParameter("uuid");
	  String action = request.getParameter("action");
	  if (action==null)
	    {
		  request.setAttribute("resumes", storage.getAllSorted());
		  request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	      return;
	    }
	  Resume r;
	  switch (action)
	    {
		  case "delete":
		    storage.delete(uuid);
		    response.sendRedirect("resume");
		    return;
		  case "view":
		  case "edit":
		    r = storage.get(uuid);
			break;
		  default:
		    throw new IllegalStateException("Action" + action + "is illegal");
		}
		request.setAttribute("resume", r);
	    request.getRequestDispatcher("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp").forward(request, response);

	}
  }
