package com.mygdx.game.android;

import android.opengl.Matrix;

public class ModelView {
	protected float [] mMatrix = new float[16];
	
	public ModelView(){
		identity();
	}
	
	public void identity(){
		Matrix.setIdentityM(mMatrix, 0);
	}
	public void rotate(float angle, float x, float y, float z){
		Matrix.rotateM(mMatrix, 0, angle, x, y, z);		
	}
	
	public void translate(float x, float y, float z){
		Matrix.translateM(mMatrix, 0, x, y, z);
	}
	public void scale(float x, float y, float z){
		Matrix.scaleM(mMatrix, 0, x, y, z);
	}
}
