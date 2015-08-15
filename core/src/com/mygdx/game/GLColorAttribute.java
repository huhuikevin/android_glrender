package com.mygdx.game;
//import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.Color;
public class GLColorAttribute  extends GLColor{
	public final static String DiffuseAlias = "diffuseColor";
	public final static long Diffuse = register(DiffuseAlias);
	public final static String SpecularAlias = "specularColor";
	public final static long Specular = register(SpecularAlias);
	public final static String AmbientAlias = "ambientColor";
	public static final long Ambient = register(AmbientAlias);
	public final static String EmissiveAlias = "emissiveColor";
	public static final long Emissive = register(EmissiveAlias);
	public final static String ReflectionAlias = "reflectionColor";
	public static final long Reflection = register(ReflectionAlias);
	public final static String AmbientLightAlias = "ambientLightColor";
	public static final long AmbientLight = register(AmbientLightAlias);
	public final static String FogAlias = "fogColor";
	public static final long Fog = register(FogAlias);
	
	protected static long Mask = Ambient | Diffuse | Specular | Emissive | Reflection | AmbientLight | Fog;
	
	public GLColorAttribute(Color c, long type){
		super(c.r, c.g, c.b, c.a, type);
	}
}
