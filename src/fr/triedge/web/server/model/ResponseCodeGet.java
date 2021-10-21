package fr.triedge.web.server.model;

public class ResponseCodeGet {

	private GCode code;
	private String message;
	public GCode getCode() {
		return code;
	}
	public void setCode(GCode code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
