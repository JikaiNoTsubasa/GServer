package fr.triedge.web.server.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Game {

	public static final String sep = "::";
	private String id, name, hostname;
	private Date creationDate;
	private int currentPlayers;
	private String password;
	
	public static final String PARAM_ID					= "id";
	public static final String PARAM_IP					= "ip";
	public static final String PARAM_NAME				= "desc";
	public static final String PARAM_players			= "players";
	public static final String PARAM_PWD				= "pwd";
	
	public static final String ACTION_CREATE			= "create";
	public static final String ACTION_DELETE			= "delete";
	public static final String ACTION_UPDATE			= "update";
	public static final String ACTION_GET				= "get";
	
	public Game() {
		setName("Default Game");
		setCreationDate(new Date());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getCurrentPlayers() {
		return currentPlayers;
	}

	public void setCurrentPlayers(int currentPlayers) {
		this.currentPlayers = currentPlayers;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder tmp = new StringBuilder();
		tmp.append(getId());
		tmp.append(sep);
		tmp.append(getHostname());
		tmp.append(sep);
		tmp.append(getCurrentPlayers());
		tmp.append(sep);
		tmp.append(getName());
		tmp.append(sep);
		SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd-HHmmss");
		tmp.append(format.format(getCreationDate()));
		tmp.append(sep);
		tmp.append(getPassword());
		return tmp.toString();
	}
	
	public String toJson() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}
