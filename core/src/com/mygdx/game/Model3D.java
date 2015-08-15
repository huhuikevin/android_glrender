package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.UBJsonReader;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.graphics.g3d.model.data.*;

public class Model3D {
	ModelData mData;
	Array<ModelPart> mParts = new Array<ModelPart>();
	private Map<String, GLMaterial> mMaterialMaps = new HashMap<String, GLMaterial>();
	private Map<String , Array<ModlePMNode>> mNodes = new HashMap<String , Array<ModlePMNode>>();
	Debug debug = new Debug();
	
	public Model3D(String pathinternal){
		long prev =System.currentTimeMillis();
		LoadModelData(pathinternal);
		long curr = System.currentTimeMillis();
		debug.debug("LoadModelData time is "+(curr-prev)+"Ms");
		prev =System.currentTimeMillis();
		LoadMaterials();
		curr = System.currentTimeMillis();
		debug.debug("LoadMaterials time is "+(curr-prev)+"Ms");
		prev =System.currentTimeMillis();
		LoadNodes();
		curr = System.currentTimeMillis();
		debug.debug("LoadNodes time is "+(curr-prev)+"Ms");
		prev =System.currentTimeMillis();
		LoadModelParts();
		curr = System.currentTimeMillis();
		debug.debug("LoadModelParts time is "+(curr-prev)+"Ms");
	}
	private void LoadModelParts(){
		for (ModelMesh m : mData.meshes){
			ModelPart mp = new ModelPart(m);
			mParts.add(mp);
			mp.CalcDraws(mNodes, mMaterialMaps);
		}
	}
	private void LoadMaterials(){
		if (mData.materials == null)
			return;
		for (ModelMaterial mm:mData.materials){
			GLMaterial m = new GLMaterial(mm);
			mMaterialMaps.put(m.mId, m);
		}
	}
	private void LoadNodes(){
		for (ModelNode mn:mData.nodes){
			LoadNodes(mn, null);
		}
	}

	private void LoadNodes(ModelNode mn, ModlePMNode parent){
		ModlePMNode pmn = new ModlePMNode(mn);
		if (mn.parts != null) {
			for (ModelNodePart np: mn.parts){
				ModlePMNode nodep = new ModlePMNode(pmn, np.materialId, np.meshPartId);
				if (parent != null)
					nodep.SetMulMatrix(parent.mTransform);
				Array<ModlePMNode> am = mNodes.get(nodep.mMeshPartId);
				if (am == null){
					am = new Array<ModlePMNode>();
				}
				am.add(nodep);
				mNodes.put(nodep.mMeshPartId, am);
			}
		}
		if (mn.children != null){
			for (int i = 0; i < mn.children.length;i++){
				LoadNodes(mn.children[i], pmn);
			}					
		}				
	}	
	
	private void LoadModelData(String pathinternal){
		String filename = pathinternal.replaceAll("\\\\", "/");
		InternalFileHandleResolver resolver = new InternalFileHandleResolver();
		if(pathinternal.endsWith("g3dj"))
			mData = new G3dModelLoader(new JsonReader(), resolver).loadModelData(resolver.resolve(filename)) ;
		else if (pathinternal.endsWith("g3db"))
			mData = new G3dModelLoader(new UBJsonReader(), resolver).loadModelData(resolver.resolve(filename)) ;
		else 
			throw new GdxRuntimeException("file "+pathinternal+" is not supported");		
	}
}
