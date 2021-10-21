package fr.triedge.web.server.rest.action;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.triedge.web.server.model.GCode;
import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.model.ResponseGameList;
import fr.triedge.web.server.rest.context.GameContext;

public class ActionGameGet extends GameAction{
	
	private static Logger log = Logger.getLogger(ActionGameGet.class);

	public ActionGameGet() {
		setName("get");
	}
	
	@Override
	public GResponse execute(GameContext ctx, Params params) {
		log.debug("Request list of all games");
		GResponse res = new GResponse();
		ResponseGameList rgl = new ResponseGameList();
		rgl.setCode(GCode.OK);
		rgl.setGames(ctx.getManager().getGames());
		ObjectMapper mapper = new ObjectMapper();
		String ret;
		try {
			ret = mapper.writeValueAsString(rgl);
			res.setContent(ret);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

}
