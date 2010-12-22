package nl.coralic.beta.sms.client.services.listissues;

import java.util.List;

import nl.coralic.beta.sms.client.model.Issues;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;

public class IssuesTable extends FlexTable 
{
	IssuesDataSource inputData;

	public IssuesTable(IssuesDataSource input)
	{
		super();
		setStyleName("issuesTable");
		setInput(input);
		
		sinkEvents(Event.ONMOUSEOVER | Event.ONMOUSEOUT | Event.ONMOUSEDOWN | Event.ONMOUSEUP);
	}

	public void setInput(IssuesDataSource input)
	{
		for (int i = this.getRowCount(); i > 0; i--)
		{
			this.removeRow(0);
		}
		if (input == null)
		{
			return;
		}

		int row = 0;
		List<String> headers = input.getTableHeader();
		if (headers != null)
		{
			int i = 0;
			for (String string : headers)
			{
				this.setText(row, i, string);
				i++;
			}
			row++;
		}
		getRowFormatter().addStyleName(0, "tableHeader");

		List<Issues> rows = input.getIssues();
		int i = 1;
		for (Issues issues : rows)
		{
			setText(i, 0, issues.getId());
			setText(i, 1, issues.getDate().toString());
			setText(i, 2, issues.getStatus());
			setText(i, 3, issues.getType());
			setText(i, 4, issues.getContent());
			setText(i, 5, String.valueOf(issues.getComments().size()));
			i++;
		}
		inputData = input;
	}

	public void onBrowserEvent(Event event)
	{
		Element td = getEventTargetCell(event);
		if (td == null)
			return;
		Element tr = DOM.getParent(td);
		switch (DOM.eventGetType(event))
		{
			case Event.ONMOUSEDOWN:
			{
				DOM.setStyleAttribute(tr, "backgroundColor", "green");
				Window.alert(tr.getFirstChildElement().getInnerText());
				break;
			}
			case Event.ONMOUSEUP:
			{
				DOM.setStyleAttribute(tr, "backgroundColor", "#f5f3e5");
				break;
			}
			case Event.ONMOUSEOVER:
			{
				DOM.setStyleAttribute(tr, "backgroundColor", "green");
				break;
			}
			case Event.ONMOUSEOUT:
			{
				DOM.setStyleAttribute(tr, "backgroundColor", "#f5f3e5");
				break;
			}
		}

	}
}
