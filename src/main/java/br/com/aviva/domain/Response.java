package br.com.aviva.domain;

public class Response {
	private String response;
	private String sessionId;

	public Response() {
		super();
	}

	public Response(final String response) {
		super();
		this.response = response;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(final String response) {
		this.response = response;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

}
