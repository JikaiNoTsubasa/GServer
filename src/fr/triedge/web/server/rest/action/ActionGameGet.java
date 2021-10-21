package fr.triedge.web.server.rest.action;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.triedge.web.server.model.GCode;
import fr.triedge.web.server.model.Game;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.model.ResponseGameList;
import fr.triedge.web.server.rest.context.GameContext;
import fr.triedge.web.utils.Utils;

public class ActionGameGet extends GameAction{
	
	private static Logger log = Logger.getLogger(ActionGameGet.class);

	public ActionGameGet() {
		setName("get");
	}
	
	@Override
	public String execute(GameContext ctx, Params params) {
		ResponseGameList rgl = new ResponseGameList();
		if (params.containsKey(Game.PARAM_ID)) {
			String id = params.get(Game.PARAM_ID);
			log.debug("Request game for ID: "+id);
			Game g = ctx.getManager().getGameByID(id);
			if (g == null) {
				rgl.setCode(GCode.ERROR);
				log.debug("No game found for ID: "+id);
			}else {
				rgl.setCode(GCode.OK);
				rgl.getGames().add(g);
				log.debug("Game found for ID: "+id);
			}
			log.debug("Finished check for game ID: "+id);
		}else {
			log.debug("Request list of all games");
			rgl.setCode(GCode.OK);
			rgl.setGames(ctx.getManager().getGames());
		}
		
		try {
			return Utils.toJson(rgl);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
