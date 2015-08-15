package com.mygdx.game;
import com.badlogic.gdx.graphics.g3d.model.data.*;
//import com.badlogic.gdx.math.*;
public class ModelFace {
	String mId;
	short [] mIndices;
	int mPrimitiveType;
//	GLMaterial mMaterial;
	//Matrix4 mTransform = new Matrix4();
	public ModelFace(ModelMeshPart mp){
		mIndices = mp.indices;
		mPrimitiveType = mp.primitiveType;
		mId = mp.id;
	}

}
