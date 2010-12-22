package nl.coralic.beta.sms.client.services.addissue;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("addNewIssue")
public interface AddNewIssueService extends RemoteService
{
	String addNewIssue(String email, String type, String text);
}
