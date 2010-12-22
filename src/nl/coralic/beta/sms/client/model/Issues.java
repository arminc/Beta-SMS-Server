package nl.coralic.beta.sms.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Issues implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String content;

	private Date date;
	
	private ArrayList<Comment> comments;
	
	private String status;

	private String type;

	public Issues()
	{
		super();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public ArrayList<Comment> getComments()
	{
		return comments;
	}

	public void setComments(ArrayList<Comment> comments)
	{
		this.comments = comments;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
