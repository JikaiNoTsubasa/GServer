package fr.triedge.web.server;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.triedge.web.server.model.GContext;
import fr.triedge.web.utils.XMLParser;

@XmlRootElement
public class Config {
	
	public static final String CONF_PATH			= "conf/server.xml";
	private String serverIP;
	private int serverPort;
	private int serverThreadPool;
	private ArrayList<GContext> serverContexts = new ArrayList<GContext>();
	
	private Config() {
	}
	
	public static Config INSTANCE = null;
	
	public static Config getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Config();
		return INSTANCE;
	}
	
	public void init() throws Exception {
		init(CONF_PATH);
	}
	
	public void init(String path) throws Exception {
		File f = new File(path);
		if (f.exists()) {
			INSTANCE = XMLParser.loadXml(getClass(), f);
		}else {
			throw new Exception("Cannot find configuration file: "+path);
		}
	}
	
	public void store() throws JAXBException {
		store(CONF_PATH);
	}
	
	public void store(String path) throws JAXBException {
		XMLParser.storeXml(getInstance(), new File(path));
	}
	
	public Config addContext(GContext ctx) {
		getServerContexts().add(ctx);
		return getInstance();
	}
	
	public String getServerIP() {
		return serverIP;
	}
	@XmlElement
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	
	public int getServerPort() {
		return serverPort;
	}
	@XmlElement
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public int getServerThreadPool() {
		return serverThreadPool;
	}
	@XmlElement
	public void setServerThreadPool(int serverThreadPool) {
		this.serverThreadPool = serverThreadPool;
	}

	public ArrayList<GContext> getServerContexts() {
		return serverContexts;
	}

	@XmlElementWrapper(name="GContextList")
    @XmlElement(name="GContext")
	public void setServerContexts(ArrayList<GContext> serverContexts) {
		this.serverContexts = serverContexts;
	}
	
	@Override
	public String toString() {
		return "Config ["+getServerIP()+":"+getServerPort()+", Threads:"+getServerThreadPool()+", Contexts:"+getServerContexts().size()+"]";
	}
}
