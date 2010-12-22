package nl.coralic.beta.sms.server.servlets;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.PersistenceManager;

import nl.coralic.beta.sms.client.services.addissue.AddNewIssueService;
import nl.coralic.beta.sms.server.dataobject.Comment;
import nl.coralic.beta.sms.server.dataobject.Issues;
import nl.coralic.beta.sms.server.db.PMF;
import nl.coralic.beta.sms.server.utils.Utils;

import com.google.appengine.api.datastore.Text;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AddNewIssueServiceImpl extends RemoteServiceServlet implements AddNewIssueService
{

	private static final long serialVersionUID = 1L;

	@Override
	public String addNewIssue(String email, String type, String text)
	{
		System.out.println("Add issue");
		// Get param's and check if they are valid	

		// Save data to db
		Date date = new Date();
		ArrayList<Comment> tmp = new ArrayList<Comment>();
		
		System.out.println("Create object");
		Issues issue = new Issues(text, date, tmp, "Unknown", new Text(""), email, type);

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
		return String.valueOf(issue.getKey().getId());
	}

}
