package com.mygdx.game;

public class ShaderProgram {
	/** default name for position attributes **/
	public static final String POSITION_ATTRIBUTE = "a_position";
	/** default name for normal attributes **/
	public static final String NORMAL_ATTRIBUTE = "a_normal";
	/** default name for color attributes **/
	public static final String COLOR_ATTRIBUTE = "a_color";
	/** default name for texcoords attributes, append texture unit number **/
	public static final String TEXCOORD_ATTRIBUTE = "a_texCoord";
	/** default name for tangent attribute **/
	public static final String TANGENT_ATTRIBUTE = "a_tangent";
	/** default name for binormal attribute **/
	public static final String BINORMAL_ATTRIBUTE = "a_binormal";
	
	public static final String SHINIESS_UNIFORM="u_shininess";
	public static final String OPACITY_UNIFORM="u_opacity";
	public static final String DIFFUSE_UNIFORM="u_diffuseColor";
	public static final String SPECULAR_UNIFORM = "u_specularColor";
	public static final String EMISSIVE_UNIFORM="u_emissiveColor";
	public static final String REFLECTION_UNIFORM="u_reflectionColor";

}
