package com.mygdx.game;
import com.badlogic.gdx.graphics.g3d.model.data.*;
public class GLTextureAttribute extends GLTexture{
	public final static String DiffuseAlias = "diffuseTexture";
	public final static long Diffuse = register(DiffuseAlias);
	public final static String SpecularAlias = "specularTexture";
	public final static long Specular = register(SpecularAlias);
	public final static String BumpAlias = "bumpTexture";
	public final static long Bump = register(BumpAlias);
	public final static String NormalAlias = "normalTexture";
	public final static long Normal = register(NormalAlias);
	public final static String AmbientAlias = "ambientTexture";
	public final static long Ambient = register(AmbientAlias);
	public final static String EmissiveAlias = "emissiveTexture";
	public final static long Emissive = register(EmissiveAlias);
	public final static String ReflectionAlias = "reflectionTexture";
	public final static long Reflection = register(ReflectionAlias);
	
	protected static long Mask = Diffuse | Specular | Bump | Normal | Ambient | Emissive | Reflection;
	
	public GLTextureAttribute(ModelTexture mt){
		super(mt.id, mt.fileName, mt.usage, mt.uvTranslation.x, mt.uvTranslation.y, mt.uvScaling.x, mt.uvScaling.y);
		switch (mt.usage) {
		case ModelTexture.USAGE_DIFFUSE:
			mType = Diffuse;
			break;
		case ModelTexture.USAGE_SPECULAR:
			mType = Specular;
			break;
		case ModelTexture.USAGE_BUMP:
			mType = Bump;
			break;
		case ModelTexture.USAGE_NORMAL:
			mType = Normal;
			break;
		case ModelTexture.USAGE_AMBIENT:
			mType = Ambient;
			break;
		case ModelTexture.USAGE_EMISSIVE:
			mType = Emissive;
			break;
		case ModelTexture.USAGE_REFLECTION:
			mType = Reflection;
			break;
		}		
	}
}
