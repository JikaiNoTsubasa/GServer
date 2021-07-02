package fr.triedge.web.server.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jboss.com.sun.net.httpserver.HttpExchange;
import org.jboss.com.sun.net.httpserver.HttpHandler;

import fr.triedge.web.utils.Utils;

public class CodeHttpHandler implements HttpHandler{

	private static Logger log = Logger.getLogger(CodeHttpHandler.class);
	private String code = null;
	private static final String CODE_PATH = "conf/code";
	
	@Override
	public void handle(HttpExchange http) throws IOException {
		log.debug("New request received for CODE Handler from "+http.getRemoteAddress().getHostString());
		Params params = Utils.readParams(http);
		if (Utils.isGET(http)) {
			GResponse resp = null;
			switch (params.get("action")) {
				case "setcode":
					resp = setCode(params);
					break;
				case "getcode":
					resp = getCode(params);
					break;
				default:
					resp = new GResponse("NULL");
					break;
			}
			Utils.sendResponse(resp, http);
		}
	}
	
	private GResponse setCode(Params params) {
		log.debug("Request set code");
		GResponse res = new GResponse();
		String _code = params.get("code");
		if (!_code.equals(code)) {
			FileWriter w;
			try {
				w = new FileWriter(new File(CODE_PATH));
				w.write(_code);
				w.flush();
				w.close();
				res.setContent("OK");
				code = _code;
				log.debug("New code saved");
			} catch (IOException e) {
				res.setContent("NOK");
			}
		}else {
			log.debug("Provided code is the same as previously, nothing changed");
			res.setContent("OK");
		}
		
		return res;
	}
	
	private GResponse getCode(Params params) {
		log.debug("Request get code");
		GResponse res = new GResponse();
		if (code == null) {
			log.debug("Internal code is null, loading from file...");
			try {
				code = loadCode();
				log.debug("Code loaded");
			} catch (IOException e) {
				log.error("Cannot load code", e);
				code = null;
			}
			if (code == null)
				res.setContent("NOK");
			else
				res.setContent(code);
		}else {
			log.debug("Internal code is valid, providing the cached code");
			res.setContent(code);
		}
		
		return res;
	}
	
	private String loadCode() throws IOException {
		File f = new File(CODE_PATH);
		if (!f.exists())
			throw new IOException("File "+CODE_PATH+" does not exist");
		
		FileReader r;
		r = new FileReader(f);
		BufferedReader br = new BufferedReader(r);
		String _res = br.readLine();
		br.close();
		return _res;
	}

}
