package nl.coralic.beta.sms.client.services.listissues;

import java.util.List;

import nl.coralic.beta.sms.client.model.Issues;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class IssuesCallback implements AsyncCallback<List<Issues>>
{
	
	private IssuesTable table;

	public IssuesCallback(IssuesTable table) {
		this.table = table;
	}

	@Override
	public void onFailure(Throwable caught)
	{
		Window.alert(caught.getMessage());
	}

	@Override
	public void onSuccess(List<Issues> result)
	{
		List<Issues> issues = result;
		IssuesDataSource datasource = new IssuesDataSource(issues);
		table.setInput(datasource);
	}
}
