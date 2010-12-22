package nl.coralic.beta.sms.client.services.listissues;

import java.util.ArrayList;
import java.util.List;

import nl.coralic.beta.sms.client.model.Issues;


public class IssuesDataSource
{
	private final List<Issues> issues;
	private List<String> header;

	public IssuesDataSource(List<Issues> issues)
	{
		header = new ArrayList<String>();
		header.add("ID");
		header.add("DATE");
		header.add("STATUS");
		header.add("TYPE");
		header.add("SUMMERY");
		header.add("COMMENTS");
		this.issues = issues;
	}

	public List<Issues> getIssues()
	{
		return issues;
	}

	public List<String> getTableHeader()
	{
		return header;
	}
}
