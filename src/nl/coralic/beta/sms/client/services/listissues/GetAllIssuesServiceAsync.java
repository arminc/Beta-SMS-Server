package nl.coralic.beta.sms.client.services.listissues;

import java.util.List;

import nl.coralic.beta.sms.client.model.Issues;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GetAllIssuesServiceAsync
{
	void getIssues(AsyncCallback<List<Issues>> callback);
}
