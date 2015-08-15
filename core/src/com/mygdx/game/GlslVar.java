package com.mygdx.game;

public class GlslVar {
	String mName;
	int    mLocation;
	
	public GlslVar(String name, int location){
		mName = name;
		mLocation = location;
	}
	public GlslVar(String name){
		mName = name;
		mLocation = -1;
	}
	public void setLocation(int loc){
		mLocation = loc;
	}
	public int getLocation(){
		return mLocation;
	}
}
