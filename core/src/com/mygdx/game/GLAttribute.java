package com.mygdx.game;
import com.badlogic.gdx.utils.Array;
public class GLAttribute {
	private final static Array<String> types = new Array<String>();
	/** @return The ID of the specified attribute type, or zero if not available */
	public final static long getAttributeType (final String alias) {
		for (int i = 0; i < types.size; i++)
			if (types.get(i).compareTo(alias) == 0) return 1L << i;
		return 0;
	}

	/** @return The alias of the specified attribute type, or null if not available. */
	public final static String getAttributeAlias (final long type) {
		int idx = -1;
		while (type != 0 && ++idx < 63 && (((type >> idx) & 1) == 0))
			;
		return (idx >= 0 && idx < types.size) ? types.get(idx) : null;
	}

	/** Call this method to register a custom attribute type, see the wiki for an example. If the alias already exists, then that ID
	 * will be reused. The alias should be unambiguously and will by default be returned by the call to {@link #toString()}.
	 * @param alias The alias of the type to register, must be different for each dirrect type, will be used for debugging
	 * @return the ID of the newly registered type, or the ID of the existing type if the alias was already registered */
	protected final static long register (final String alias) {
		long result = getAttributeType(alias);
		if (result > 0) return result;
		types.add(alias);
		return 1L << (types.size - 1);
	}
	protected long mType;
	
	public GLAttribute(){}
	public GLAttribute(long t){
		mType = t;
	}
	public long getType(){
		return mType;
	}
}
