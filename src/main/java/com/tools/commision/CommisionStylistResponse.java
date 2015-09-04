package com.tools.commision;

import org.codehaus.jackson.annotate.JsonIgnore;

public class CommisionStylistResponse {

	private String status;
	private StylistBody body;
	@JsonIgnore
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StylistBody getBody() {
		return body;
	}

	public void setBody(StylistBody body) {
		this.body = body;
	}

}
