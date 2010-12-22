package nl.coralic.beta.sms.server.servlets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;

import nl.coralic.beta.sms.client.services.listissues.GetAllIssuesService;
import nl.coralic.beta.sms.server.dataobject.Comment;
import nl.coralic.beta.sms.server.dataobject.Issues;
import nl.coralic.beta.sms.server.db.PMF;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GetAllIssuesServiceImpl extends RemoteServiceServlet implements GetAllIssuesService
{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<nl.coralic.beta.sms.client.model.Issues> getIssues()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Issues> allIssues = null;
		List<nl.coralic.beta.sms.client.model.Issues> returnIssues = new ArrayList<nl.coralic.beta.sms.client.model.Issues>();
		ArrayList<nl.coralic.beta.sms.client.model.Comment> commentsModelList = new ArrayList<nl.coralic.beta.sms.client.model.Comment>();

		String query = "select from " + Issues.class.getName();
		allIssues = (List<Issues>) pm.newQuery(query).execute();
		Iterator<Issues> i = allIssues.iterator();
		while (i.hasNext())
		{
			nl.coralic.beta.sms.client.model.Issues issueModel = new nl.coralic.beta.sms.client.model.Issues();
			Issues issue = i.next();
			issueModel.setContent(issue.getContent());
			issueModel.setDate(issue.getDate());
			issueModel.setId(String.valueOf(issue.getKey().getId()));
			issueModel.setStatus(issue.getStatus());
			issueModel.setType(issue.getType());

			Iterator<Comment> comments = issue.getComments().iterator();
			while (comments.hasNext())
			{
				Comment comment = comments.next();
				nl.coralic.beta.sms.client.model.Comment commentModel = new nl.coralic.beta.sms.client.model.Comment();
				commentModel.setAuthorname(comment.getAuthorname());
				commentModel.setComment(comment.getComment());
				commentsModelList.add(commentModel);
			}
			issueModel.setComments(commentsModelList);
			returnIssues.add(issueModel);
		}
		return returnIssues;
	}

}
