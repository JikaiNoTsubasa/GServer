package fr.triedge.web.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.jboss.com.sun.net.httpserver.HttpExchange;

import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Params;

public class Utils {

	public static Params readParams(HttpExchange http) {
		Params par = new Params();
		String fullUrl = http.getRequestURI().toString();
		String subUrl = fullUrl.substring(fullUrl.indexOf("?")+1);
		String[] params = subUrl.split("&");
		for(String p : params){
			String[] duo = p.split("=");
			par.put(duo[0], duo[1]);
		}
		return par;
	}
	
	public static void sendResponse(GResponse resp, HttpExchange http) throws IOException {
		int code = 200;
		if (resp.getContent().contains("NOK"))
			code = 500;
		http.sendResponseHeaders(code, resp.getContent().length());
		OutputStream out = http.getResponseBody();
		out.write(resp.getContent().getBytes());
		out.flush();
		out.close();
	}
	
	public static boolean isGET(HttpExchange http) {
		return "GET".equals(http.getRequestMethod());
	}
}
