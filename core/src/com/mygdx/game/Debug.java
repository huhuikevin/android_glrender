package com.mygdx.game;

import com.badlogic.gdx.utils.Logger;

public class Debug {
	private Logger log;
	public static int DEBUG = 3;
	public Debug(int level) {
		log = new Logger("MyGdxGame", level);
	}
	public Debug(){
		log = new Logger("MyGdxGame", Logger.DEBUG);
	}
	void debug(String msg){
		log.info(msg);
	}
}
