package nl.coralic.beta.sms.client.services.addissue;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddNewIssueServiceAsync
{
	void addNewIssue(String email, String type, String text, AsyncCallback<String> callback);
}
