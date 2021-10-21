package fr.triedge.web.server.rest.context;

import java.util.HashMap;

import fr.triedge.web.server.model.CodeManager;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.rest.action.CodeAction;

public class CodeContext extends RestContext{

	private CodeManager codeManager = new CodeManager();
	private HashMap<String, CodeAction> actions = new HashMap<>();
	
	public CodeContext(String name) {
		super(name);
	}

	@Override
	public String execute(String action, Params params) {
		CodeAction ac = getActions().get(action);
		return ac.execute(this, params);
	}

	public HashMap<String, CodeAction> getActions() {
		return actions;
	}

	public void setActions(HashMap<String, CodeAction> actions) {
		this.actions = actions;
	}

	public CodeManager getCodeManager() {
		return codeManager;
	}

	public void setCodeManager(CodeManager codeManager) {
		this.codeManager = codeManager;
	}
	
	

}
