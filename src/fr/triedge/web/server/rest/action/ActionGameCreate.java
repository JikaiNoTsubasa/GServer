package fr.triedge.web.server.rest.action;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.triedge.web.server.model.GCode;
import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Game;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.model.ResponseGameCreate;
import fr.triedge.web.server.rest.context.GameContext;
import fr.triedge.web.utils.Utils;

public class ActionGameCreate extends GameAction{

	private static Logger log = Logger.getLogger(ActionGameCreate.class);
	
	public ActionGameCreate() {
		setName("create");
	}

	@Override
	public GResponse execute(GameContext ctx, Params params) {
		log.debug("Request create new game with ID: "+params.get(Game.PARAM_ID));
		ResponseGameCreate rgc = new ResponseGameCreate();
		GResponse res = new GResponse();
		Game g = ctx.getManager().createGame(
				params.get(Game.PARAM_ID), 
				params.get(Game.PARAM_IP), 
				params.get(Game.PARAM_NAME),
				params.get(Game.PARAM_PWD));
		
		if (g == null) {
			res.setCode(1);
			rgc.setCode(GCode.ERROR);
			rgc.setMessage("Game not created because IP already used");
			log.warn("Game not created because IP already used");
		}else {
			res.setCode(0);
			rgc.setCode(GCode.OK);
			rgc.setMessage("Game created width ID: "+g.getId());
			log.debug("Game created with: "+ g.toString());
		}
		try {
			res.setContent(Utils.toJson(rgc));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

}
