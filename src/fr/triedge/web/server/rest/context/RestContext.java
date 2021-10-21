package fr.triedge.web.server.rest.context;

import java.io.IOException;

import org.jboss.com.sun.net.httpserver.HttpExchange;
import org.jboss.com.sun.net.httpserver.HttpHandler;

import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.utils.Utils;

public abstract class RestContext implements HttpHandler{

	private String name;
	
	public RestContext(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract GResponse execute(String action, Params params);
	
	@Override
	public void handle(HttpExchange http) throws IOException {
		Params params = Utils.readParams(http);
		GResponse resp = execute(params.get("action"), params);
		Utils.sendResponse(resp, http);
	}
}
