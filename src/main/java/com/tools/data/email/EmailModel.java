package com.tools.data.email;

import java.util.Date;

/**
 * This model is used to hold email related information. 
 * Subject of the email. 
 * Content of the email
 * This model should be used in a list that would represent for example the 
 * INBOX list.
 * @author voicu.vac
 *
 */
public class EmailModel {

	private String subject;
	private String content;
	private Date recievedDate;
	private Date sentDate;

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date date) {
		this.recievedDate = date;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

}
