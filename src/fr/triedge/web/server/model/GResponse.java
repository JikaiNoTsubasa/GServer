package fr.triedge.web.server.model;

public class GResponse {

	private int code;
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
