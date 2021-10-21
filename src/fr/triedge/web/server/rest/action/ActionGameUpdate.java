package fr.triedge.web.server.rest.action;

import org.apache.log4j.Logger;

import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Game;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.rest.context.GameContext;

public class ActionGameUpdate extends GameAction{
	
	private static Logger log = Logger.getLogger(ActionGameUpdate.class);

	public ActionGameUpdate() {
		setName("update");
	}
	
	@Override
	public GResponse execute(GameContext ctx, Params params) {
		log.debug("Request update on ID: "+params.get(Game.PARAM_ID)+" with players: "+params.get(Game.PARAM_players));
		GResponse res = new GResponse();
		if (ctx.getManager().updateNumberOfPlayers(
				params.get(Game.PARAM_ID), 
				Integer.valueOf(params.get(Game.PARAM_players)))) {
			res.setContent("OK");
			log.debug("Updated players count to "+params.get(Game.PARAM_players)+"for ID: "+params.get(Game.PARAM_ID));
		}else
			res.setContent("NOK");
		log.debug("Failed to updated players count to "+params.get(Game.PARAM_players)+"for ID: "+params.get(Game.PARAM_ID));
		return res;
	}

}
