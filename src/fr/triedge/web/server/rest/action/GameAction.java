package fr.triedge.web.server.rest.action;

import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.rest.context.GameContext;
import fr.triedge.web.server.rest.context.RestContext;

public abstract class GameAction {

	private String name;
	
	public GameAction() {
	}
	
	public abstract GResponse execute(GameContext ctx, Params params);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
