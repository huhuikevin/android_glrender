package com.mygdx.game;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.utils.*;
public class GLMaterial {
	public String mId;
	public GLColorAttribute mAmbient;
	public GLColorAttribute mDiffuse;
	public GLColorAttribute mSpecular;
	public GLColorAttribute mEmissive;
	public GLColorAttribute mReflection;

	public float mShininess;
	public float mOpacity = 1.f;	
	
	Array<GLTextureAttribute> mTextures = new Array<GLTextureAttribute>(); 
	long mMaskColor;
	long mMaskTexture;
	public GLMaterial(ModelMaterial mm){
		mId = mm.id;
		if (mm.ambient != null) {
			mAmbient = new GLColorAttribute(mm.ambient, GLColorAttribute.Ambient);
			mMaskColor |= mAmbient.getType();
		}
		else if (mm.diffuse != null) {
			mDiffuse = new GLColorAttribute(mm.diffuse, GLColorAttribute.Diffuse);
			mMaskColor |= mDiffuse.getType();
		}
		else if (mm.emissive != null) {
			mEmissive = new GLColorAttribute(mm.emissive, GLColorAttribute.Emissive);
			mMaskColor |= mEmissive.getType();
		}
		else if (mm.reflection != null){
			mReflection = new GLColorAttribute(mm.reflection, GLColorAttribute.Reflection);
			mMaskColor |= mReflection.getType();
		}
		else if (mm.specular != null) {
			mSpecular = new GLColorAttribute(mm.specular, GLColorAttribute.Specular);
			mMaskColor |= mSpecular.getType();
		}
		if (mm.textures != null) {
			for (int i = 0; i < mm.textures.size; i++){
				GLTextureAttribute temp = new GLTextureAttribute(mm.textures.get(i));
				mTextures.add(temp);
				mMaskTexture |= temp.getType();
			}
		}
	}
}
