package fr.triedge.web.server.model;

import java.util.ArrayList;
import java.util.Iterator;

public class GameManager {

private ArrayList<Game> games = new ArrayList<>();
	
	public void createGame(String id, String ip){
		createGame(id, ip, "Default Game");
	}
	
	public Game createGame(String id, String ip, String desc){
		if (getGameByIP(ip) != null)
			return null;
		Game g = new Game();
		g.setName(desc);
		g.setHostname(ip);
		g.setId(id);
		g.setCurrentPlayers(1);
		games.add(g);
		return g;
	}
	
	public Game createGame(String id, String ip, String desc, String password){
		if (getGameByIP(ip) != null)
			return null;
		Game g = new Game();
		g.setName(desc);
		g.setHostname(ip);
		g.setId(id);
		g.setCurrentPlayers(1);
		g.setPassword(password);
		games.add(g);
		return g;
	}
	
	public boolean removeGame(String id){
		Iterator<Game> it = games.iterator();
		while(it.hasNext()){
			Game g = it.next();
			if (g.getId().equals(id)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean updateNumberOfPlayers(String id, int number){
		Iterator<Game> it = games.iterator();
		while(it.hasNext()){
			Game g = it.next();
			if (g.getId().equals(id)){
				g.setCurrentPlayers(number);
				return true;
			}
			
		}
		return false;
	}
	
	public ArrayList<Game> getGames(){
		return games;
	}
	
	public Game getGameByIP(String ip){
		for (Game game : games)
			if (ip.equals(game.getHostname()))
				return game;
		return null;
	}
	
	public Game getGameByID(String id){
		for (Game game : games)
			if (id.equals(game.getId()))
				return game;
		return null;
	}
}
