package com.mygdx.game;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.graphics.g3d.model.data.*;
public class ModlePMNode {
	String mId;
	Matrix4 mTransform = new Matrix4();
	String mMeshId;
	String mMaterialId;
	String mMeshPartId;
	Vector3 mTranslation = new Vector3();
	Quaternion mRotation = new Quaternion(0, 0, 0, 1);	
	Vector3 mScale = new Vector3(1, 1, 1);
	
	public ModlePMNode(ModelNode mn){
		mId = mn.id;
		mMeshId = mn.meshId;
		
		if (mn.translation != null)
			mTranslation.set(mn.translation);
		if (mn.rotation != null)
			mRotation.set(mn.rotation);
		if (mn.scale != null)
			mScale.set(mn.scale);
		mTransform.set(mTranslation, mRotation, mScale);
	}
	
	public ModlePMNode(ModlePMNode fromOthers, String mid, String meid){
		mTransform.set(fromOthers.mTransform);
		mId = fromOthers.mId;
		mMeshId = fromOthers.mMeshId;
		mMaterialId = mid;
		mMeshPartId = meid;
	}
	
	public void SetMulMatrix(Matrix4 m4){
		mTransform.mul(m4);
	}
}
