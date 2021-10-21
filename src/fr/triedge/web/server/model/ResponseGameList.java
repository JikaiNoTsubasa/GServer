package fr.triedge.web.server.model;

import java.util.ArrayList;

public class ResponseGameList {

	private GCode code;
	private ArrayList<Game> games;
	public GCode getCode() {
		return code;
	}
	public void setCode(GCode code) {
		this.code = code;
	}
	public ArrayList<Game> getGames() {
		return games;
	}
	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}
	
	
}
