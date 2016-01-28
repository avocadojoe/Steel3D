package net.steel3d.core;


import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 * @author avocadojoe
 * steel 3d is an open-source java game engine
 * work in progress
 * 
 */
public abstract class Game {

	/**
	 * the view distance Note: will only work if initialized inside the constructor
	 */
	public float viewDistance = 1000;
	
	/**
	 * the FOV Note: will only work if initialized inside the constructor
	 */
	public float fov = 45;

	
	/**
	 * the title of the game
	 */
	public String title = "Game";
	
	public Game(String title,String version){
		this.title = title+" "+version;
	}
	
	long lastFrameTime;
	
	/**
	 * runs the program
	 * @param display mode
	 */
	public void run(DisplayMode mode) {

		createWindow(mode);
		getDelta(); 
		initGL();
		init();
		
		while (!Display.isCloseRequested()) {
			update(getDelta());
			Display.sync(20000);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // Clear The Screen And The Depth Buffer
			GL11.glLoadIdentity(); 
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0f, 0.0f, -6.0f);
			render();
			GL11.glPopMatrix();
			
			set2D();
			renderUI();
			set3D();
			Display.update();
		}
		
		destroy();
	}
	
	private void initGL() {
	
		int width = Display.getDisplayMode().getWidth();
		int height = Display.getDisplayMode().getHeight();
	
		
		GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK,GL11.GL_AMBIENT_AND_DIFFUSE);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glViewport(0, 0, width, height); 
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	
	        
		IntBuffer lights = BufferUtils.createIntBuffer(16);
        GL11.glGetInteger(GL11.GL_MAX_LIGHTS, lights);
        GL11.glEnable(GL11.GL_LIGHT_MODEL_LOCAL_VIEWER);
        GL11.glEnable(GL11.GL_LIGHT1);
        GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glMatrixMode(GL11.GL_PROJECTION); 
		GL11.glLoadIdentity();
		GLU.gluPerspective(fov, ((float) width / (float) height), 2.0f, viewDistance); // Calculate The Aspect Ratio Of The Window
		GL11.glMatrixMode(GL11.GL_MODELVIEW); 

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
		GL11.glClearDepth(1.0f); 
		GL11.glEnable(GL11.GL_DEPTH_TEST); 
		GL11.glDepthFunc(GL11.GL_LEQUAL); 
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST); 
		
	}
	
	public abstract  void update(int delta);


	public abstract  void render();
	public abstract  void renderUI();

	public abstract  void init();


	/**
	 * 
	 * @return the delta time
	 */
	public int getDelta() {
	    long time = (Sys.getTime() * 1000) / Sys.getTimerResolution();
	    int delta = (int) (time - lastFrameTime);
	    lastFrameTime = time;
	    return delta;
	}

	private void createWindow(DisplayMode mode) {
		try {
			Display.setDisplayMode(mode);
			Display.setVSyncEnabled(true);
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			Sys.alert("Error", "Error "+System.lineSeparator() + e.getMessage());
			System.exit(0);
		}
	}
	
	private void destroy() {
		Display.destroy();
	}
	 	private void set2D() {
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glMatrixMode(GL11.GL_PROJECTION);
	        GL11.glPushMatrix();
	        GL11.glLoadIdentity();
	        GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	        GL11.glPushMatrix();
	        GL11.glLoadIdentity();
	    }
	 
	 	private void set3D() {
	        GL11.glMatrixMode(GL11.GL_PROJECTION);
	        GL11.glPopMatrix();
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	        GL11.glPopMatrix();
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_LIGHTING);
	    }
}
