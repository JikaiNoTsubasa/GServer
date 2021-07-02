package fr.triedge.web.server.model;

public class GResponse {

	private String content;
	
	public GResponse() {
	}
	
	public GResponse(String _content) {
		setContent(_content);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
