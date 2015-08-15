package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider;
public class MyGdxGame extends ApplicationAdapter {
	AssetManager assets; 
	boolean loading = false;
	ModelBatch modelBatch;
	ModelBatch shadowBatch;
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public Environment lights;
	public ModelInstance ship;
	public ModelInstance space;
	public static boolean shadows = true;
	public Array<ModelInstance> instances = new Array<ModelInstance>(); 
	Logger log = new Logger("MyGdxGame", Logger.DEBUG);
	@Override
	public void create () {
		lights = new Environment();
		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.f));
		lights.add(new DirectionalLight().set(1f, 1f, 1f, 0, 0, 0));
		
		modelBatch = new ModelBatch();
		cam = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0f, 0f, 0f);
		cam.lookAt(0, 0, -1f);
		cam.near = 0.1f;
		cam.far = 30000;
		cam.update();
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
		
		//batch = new SpriteBatch();
		//assets = new AssetManager();
		//assets.load("data/sp.g3dj", Model.class);  
		//loading = true;
		String tf = "data/sp.g3dj";
		//String tf = "data/invaders.g3dj";
		String filename = tf.replaceAll("\\\\", "/");
		//InternalFileHandleResolver resolver = new InternalFileHandleResolver();
		//ModelData md = new G3dModelLoader(new JsonReader(), resolver).loadModelData(resolver.resolve(filename)) ;
		log.info("===xxxxxxxxxxxxxxxxxxxxxxxx===");
		Model3D m3d = new Model3D(filename);
	}

	private void doneLoading () {
		ModelInstance model = new ModelInstance(assets.get("data/file.obj", Model.class));
		
		model.calculateTransforms();
		instances.add(model);
		loading = false;
		log.info("doneloading.....");
	}	
	

	private void doneLoading2 () {
		Model model = assets.get("data/sp.g3dj", Model.class);
		log.info("xxxxxxxx"+model.nodes.size);
		for (int i = 0; i < model.nodes.size; i++) {
			String id = model.nodes.get(i).id;
			ModelInstance instance = new ModelInstance(model, id);
			Node node = instance.getNode(id);

			instance.transform.set(node.globalTransform);
			node.translation.set(0, 0, 0);
			node.scale.set(1, 1, 1);
			node.rotation.idt();
			instance.calculateTransforms();

			if (id.equals("space")) {
				space = instance;
				continue;
			}
			if (id.equals("ship"))
				ship = instance;
			
			instances.add(instance);

		}

		loading = false;
	}	
	
	@Override
	public void render () {
		
        if (loading && assets.update())  
            doneLoading2();  
        camController.update(); 
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);		
        modelBatch.begin(cam);  
        for (ModelInstance instance : instances) { 
            modelBatch.render(instance);
            //log.info("render instance");
        }
        if (space != null) modelBatch.render(space);
        modelBatch.end();  
	}
	
    @Override  
    public void dispose () { 
    	if (modelBatch != null)
    		modelBatch.dispose();
    	if (instances != null)
    		instances.clear();
    	if (assets != null)
    		assets.dispose();  
    }  

    @Override  
    public void resume () {  
    }  

    @Override  
    public void resize (int width, int height) {  
    }  

    @Override  
    public void pause () {  
    }  	
}
