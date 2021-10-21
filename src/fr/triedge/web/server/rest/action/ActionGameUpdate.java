package fr.triedge.web.server.rest.action;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.triedge.web.server.model.GCode;
import fr.triedge.web.server.model.Game;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.model.ResponseGameUpdate;
import fr.triedge.web.server.rest.context.GameContext;
import fr.triedge.web.utils.Utils;

public class ActionGameUpdate extends GameAction{
	
	private static Logger log = Logger.getLogger(ActionGameUpdate.class);

	public ActionGameUpdate() {
		setName("update");
	}
	
	@Override
	public String execute(GameContext ctx, Params params) {
		log.debug("Request update on ID: "+params.get(Game.PARAM_ID)+" with players: "+params.get(Game.PARAM_players));
		ResponseGameUpdate rgu = new ResponseGameUpdate();
		if (ctx.getManager().updateNumberOfPlayers(
				params.get(Game.PARAM_ID), 
				Integer.valueOf(params.get(Game.PARAM_players)))) {
			rgu.setCode(GCode.OK);
			rgu.setMessage("Updated players count to "+params.get(Game.PARAM_players)+"for ID: "+params.get(Game.PARAM_ID));
			log.debug("Updated players count to "+params.get(Game.PARAM_players)+"for ID: "+params.get(Game.PARAM_ID));
		}else {
			rgu.setCode(GCode.ERROR);
			rgu.setMessage("Failed to updated players count to "+params.get(Game.PARAM_players)+" for ID: "+params.get(Game.PARAM_ID));
			log.warn("Failed to updated players count to "+params.get(Game.PARAM_players)+" for ID: "+params.get(Game.PARAM_ID));
		}
		try {
			return Utils.toJson(rgu);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
