package com.mygdx.game.android;

public interface Shader {
	/** Initializes the Shader, must be called before the Shader can be used. This typically compiles a {@link ShaderProgram},
	 * fetches uniform locations and performs other preparations for usage of the Shader. */
	void init ();

	/** Compare this shader against the other, used for sorting, light weight shaders are rendered first. */
	//int compareTo (Shader other); // TODO: probably better to add some weight value to sort on

	/** Checks whether this shader is intended to render the {@link Renderable}. Use this to make sure a call to the
	 * {@link #render(Renderable)} method will succeed. This is expected to be a fast, non-blocking method. Note that this method
	 * will only return true if it is intended to be used. Even when it returns false the Shader might still be capable of
	 * rendering, but it's not preferred to do so.
	 * @param instance The renderable to check against this shader.
	 * @return true if this shader is intended to render the {@link Renderable}, false otherwise. */
	boolean canRender (Renderable instance);

	/** Initializes the context for exclusive rendering by this shader. Use the {@link #render(Renderable)} method to render a
	 * {@link Renderable}. When done rendering the {@link #end()} method must be called.
	 * @param camera The camera to use when rendering
	 * @param context The context to be used, which must be exclusive available for the shader until the call to the {@link #end()}
	 *           method. */
	void begin ();

	/** Renders the {@link Renderable}, must be called between {@link #begin(Camera, RenderContext)} and {@link #end()}. The Shader
	 * instance might not be able to render every type of {@link Renderable}s. Use the {@link #canRender(Renderable)} method to
	 * check if the Shader is capable of rendering a specific {@link Renderable}.
	 * @param renderable The renderable to render, all required fields (e.g. {@link Renderable#mesh}, {@link Renderable#material}
	 *           and others) must be set. The {@link Renderable#shader} field will be ignored. */
	void render (final Renderable renderable);

	/** Cleanup the context so other shaders can render. Must be called when done rendering using the {@link #render(Renderable)}
	 * method, which must be preceded by a call to {@link #begin(Camera, RenderContext)}. After a call to this method an call to
	 * the {@link #render(Renderable)} method will fail until the {@link #begin(Camera, RenderContext)} is called. */
	void end ();
}
