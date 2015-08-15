package com.mygdx.game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.*;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.model.data.*;
import com.badlogic.gdx.graphics.*;

public class ModelPart {
	String mId;
	private FloatBuffer mVertexData;
	private GLVertexAttribute[] attribute;
	private int mVertexSize;
	int mVertexStrip;
	private Array<ModelFace> mFaces = new Array<ModelFace>();
	private Array<GLDrawable> mDraws = new Array<GLDrawable>();
	//ShaderProgram shader = new ShaderProgram();
	private Debug mDebug = new Debug();
	long mMask;
	public ModelPart(ModelMesh mesh){
		mId = mesh.id;
		mVertexSize = mesh.vertices.length;
		//copy vertex data, include Position, texCoords, normals, and etc.
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(mVertexSize * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		mVertexData = byteBuf.asFloatBuffer();
		mVertexData.put(mesh.vertices);
		mVertexData.position(0);
		
		mVertexStrip = calculateOffsets(mesh.attributes);
		AllocAttribute(mesh);
		AllocModelFace(mesh);
		

	}
	public Array<GLDrawable> CalcDraws(Map<String , Array<ModlePMNode>> nodes, Map<String, GLMaterial> materials){
		for (ModelFace mf : mFaces){
			Array<ModlePMNode> am = nodes.get(mf.mId);
			if (am == null){
				mDebug.debug("ModelFace "+mf.mId+" not in used in nodes");
			}
			for (ModlePMNode pmn: am){
				GLMaterial m = materials.get(pmn.mMaterialId);
				GLDrawable d = new GLDrawable(mf, m, pmn.mTransform);
				mDebug.debug("add drawable "+pmn.mId+" xxxxx materials "+m.mId);
				mDraws.add(d);
			}
		}
		return mDraws;
	}
	private void AllocModelFace(ModelMesh mesh){
		mDebug.debug("AllocModelFace");
		for (int i = 0; i < mesh.parts.length; i++){
			ModelFace mf = new ModelFace(mesh.parts[i]);
			mFaces.add(mf);
			mDebug.debug("AllocModelFace "+mf.mId);
		}
	}
	private void AllocAttribute(ModelMesh mesh){
		attribute = new GLVertexAttribute[mesh.attributes.length];
		for (int i = 0; i < attribute.length; i++){
			if (mesh.attributes[i].usage == Usage.Position){
				attribute[i] = GLVertexAttribute.Position();
				//shader.addGlslAttri(ShaderProgram.POSITION_ATTRIBUTE);
			}
			else if (mesh.attributes[i].usage == Usage.TextureCoordinates){
				attribute[i] = GLVertexAttribute.TexCoords(mesh.attributes[i].unit);
				//shader.addGlslAttri(ShaderProgram.TEXCOORD_ATTRIBUTE);
			}
			else if (mesh.attributes[i].usage == Usage.Normal){
				attribute[i] = GLVertexAttribute.Normal();
				//shader.addGlslAttri(ShaderProgram.TEXCOORD_ATTRIBUTE);
			}
			else if (mesh.attributes[i].usage == Usage.ColorPacked){
				attribute[i] = GLVertexAttribute.ColorPacked();
			}
			else if (mesh.attributes[i].usage == Usage.ColorUnpacked){
				attribute[i] = GLVertexAttribute.ColorUnpacked();
			}
			else if (mesh.attributes[i].usage == Usage.Tangent){
				attribute[i] = GLVertexAttribute.Tangent();
			}
			else if (mesh.attributes[i].usage == Usage.BiNormal){
				attribute[i] = GLVertexAttribute.Binormal();
			}
			else if (mesh.attributes[i].usage == Usage.BoneWeight){
				attribute[i] = GLVertexAttribute.BoneWeight(mesh.attributes[i].unit);
			}
			mMask |= attribute[i].getType();
		}		
		
	}
	private int calculateOffsets (VertexAttribute[] attributes) {
		int count = 0;
		for (int i = 0; i < attributes.length; i++) {
			VertexAttribute attribute = attributes[i];
			attribute.offset = count;
			if (attribute.usage == VertexAttributes.Usage.ColorPacked)
				count += 4;
			else
				count += 4 * attribute.numComponents;
		}

		return count;
	}	
}
