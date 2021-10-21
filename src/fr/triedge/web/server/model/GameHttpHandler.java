package fr.triedge.web.server.model;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jboss.com.sun.net.httpserver.HttpExchange;
import org.jboss.com.sun.net.httpserver.HttpHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.triedge.web.utils.Utils;

public class GameHttpHandler implements HttpHandler{
	
	private static Logger log = Logger.getLogger(GameHttpHandler.class);
	private GameManager manager = new GameManager();

	@Override
	public void handle(HttpExchange http) throws IOException {
		log.debug("New request received for GAME Handler from "+http.getRemoteAddress().getHostString());
		Params params = Utils.readParams(http);
		if (Utils.isGET(http)) {
			GResponse resp = null;
			switch (params.get("action")) {
				case Game.ACTION_CREATE:
					resp = createGame(params);
					break;
				case Game.ACTION_DELETE:
					resp = deleteGame(params);
					break;
				case Game.ACTION_GET:
					resp = getGames(params);
					break;
				case Game.ACTION_UPDATE:
					resp = updateGame(params);
					break;
				default:
					resp = new GResponse("NULL");
					break;
			}
			Utils.sendResponse(resp, http);
		}
		
	}

	private GResponse updateGame(Params params) {
		log.debug("Request update on ID: "+params.get(Game.PARAM_ID)+" with players: "+params.get(Game.PARAM_players));
		GResponse res = new GResponse();
		if (getManager().updateNumberOfPlayers(
				params.get(Game.PARAM_ID), 
				Integer.valueOf(params.get(Game.PARAM_players)))) {
			res.setContent("OK");
		}else
			res.setContent("NOK");
		return res;
	}

	private GResponse getGames(Params params) {
		log.debug("Request list of all games");
		GResponse res = new GResponse();
		String s = "";
		for (Game g : getManager().getGames())
			try {
				s+=g.toJson()+"\r\n";
			} catch (JsonProcessingException e) {
				log.error("Failed to parse to json",e);
			}
		res.setContent(s);
		return res;
	}

	private GResponse deleteGame(Params params) {
		log.debug("Request delete game with ID: "+params.get(Game.PARAM_ID));
		GResponse res = new GResponse();
		String id = params.get(Game.PARAM_ID);
		log.debug("Request delete game with ID: "+id);
		if (getManager().removeGame(id)) {
			log.debug("Game with ID "+id+ " deleted");
			res.setContent("OK");
		}else {
			log.debug("No game was deleted with ID: "+id);
			res.setContent("NOK");
		}
		return res;
	}

	private GResponse createGame(Params params) {
		log.debug("Request create new game...");
		GResponse res = new GResponse();
		Game g = getManager().createGame(
				params.get(Game.PARAM_ID), 
				params.get(Game.PARAM_IP), 
				params.get(Game.PARAM_NAME));
		if (g == null) {
			log.warn("Game not created because id is null");
			res.setContent("NOK");
		}else {
			res.setContent("OK");
			log.debug("Game created with: "+ g.toString());
		}
		return res;
	}

	public GameManager getManager() {
		return manager;
	}

	public void setManager(GameManager manager) {
		this.manager = manager;
	}

}
