package com.mygdx.game;

public class GLColor extends GLAttribute{
	public static final GLColor CLEAR = new GLColor(0, 0, 0, 0);
	public static final GLColor WHITE = new GLColor(1, 1, 1, 1);
	public static final GLColor BLACK = new GLColor(0, 0, 0, 1);
	public static final GLColor RED = new GLColor(1, 0, 0, 1);
	public static final GLColor GREEN = new GLColor(0, 1, 0, 1);
	public static final GLColor BLUE = new GLColor(0, 0, 1, 1);
	public static final GLColor LIGHT_GRAY = new GLColor(0.75f, 0.75f, 0.75f, 1);
	public static final GLColor GRAY = new GLColor(0.5f, 0.5f, 0.5f, 1);
	public static final GLColor DARK_GRAY = new GLColor(0.25f, 0.25f, 0.25f, 1);
	public static final GLColor PINK = new GLColor(1, 0.68f, 0.68f, 1);
	public static final GLColor ORANGE = new GLColor(1, 0.78f, 0, 1);
	public static final GLColor YELLOW = new GLColor(1, 1, 0, 1);
	public static final GLColor MAGENTA = new GLColor(1, 0, 1, 1);
	public static final GLColor CYAN = new GLColor(0, 1, 1, 1);
	public static final GLColor OLIVE = new GLColor(0.5f, 0.5f, 0, 1);
	public static final GLColor PURPLE = new GLColor(0.5f, 0, 0.5f, 1);
	public static final GLColor MAROON = new GLColor(0.5f, 0, 0, 1);
	public static final GLColor TEAL = new GLColor(0, 0.5f, 0.5f, 1);
	public static final GLColor NAVY = new GLColor(0, 0, 0.5f, 1);	
	float r, g, b, a;
	String mName;
	public GLColor(float r, float g, float b, float a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	public GLColor(float r, float g, float b, float a, String name){
		this( r,  g,  b,  a);
		mName = name;
	}
	public GLColor(float r, float g, float b, float a, long type){
		super(type);	
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
			
	}	
	public GLColor(){}
	public float getr(){
		return r;
	}
	public float getg(){
		return g;
	}
	public float getb(){
		return b;
	}
	public float geta(){
		return a;
	}
}
