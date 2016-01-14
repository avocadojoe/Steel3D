package net.steel3d.util;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ImageLoader {

	public static Texture loadImage(String name){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(name));
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return texture;
	}
}
