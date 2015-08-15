package com.mygdx.game.android;

import android.opengl.*;
public class BaseCamera {
	private float [] mProjection = new float[16];
	private float [] mView = new float[16];
	private float [] mViewProjection = new float[16];

	public BaseCamera(){
		Matrix.setIdentityM(mProjection, 0);
		Matrix.setIdentityM(mView, 0);
		Matrix.setIdentityM(mViewProjection, 0);
	}
	
	public void ortho(float left, float right, float bottom, float top,
	        float near, float far){
		Matrix.frustumM(mProjection, 0, left, right, bottom, top, near, far);
		update();
	}
	
	public void perspective(float fovy, float aspect, float zNear, float zFar){
		Matrix.perspectiveM(mProjection, 0, fovy, aspect, zNear, zFar);
		update();
	}
	
	public void lookat(float eyeX, float eyeY, float eyeZ,
            float centerX, float centerY, float centerZ, float upX, float upY,
            float upZ){
		
		Matrix.setLookAtM(mView, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
		update();
	}
	
	public void update(){
		Matrix.multiplyMM(mViewProjection, 0, mProjection, 0, mView, 0);
	}
	
	public float[] getMatrix(){
		return mViewProjection;
	}
}
