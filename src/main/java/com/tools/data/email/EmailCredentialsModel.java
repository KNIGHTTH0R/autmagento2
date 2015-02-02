package com.tools.data.email;

/**
 * This model is used to pass to the [Email]Connector the authentication credentials.
 * @author voicu.vac
 *
 */
public class EmailCredentialsModel {

	private String host;
	private String protocol;
	private String username;
	private String password;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}
