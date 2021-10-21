package fr.triedge.web.server.rest.context;

import java.util.HashMap;

import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.GameManager;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.rest.action.GameAction;

public class GameContext extends RestContext{
	
	private GameManager manager = new GameManager();
	private HashMap<String, GameAction> actions = new HashMap<>();

	public GameContext(String name) {
		super(name);
	}

	public GameManager getManager() {
		return manager;
	}

	public void setManager(GameManager manager) {
		this.manager = manager;
	}

	@Override
	public GResponse execute(String action, Params params) {
		GameAction ac = getActions().get(action);
		return ac.execute(this, params);
	}

	public HashMap<String, GameAction> getActions() {
		return actions;
	}

	public void setActions(HashMap<String, GameAction> actions) {
		this.actions = actions;
	}

}
