package fr.triedge.web.server.rest.action;

import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.rest.context.CodeContext;

public abstract class CodeAction {

	private String name;
	
	public CodeAction() {
	}
	
	public abstract String execute(CodeContext ctx, Params params);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
