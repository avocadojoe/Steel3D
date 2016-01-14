package net.steel3d.graphics;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class UI {
	public void drawTexture(Texture texture,float x,float y){
		drawTexture(texture, x, y, texture.getWidth(), texture.getHeight(),true);
	}
	public void drawTexture(Texture texture,float x,float y,float w,float h){
		drawTexture(texture, x, y, w, h,true);
	}
	
	public void drawTexture(Texture texture,float x,float y,float w,float h,boolean mipmaps){
		texture.bind();
		if(!mipmaps){
			GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST );
		}
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
				GL11.glVertex2f(x, y);
				
			GL11.glTexCoord2f(1, 0);
				GL11.glVertex2f(x+w, y);
				
			GL11.glTexCoord2f(1, 1);
				GL11.glVertex2f(x+w, y+h);
	
			GL11.glTexCoord2f(0, 1);
				GL11.glVertex2f(x, y+h);
		GL11.glEnd();
	}
}
