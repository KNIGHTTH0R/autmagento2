package com.tools.geolocation;

import org.codehaus.jackson.annotate.JsonIgnore;

public class GoogleResponse {

	private Result[] results;
	private String status;
	@JsonIgnore
	private String exclude_from_slo;

	public Result[] getResults() {
		return results;
	}

	public void setResults(Result[] results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
