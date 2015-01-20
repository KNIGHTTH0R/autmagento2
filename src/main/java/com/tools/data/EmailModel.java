package com.tools.data;

import java.util.Date;

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
