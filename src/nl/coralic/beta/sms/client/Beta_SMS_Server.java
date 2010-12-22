package nl.coralic.beta.sms.client;

import nl.coralic.beta.sms.client.services.listissues.GetAllIssuesService;
import nl.coralic.beta.sms.client.services.listissues.GetAllIssuesServiceAsync;
import nl.coralic.beta.sms.client.services.listissues.IssuesCallback;
import nl.coralic.beta.sms.client.services.listissues.IssuesTable;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Beta_SMS_Server implements EntryPoint
{
	private IssuesTable table;
	private AddNewIssueDialog addNewIssueDialog;
	AppConst appConst = GWT.create(AppConst.class);
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		RootPanel.get().getElement().getStyle().setProperty("backgroundColor", "#f5f3e5");
		Label appName = new Label(appConst.appName());
		appName.setStyleName("appName");

		HorizontalPanel vp = new HorizontalPanel();

		Button addNewIssue = new Button("New", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event)
			{
				addNewIssueDialog = new AddNewIssueDialog(Beta_SMS_Server.this);
				addNewIssueDialog.show();
			}
		});

		Label indexText = new Label(
				"This is an list of all the issues and feature requests for Beta-SMS. If you wish to submit an issue or have a feature request please do so ");
		indexText.setStyleName("indexText");
		vp.add(indexText);
		vp.add(addNewIssue);

		RootPanel.get().add(appName);
		RootPanel.get().add(vp);

		table = new IssuesTable(null);
		
		RootPanel.get().add(table);
		getAllIssues();
	}

	public void getAllIssues()
	{
		GetAllIssuesServiceAsync service = (GetAllIssuesServiceAsync) GWT.create(GetAllIssuesService.class);
		ServiceDefTarget serviceDef = (ServiceDefTarget) service;
		serviceDef.setServiceEntryPoint("getAllIssues");
		IssuesCallback issuesCallback = new IssuesCallback(table);
		service.getIssues(issuesCallback);
	}
}
