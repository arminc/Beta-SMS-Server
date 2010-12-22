package nl.coralic.beta.sms.client;

import nl.coralic.beta.sms.client.services.addissue.AddNewIssueService;
import nl.coralic.beta.sms.client.services.addissue.AddNewIssueServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class AddNewIssueDialog extends DialogBox
{
	private final AddNewIssueServiceAsync addNewIssueService = GWT.create(AddNewIssueService.class);
	private TextBox txtEmail;
	private ListBox lstType;
	private TextArea txtText;
	Beta_SMS_Server entry;
	FlexTable flexTable;

	public AddNewIssueDialog(Beta_SMS_Server entry)
	{
		this.entry = entry;
		setGlassEnabled(true);
		setAnimationEnabled(true);
		setStyleName("addNewIssueDialog");
		center();
		setText("Add a new Issue/Request new feature.");

		flexTable = new FlexTable();
		flexTable.setWidth("600px");
		flexTable.setCellSpacing(6);
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();

		flexTable.setHTML(0, 0, "Email (Optional):");
		txtEmail = new TextBox();
		flexTable.setWidget(0, 1, txtEmail);
		flexTable.setHTML(1, 0, "Type (Mandatory):");
		lstType = new ListBox();
		lstType.addItem("Bug");
		lstType.addItem("Feature");
		lstType.addItem("Other");
		flexTable.setWidget(1, 1, lstType);
		flexTable.setHTML(2, 0, "Text (Mandatory):");
		txtText = new TextArea();
		txtText.setWidth("400px");
		flexTable.setWidget(2, 1, txtText);

		Button send = new Button("Submit", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event)
			{
				if(validate())
				{
				addNewIssue();
				}
			}
		});
		flexTable.setWidget(3, 1, send);

		cellFormatter.setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_RIGHT);

		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(flexTable);
		setWidget(decPanel);
	}

	private void addNewIssue()
	{
		addNewIssueService.addNewIssue(txtEmail.getText(), lstType.getItemText(lstType.getSelectedIndex()), txtText.getText(),
				new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result)
					{
						entry.getAllIssues();
						AddNewIssueDialog.this.hide();
					}

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert(caught.getMessage());
					}
				});
	}
	
	private boolean validate()
	{
		boolean returnData = true;
		if(txtText == null || txtText.getText().equalsIgnoreCase(""))
		{
			Label l = new Label("Text can not be empty.");
			DOM.setStyleAttribute(l.getElement(), "color",  "red");
			flexTable.setWidget(2, 3, l);
			returnData = false;
		}
		return returnData;
	}
}
