package fr.triedge.web.server.rest.action;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.triedge.web.server.model.GCode;
import fr.triedge.web.server.model.Params;
import fr.triedge.web.server.model.ResponseCodeGet;
import fr.triedge.web.server.rest.context.CodeContext;
import fr.triedge.web.utils.Utils;

public class ActionCodeGet extends CodeAction{

	private static Logger log = Logger.getLogger(ActionCodeGet.class);
	
	public ActionCodeGet() {
		setName("get");
	}
	
	@Override
	public String execute(CodeContext ctx, Params params) {
		log.debug("Request code for license");
		ResponseCodeGet rcg = new ResponseCodeGet();
		try {
			String code = ctx.getCodeManager().getCode();
			if (code == null) {
				rcg.setCode(GCode.ERROR);
				rcg.setMessage("Code is null");
				log.warn("License code is NULL");
			}else {
				rcg.setCode(GCode.OK);
				rcg.setMessage(code);
				log.debug("License code is provided");
			}
		} catch (IOException e) {
			e.printStackTrace();
			rcg.setCode(GCode.ERROR);
			rcg.setMessage(e.getMessage());
		}
		
		String res = "";
		try {
			res = Utils.toJson(rcg);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return res;
	}

}
