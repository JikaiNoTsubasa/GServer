package fr.triedge.web.server.rest.action;

import org.apache.log4j.Logger;

import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Game;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.rest.context.GameContext;

public class ActionGameDelete extends GameAction{
	
	private static Logger log = Logger.getLogger(ActionGameDelete.class);
	
	public ActionGameDelete() {
		setName("delete");
	}

	@Override
	public GResponse execute(GameContext ctx, Params params) {
		GResponse res = new GResponse();
		String id = params.get(Game.PARAM_ID);
		log.debug("Request delete game with ID: "+id);
		if (ctx.getManager().removeGame(id)) {
			log.debug("Game with ID "+id+ " deleted");
			res.setContent("OK");
		}else {
			log.debug("No game was deleted with ID: "+id);
			res.setContent("NOK");
		}
		return res;
	}

}
