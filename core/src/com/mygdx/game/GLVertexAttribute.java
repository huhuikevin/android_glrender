package com.mygdx.game;
import com.badlogic.gdx.graphics.*;

public class GLVertexAttribute extends GLAttribute{
	public static final class GLUsage {
		public static final int Position = 1;
		public static final int ColorUnpacked = 2;
		public static final int ColorPacked = 4;
		public static final int Normal = 8;
		public static final int TextureCoordinates = 16;
		public static final int Generic = 32;
		public static final int BoneWeight = 64;
		public static final int Tangent = 128;
		public static final int BiNormal = 256;
	}		
	int usage;
	int numComponents;
	boolean normalized;
	int type;
    int offset;	
	int unit;
	int usageIndex;
	public String alias;
	public static Debug mDebug = new Debug();
	//GlslVar mGlslvar;
	
	public GLVertexAttribute (int usage, int numComponents, String alias) {
		this(usage, numComponents, alias, 0);
	}

	/** Constructs a new VertexAttribute.
	 * 
	 * @param usage the usage, used for the fixed function pipeline. Generic attributes are not supported in the fixed function
	 *           pipeline.
	 * @param numComponents the number of components of this attribute, must be between 1 and 4.
	 * @param alias the alias used in a shader for this attribute. Can be changed after construction.
	 * @param index unit/index of the attribute, used for boneweights and texture coordinates. */
	public GLVertexAttribute (int usage, int numComponents, String alias, int index) {
		this(usage, numComponents, usage == GLUsage.ColorPacked ? GL20.GL_UNSIGNED_BYTE : GL20.GL_FLOAT, 
				usage == GLUsage.ColorPacked, alias, index);
	}
	
	private GLVertexAttribute (int usage, int numComponents, int type, boolean normalized, String alias) {
		this(usage, numComponents, type, normalized, alias, 0);
	}
	
	private GLVertexAttribute (int usage, int numComponents, int type, boolean normalized, String alias, int index) {
		super((long)usage);
		this.usage = usage;
		this.numComponents = numComponents;
		this.type = type;
		this.normalized = normalized;
		this.alias = alias;
		this.unit = index;
		this.usageIndex = Integer.numberOfTrailingZeros(usage);	
		//mGlslvar = new GlslVar(alias);
		
	}	
	public static GLVertexAttribute Position () {
		mDebug.debug("Position");
		return new GLVertexAttribute(GLUsage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE);
	}

	public static GLVertexAttribute TexCoords (int unit) {
		mDebug.debug("TexCoords"+unit);
		return new GLVertexAttribute(GLUsage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE + unit, unit);
	}

	public static GLVertexAttribute Normal () {
		mDebug.debug("Normal");
		return new GLVertexAttribute(GLUsage.Normal, 3, ShaderProgram.NORMAL_ATTRIBUTE);
	}
	
	public static GLVertexAttribute ColorPacked () {
		mDebug.debug("ColorPacked");
		return new GLVertexAttribute(GLUsage.ColorPacked, 4, GL20.GL_UNSIGNED_BYTE, true, ShaderProgram.COLOR_ATTRIBUTE);
	}

	public static GLVertexAttribute ColorUnpacked () {
		mDebug.debug("ColorUnpacked");
		return new GLVertexAttribute(GLUsage.ColorUnpacked, 4, GL20.GL_FLOAT, false, ShaderProgram.COLOR_ATTRIBUTE);
	}

	public static GLVertexAttribute Tangent () {
		return new GLVertexAttribute(GLUsage.Tangent, 3, ShaderProgram.TANGENT_ATTRIBUTE);
	}

	public static GLVertexAttribute Binormal () {
		return new GLVertexAttribute(GLUsage.BiNormal, 3, ShaderProgram.BINORMAL_ATTRIBUTE);
	}

	public static GLVertexAttribute BoneWeight (int unit) {
		return new GLVertexAttribute(GLUsage.BoneWeight, 2, "a_boneWeight" + unit, unit);
	}
	
}
