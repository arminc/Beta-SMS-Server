package nl.coralic.beta.sms.server.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import nl.coralic.beta.sms.server.dataobject.Comment;
import nl.coralic.beta.sms.server.dataobject.Issues;
import nl.coralic.beta.sms.server.db.PMF;
import nl.coralic.beta.sms.server.utils.Utils;

import com.google.appengine.api.datastore.Text;


@SuppressWarnings("serial")
public class AddIssueServlet extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		doGet(req, resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		/*
		 * We only accept four param's: HASH = identify our application (99dae5fed1878dea1ee46a1bb0f8f149) DATA = users feature request DEBUG = logcat
		 * text MAIL = users mail
		 */

		resp.setContentType("text/plain");
		System.out.println("Add issue");
		// Get param's and check if they are valid
		String HASH = req.getParameter("HASH");
		String DATA = req.getParameter("DATA");
		String DEBUG = req.getParameter("DEBUG");
		String MAIL = req.getParameter("MAIL");
		String TYPE = req.getParameter("TYPE");
		if (HASH == null || !HASH.equalsIgnoreCase("99dae5fed1878dea1ee46a1bb0f8f149") || DATA == null)
		{
				resp.getWriter().println("ERROR");
		}

		// Save data to db
		Date date = new Date();
		ArrayList<Comment> tmp = new ArrayList<Comment>();
		
		System.out.println("Create object");
		if (DEBUG == null)
		{
			DEBUG = "";
		}
		Issues issue = new Issues(DATA, date, tmp, "Unknown", new Text(DEBUG), MAIL, TYPE);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try
		{
			System.out.println("Persist object");
			issue = pm.makePersistent(issue);
			Utils utils = new Utils();
			if (issue.getEmail() != null && !issue.getEmail().equalsIgnoreCase(""))
			{
				System.out.println("Try to send mail");
				utils.sendMail(String.valueOf(issue.getKey().getId()), issue.getEmail());
			}
		}
		finally
		{
			System.out.println("Close pm");
			pm.close();
		}
		resp.getWriter().println(issue.getKey().getId());
		return;
	}
}
