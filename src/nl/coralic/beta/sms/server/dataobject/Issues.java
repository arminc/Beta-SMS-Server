package nl.coralic.beta.sms.server.dataobject;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Issues
{	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String content;

	@Persistent
	private Date date;
	
	@Persistent
	private ArrayList<Comment> comments;
	
	@Persistent
	private String status;
	
	@Persistent
	private String email;
	
	@Persistent
	private String type;
	
	@Persistent
	private Text debug;

	public Issues()
	{
		super();
	}
	
	public Issues(String content, Date date, ArrayList<Comment> comments, String status, Text debug, String email, String type)
	{
		this.content = content;
		this.date = date;
		this.comments = comments;
		this.status = status;
		this.debug = debug;
		this.email = email;
		this.type = type;
	}

	public Key getKey()
	{
		return key;
	}

	public void setKey(Key key)
	{
		this.key = key;
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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Text getDebug()
	{
		return debug;
	}

	public void setDebug(Text debug)
	{
		this.debug = debug;
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
