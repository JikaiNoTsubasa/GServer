package fr.triedge.web.server.rest.action;

import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.rest.context.GameContext;

public abstract class GameAction {

	private String name;
	
	public GameAction() {
	}
	
	public abstract String execute(GameContext ctx, Params params);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
