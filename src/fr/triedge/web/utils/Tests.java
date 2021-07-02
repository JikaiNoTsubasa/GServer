package fr.triedge.web.utils;

import java.io.File;

import javax.xml.bind.JAXBException;

import fr.triedge.web.server.Config;
import fr.triedge.web.server.model.GContext;
import fr.triedge.web.server.model.GContextType;

public class Tests {

	public static void main(String[] args) {
		Config c = Config.getInstance();
		c.setServerIP("localhost");
		c.setServerPort(50000);
		c.setServerThreadPool(10);
		GContext ctx = new GContext();
		ctx.setContextName("neolan");
		ctx.setContextType(GContextType.GAME);
		c.addContext(ctx);
		try {
			XMLParser.storeXml(c, new File(Config.CONF_PATH));
			System.out.println("Stored");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
