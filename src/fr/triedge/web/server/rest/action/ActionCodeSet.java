package fr.triedge.web.server.rest.action;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.triedge.web.server.model.GCode;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.model.ResponseCodeSet;
import fr.triedge.web.server.rest.context.CodeContext;
import fr.triedge.web.utils.Utils;

public class ActionCodeSet extends CodeAction{
	
	private static Logger log = Logger.getLogger(ActionCodeSet.class);
	
	public ActionCodeSet() {
		setName("set");
	}

	@Override
	public String execute(CodeContext ctx, Params params) {
		log.debug("Request set license code");
		ResponseCodeSet rcs = new ResponseCodeSet();
		if (params.containsKey("code")) {
			String code = params.get("code");
			log.debug("Code found in request");
			try {
				ctx.getCodeManager().setCode(code);
				rcs.setCode(GCode.OK);
				rcs.setMessage("Code updated successfully");
			} catch (IOException e) {
				log.error("Failed to write code to file", e);
				rcs.setCode(GCode.ERROR);
				rcs.setMessage(e.getMessage());
			}
		}else {
			rcs.setCode(GCode.ERROR);
			rcs.setMessage("No code provided");
			log.warn("No code provided");
		}
		String res = "";
		try {
			res = Utils.toJson(rcs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

}
