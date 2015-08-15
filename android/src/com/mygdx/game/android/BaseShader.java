package com.mygdx.game.android;

public class BaseShader implements Shader{
	public void init(){}
	public void begin (){}
	public void render (final Renderable renderable){};
	public boolean canRender (Renderable instance){
		return true;
	};
	public void end(){}
}
