package fr.triedge.web.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.jboss.com.sun.net.httpserver.HttpHandler;
import org.jboss.com.sun.net.httpserver.HttpServer;

import fr.triedge.web.server.model.DefaultHttpHandler;
import fr.triedge.web.server.model.GContext;
import fr.triedge.web.server.model.GContextType;
import fr.triedge.web.server.rest.RestFactory;

public class GServer {

	private static Logger log = Logger.getLogger(GServer.class);
	private HttpServer httpServer;
	
	public static void main(String[] args) {
		long ms = System.currentTimeMillis();
		log.info("Starting server...");
		GServer server = new GServer();
		server.init();
		long end = System.currentTimeMillis() - ms;
		log.info("Server started in "+end+"ms");
	}
	
	public void init() {
		log.info("Server initialisation...");
		try {
			Config.getInstance().init();
			log.debug(Config.getInstance().toString());
			log.info("Configuration loaded");
		} catch (Exception e) {
			storeDefaultConfig();
		}
		
		try {
			setHttpServer(HttpServer.create(new InetSocketAddress(Config.getInstance().getServerIP(), Config.getInstance().getServerPort()), 0));
			Config.getInstance().getServerContexts().forEach(ctx ->{
				HttpHandler handler = null;
				switch(ctx.getContextType()) {
					case GAME:
						handler = RestFactory.createGameContext(ctx.getContextName());
						break;
					case CODE:
						handler = RestFactory.createCodeContext(ctx.getContextName());
						break;
					default:
						handler = new DefaultHttpHandler();
						break;
				}
				log.debug("Creating context: "+ctx.getContextName());
				getHttpServer().createContext("/"+ctx.getContextName(), handler);
				
			});
			ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
			getHttpServer().setExecutor(threadPoolExecutor);
			getHttpServer().start();
		} catch (IOException e) {
			log.error("Could not create internal http server", e);
		}
	}
	
	public void storeDefaultConfig() {
		log.debug("Storing default config...");
		Config c = Config.getInstance();
		c.setServerIP("localhost");
		c.setServerPort(50000);
		c.setServerThreadPool(10);
		GContext ctx = new GContext();
		ctx.setContextName("neolan");
		ctx.setContextType(GContextType.GAME);
		GContext ctxc = new GContext();
		ctxc.setContextName("license");
		ctxc.setContextType(GContextType.CODE);
		c.addContext(ctx);
		c.addContext(ctxc);
		try {
			c.store();
			log.debug("Stored default config to "+Config.CONF_PATH);
		} catch (JAXBException e) {
			log.error("Failed to store default config");
		}
	}

	public HttpServer getHttpServer() {
		return httpServer;
	}

	public void setHttpServer(HttpServer httpServer) {
		this.httpServer = httpServer;
	}
	
}
