package fr.triedge.web.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.jboss.com.sun.net.httpserver.HttpExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.triedge.web.server.model.GResponse;
import fr.triedge.web.server.model.Params;

public class Utils {
	
	private static Logger log = Logger.getLogger(Utils.class);

	public static Params readParams(HttpExchange http) {
		Params par = new Params();
		String fullUrl = http.getRequestURI().toString();
		log.debug("FULL URL: "+fullUrl);
		String subUrl = fullUrl.substring(fullUrl.indexOf("?")+1);
		String[] params = subUrl.split("&");
		for(String p : params){
			log.debug("Parsed Param: "+p);
			String[] duo = p.split("=");
			par.put(duo[0], duo[1]);
		}
		return par;
	}
	
	public static void sendResponse(int code, String body, HttpExchange http) throws IOException {
		http.sendResponseHeaders(code, body.length());
		OutputStream out = http.getResponseBody();
		out.write(body.getBytes());
		out.flush();
		out.close();
	}
	
	public static void sendResponse(GResponse resp, HttpExchange http) throws IOException {
		int code = 200;
		if (resp.getCode()>0)
			code = resp.getCode();
		http.sendResponseHeaders(code, resp.getContent().length());
		OutputStream out = http.getResponseBody();
		out.write(resp.getContent().getBytes());
		out.flush();
		out.close();
	}
	
	public static void sendResponse(String json, HttpExchange http) throws IOException {
		http.sendResponseHeaders(200, json.length());
		OutputStream out = http.getResponseBody();
		out.write(json.getBytes());
		out.flush();
		out.close();
	}
	
	public static boolean isGET(HttpExchange http) {
		return "GET".equals(http.getRequestMethod());
	}
	
	public static String toJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
}
