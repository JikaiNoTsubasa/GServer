package fr.triedge.web.server.rest;

import fr.triedge.web.server.rest.action.ActionGameCreate;
import fr.triedge.web.server.rest.action.ActionGameDelete;
import fr.triedge.web.server.rest.action.ActionGameGet;
import fr.triedge.web.server.rest.action.ActionGameUpdate;
import fr.triedge.web.server.rest.context.GameContext;

public class RestFactory {

	public static GameContext createGameContext(String name) {
		GameContext ctx = new GameContext(name);
		
		ActionGameCreate create = new ActionGameCreate();
		ActionGameDelete delete = new ActionGameDelete();
		ActionGameUpdate update = new ActionGameUpdate();
		ActionGameGet get = new ActionGameGet();
		
		ctx.getActions().put(create.getName(), create);
		ctx.getActions().put(delete.getName(), delete);
		ctx.getActions().put(update.getName(), update);
		ctx.getActions().put(get.getName(), get);
		return ctx;
	}
}
