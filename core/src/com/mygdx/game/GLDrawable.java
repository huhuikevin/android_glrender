package com.mygdx.game;

import com.badlogic.gdx.math.Matrix4;

public class GLDrawable {
	short [] mIndices;
	int mPrimitiveType;
	Matrix4 mTransform ;
	GLMaterial mMaterial;
	
	public GLDrawable(ModelFace mf, GLMaterial mt, Matrix4 t){
		mIndices = mf.mIndices;
		mPrimitiveType = mf.mPrimitiveType;
		mMaterial = mt;
		mTransform = t;
	}
	public GLDrawable(ModelFace mf, GLMaterial mt){
		this(mf, mt,  new Matrix4());
	}	
}
