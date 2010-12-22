package nl.coralic.beta.sms.client.services.listissues;

import java.util.List;

import nl.coralic.beta.sms.client.model.Issues;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("getAllIssues")
public interface GetAllIssuesService extends RemoteService
{
	List<Issues> getIssues();
}
