package net.steel3d.examples;


import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import net.steel3d.core.Game;
import net.steel3d.graphics.Graphics;
import net.steel3d.util.ImageLoader;

/**
 * 
 * main class must extend net.steel3d.core.Game
 *
 */
public class Example extends Game{


	public Example() {
		super("Example Program",//project name
		"");//project version
	}
	
	/**
	 * defines a method
	 */
    public Texture sand;


	/**
	 * method called on start
	 */
	public void init() {	
		sand = ImageLoader.loadImage("net/steel3d/examples/sand.png");
		
	}
	
	/**
	 * called in game loop NOT FOR RENDERING*/
	public void update(int delta) {
		
		System.out.println(delta);
		
		//rotates the camera
		if(Mouse.isButtonDown(0)){
			xrot-=Mouse.getDY()/5;
			yrot+=Mouse.getDX()/5;

		}
	}
	
	/**
	 * define a Graphics instance (net.torchlight3d.graphics.Graphics)
	 */
	public Graphics g = new Graphics();
	

	/**
	 * Render method. Called in game loop 
	 */
	float xrot = 0;
	float yrot = 0;

	public void render() {
		g.rotateX(xrot);		

		g.rotateY(yrot);
		
		g.translate(new Vector3f(0,-6,0));
		
		g.drawTexturedCube(0,0, 0, 1, 1, 1, sand);
	}



	/**
	 * main method
	 * @param args
	 */
	public static void main(String[]args){
		Example example = new Example();
		example.run(new DisplayMode(1080,600));
	}

	public void renderUI() {
		
	}
}
