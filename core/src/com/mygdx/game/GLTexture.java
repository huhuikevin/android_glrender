package com.mygdx.game;

public class GLTexture  extends GLAttribute{
	String mId;
	String mFilename;
	int mUseage;
	float[] mUVTranslation = new float[2];
	float[] mUVScaling = new float[2];
	
	public GLTexture(String id, String filename, int useage, float[] uvt, float[] uvs){
		mId = id;
		mFilename = filename;
		mUseage = useage;
		mUVTranslation[0] = uvt[0];
		mUVTranslation[1] = uvt[1];
		mUVScaling[0] = uvs[0];
		mUVScaling[1] = uvs[1];
	}
	public GLTexture(String id, String filename, int useage, float uvtx, float uvty, float uvsx, float uvsy){
		mId = id;
		mFilename = filename;
		mUseage = useage;
		mUVTranslation[0] = uvtx;
		mUVTranslation[1] = uvty;
		mUVScaling[0] = uvsx;
		mUVScaling[1] = uvsy;
	}
	public GLTexture(){}
}
