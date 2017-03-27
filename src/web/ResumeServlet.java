package web;

import model.*;
import storage.Storage;
import util.Config;
import util.DateUtil;
import util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
      final boolean isCreate = (uuid == null || uuid.length()==0);
	  Resume r;
	  if (isCreate)
	    {
	       r = new Resume (fullName);
		}
	  else
	    {
		  r = storage.get(uuid);
		  r.setFullName(fullName);
		}
      for (ContactType type: ContactType.values())
	    {
	      String value = request.getParameter(type.name());
	      if (value!=null && value.trim().length()!=0)
		    {
		      r.setContact(type, value);
			}
		  else
		    {
		      r.getContacts().remove(type);
			}
		}
		for (SectionType type: SectionType.values())
		  {
		    String value = request.getParameter(type.name());
		    String[] values = request.getParameterValues(type.name());
		    if (HtmlUtil.isEmpty(value) && values.length < 2)
			 {
			   r.getSections().remove(type);
			 }
			 else
			 {
			   switch (type)
			     {
				   case OBJECTIVE:
				   case PERSONAL:
				     r.setSection(type, new TextSection(value));
				     break;
				   case ACHIEVEMENT:
				   case QUALIFICATIONS:
				     r.setSection(type, new ListSection(value.split("\\n")));
				     break;
				   case EDUCATION:
				   case EXPERIENCE:
				   List<Organization> orgs = new ArrayList<>();
				   String[] urls = request.getParameterValues(type.name() + "url");
				   for (int i=0; i<values.length; i++)
				     {
				       List<Organization.Position> positions = new ArrayList<>();
				       String name = values[i];
				       if (!HtmlUtil.isEmpty(name))
					     {
					       String pfx  = type.name() + i;
					       String[] startDates = request.getParameterValues(pfx + "startDate");
						   String[] endDates = request.getParameterValues(pfx + "endDate");
						   String[] titles = request.getParameterValues(pfx + "title");
						   String[] descriptions = request.getParameterValues(pfx + "description");
						   for (int j = 0; j < titles.length; j ++)
						     {
						       if (!HtmlUtil.isEmpty(titles[j]))
							     {
							       positions.add(new Organization.Position(DateUtil.parce(startDates[j]), DateUtil.parce(endDates[j]), titles[j], descriptions[j]));
								 }
							 }
						   orgs.add(new Organization(new Link(name, urls[i]), positions));
						 }
					 }
					r.setSection(type, new OrganizationSection(orgs));
				   break;
				 }
			 }
		  }
	  if (isCreate)
	    {
		  storage.save(r);
	    }
	  else
	    {
		  storage.update(r,r);
	    }
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
		      r = storage.get(uuid);
		      break;
		  case "add":
		      r = Resume.EMPTY;
		      break;
		  case "edit":
		    r = storage.get(uuid);
		    for (SectionType type: new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION})
		 	  {
				OrganizationSection section = (OrganizationSection) r.getSection(type);
				List<Organization> emptyFirstOrganization = new ArrayList<>();
				emptyFirstOrganization.add(Organization.EMPTY);
				if (section != null)
				  {
				    for (Organization org: section.getOrganizations())
					  {
					    List<Organization.Position> emptyFirstPosition = new ArrayList<>();
					    emptyFirstPosition.add(Organization.Position.EMPTY);
					    emptyFirstPosition.addAll(org.getPositions());
					    emptyFirstOrganization.add (new Organization(org.getHomePage(), emptyFirstPosition));
					  }
				  }
				 r.setSection(type, new OrganizationSection(emptyFirstOrganization));
			  }
			break;
		  default:
		    throw new IllegalStateException("Action" + action + "is illegal");
		}
		request.setAttribute("resume", r);
	    request.getRequestDispatcher("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp").forward(request, response);

	}
  }
