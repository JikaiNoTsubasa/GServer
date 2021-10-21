package fr.triedge.web.server.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class CodeManager {

	private static Logger log = Logger.getLogger(CodeManager.class);
	private static final String CODE_PATH = "conf/code";
	private String code = null;
	
	
	public String getCode() throws IOException {
		if (code==null) {
			code = loadCode();
		}
		return code;
	}
	
	public void setCode(String code) throws IOException {
		log.debug("Writing code to file");
		if (this.code == null || !this.code.equals(code)) {
			log.debug("Code is different so it will be updated");
			FileWriter w;
			w = new FileWriter(new File(CODE_PATH));
			w.write(code);
			w.flush();
			w.close();
			this.code = code;
		}else {
			log.debug("Code is the same and will not be updated");
		}
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
