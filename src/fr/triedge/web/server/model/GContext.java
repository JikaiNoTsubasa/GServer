package fr.triedge.web.server.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GContext {

	private String contextName;
	private GContextType contextType;

	public String getContextName() {
		return contextName;
	}

	@XmlElement
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public GContextType getContextType() {
		return contextType;
	}

	@XmlElement
	public void setContextType(GContextType contextType) {
		this.contextType = contextType;
	}
}
