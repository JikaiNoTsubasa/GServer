package fr.triedge.web.server.rest.action;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.triedge.web.server.model.GCode;
import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Game;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.model.ResponseGameDelete;
import fr.triedge.web.server.rest.context.GameContext;
import fr.triedge.web.utils.Utils;

public class ActionGameDelete extends GameAction{
	
	private static Logger log = Logger.getLogger(ActionGameDelete.class);
	
	public ActionGameDelete() {
		setName("delete");
	}

	@Override
	public GResponse execute(GameContext ctx, Params params) {
		GResponse res = new GResponse();
		ResponseGameDelete rgd = new ResponseGameDelete();
		String id = params.get(Game.PARAM_ID);
		log.debug("Request delete game with ID: "+id);
		if (ctx.getManager().removeGame(id)) {
			log.debug("Game with ID "+id+ " deleted");
			res.setCode(0);
			rgd.setCode(GCode.OK);
			rgd.setMessage("Game with ID "+id+ " deleted");
		}else {
			log.debug("No game was deleted with ID: "+id);
			res.setCode(1);
			rgd.setCode(GCode.ERROR);
			rgd.setMessage("No game was deleted with ID: "+id);
		}
		try {
			res.setContent(Utils.toJson(rgd));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

}
