package net.steel3d.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;


public class Graphics {


	public void rotateX(float rot){
		GL11.glRotatef(rot, 1, 0, 0);
	}
	
	public void rotateY(float rot){
		GL11.glRotatef(rot, 0, 1, 0);
	}
	
	public void rotateZ(float rot){
		GL11.glRotatef(rot, 0, 0, 1);
	}
	
	public void rotate(Vector3f rot){
		GL11.glRotatef(rot.x, 1, 0, 0);
		GL11.glRotatef(rot.y, 0, 1, 0);
		GL11.glRotatef(rot.z, 0, 0, 1);
	}
	

	/**
	 * translates to specified coordinates
	 * @param translation
	 */
	public void translate(Vector3f translation){
		GL11.glTranslatef(translation.x, translation.y, translation.z);
	}
	
	/**
	 * draws a light at specified position
	 * @param position
	 */
	public void light(Vector3f position){
		float[] pos = new float[]{position.x,position.y,position.z,0.0F};
		FloatBuffer b = BufferUtils.createFloatBuffer(4);
		b.put(pos);


        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION,(FloatBuffer)b.flip());  
	}
	
	
	/**
	 * draws a colored model at specified coordinates
	 * @param x
	 * @param y
	 * @param z
	 * @param model
	 */
	public void drawModel(float x,float y, float z,Model model){
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glBegin(GL11.GL_TRIANGLES);
		for(Face face:model.faces){
			
			Vector3f n1 = model.vectorNormals.get((int)face.normal.x -1);
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			Vector3f v1 = model.vectors.get((int)face.vertex.x -1);
			GL11.glVertex3f(v1.x, v1.y, v1.z);
			
			Vector3f n2 = model.vectorNormals.get((int)face.normal.y -1);
			GL11.glNormal3f(n2.x, n2.y, n2.z);
			Vector3f v2 = model.vectors.get((int)face.vertex.y -1);
			GL11.glVertex3f(v2.x, v2.y, v2.z);
			
			Vector3f n3 = model.vectorNormals.get((int)face.normal.z -1);
			GL11.glNormal3f(n3.x, n3.y, n3.z);
			Vector3f v3 = model.vectors.get((int)face.vertex.z -1);
			GL11.glVertex3f(v3.x, v3.y, v3.z);
			
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	/**
	 * draws a textured model at the specified coordinates
	 * @param x
	 * @param y
	 * @param z
	 * @param model
	 * @param texture
	 */
	public void drawModel(float x,float y, float z,Model model,Texture texture){
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		texture.bind();
		GL11.glBegin(GL11.GL_TRIANGLES);
		for(Face face:model.faces){
			Vector3f n1 = model.vectorNormals.get((int)face.normal.x -1);
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			Vector3f v1 = model.vectors.get((int)face.vertex.x -1);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(v1.x, v1.y, v1.z);
			
			Vector3f n2 = model.vectorNormals.get((int)face.normal.y -1);
			GL11.glNormal3f(n2.x, n2.y, n2.z);
			Vector3f v2 = model.vectors.get((int)face.vertex.y -1);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex3f(v2.x, v2.y, v2.z);
			
			Vector3f n3 = model.vectorNormals.get((int)face.normal.z -1);
			GL11.glNormal3f(n3.x, n3.y, n3.z);
			Vector3f v3 = model.vectors.get((int)face.vertex.z -1);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(v3.x, v3.y, v3.z);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	/**
	 * draws a textured cube at specified coordinates
	 * @param x
	 * @param y
	 * @param z
	 * @param width
	 * @param height
	 * @param depth
	 * @param texture
	 */
	public void drawTexturedCube(float x,float y,float z,float width, float height,float depth,Texture texture){

		Color.white.bind();
		float w = width/2;
		float h = height/2;
		float d = depth/2;
		

		texture.bind();
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST );
		GL11.glBegin(GL11.GL_QUADS); 
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x+w, y+h, z+-d);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(x+-w, y+h, z+-d);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(x+-w, y+h, z+d);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(x+w, y+h, z+d); 
		
		GL11.glEnd();
		texture.bind();
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST );
		GL11.glBegin(GL11.GL_QUADS); 

		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x+w, y+-h, z+d);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(x+-w, y+-h, z+d);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(x+-w, y+-h, z+-d);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(x+w, y+-h, z+-d); 

		GL11.glEnd();
		texture.bind();
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST );
		GL11.glBegin(GL11.GL_QUADS); 


		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x+w, y+h, z+d);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(x+-w, y+h, z+d);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(x+-w, y+-h, z+d); 
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(x+w, y+-h, z+d); 
		
		GL11.glEnd();
		texture.bind();
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST );
		GL11.glBegin(GL11.GL_QUADS); 

		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x+w, y+-h, z+-d);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(x+-w, y+-h, z+-d);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(x+-w, y+h, z+-d); 
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(x+w, y+h, z+-d); 
		
		GL11.glEnd();
		texture.bind();
		GL11.glTexParameteri( GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST );
		GL11.glBegin(GL11.GL_QUADS); 



		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x+-w, y+h, z+d);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(x+-w, y+h, z+-d); 
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(x+-w, y+-h, z+-d);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(x+-w, y+-h, z+d);
		
		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS); 


		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x+w, y+h, z+-d);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(x+w, y+h, z+d);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(x+w, y+-h, z+d); 
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(x+w, y+-h, z+-d); 
		
		
		
		GL11.glEnd();

	}
	
	/**
	 * draws a colored cube at specified coordinates
	 * @param x
	 * @param y
	 * @param z
	 * @param width
	 * @param height
	 * @param depth
	 * @param color
	 */
	public void drawColoredCube(float x,float y,float z,float width, float height,float depth,Color color){

		float w = width/2;
		float h = height/2;
		float d = depth/2;

		GL11.glBegin(GL11.GL_QUADS); 
		color.bind();
		GL11.glVertex3f(x+w, y+h, z+-d);
		GL11.glVertex3f(x+-w, y+h, z+-d);
		GL11.glVertex3f(x+-w, y+h, z+d); 
		GL11.glVertex3f(x+w, y+h, z+d); 

		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS); 

		GL11.glVertex3f(x+w, y+-h, z+d);
		GL11.glVertex3f(x+-w, y+-h, z+d); 
		GL11.glVertex3f(x+-w, y+-h, z+-d); 
		GL11.glVertex3f(x+w, y+-h, z+-d); 

		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS); 
		
		GL11.glVertex3f(x+w,y+ h, z+d); 
		GL11.glVertex3f(x+-w, y+h, z+d);
		GL11.glVertex3f(x+-w, y+-h,z+ d); 
		GL11.glVertex3f(x+w, y+-h, z+d);
		
		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS); 

		GL11.glVertex3f(x+w, y+-h, z+-d); 
		GL11.glVertex3f(x+-w, y+-h, z+-d); 
		GL11.glVertex3f(x+-w, y+h,z+ -d); 
		GL11.glVertex3f(x+w,  y+h,z+ -d); 
		
		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS); 

		GL11.glVertex3f(x+-w, y+ h,z+ d); 
		GL11.glVertex3f(x+-w, y+ h,z+ -d); 
		GL11.glVertex3f(x+-w, y+ -h,z+ -d);
		GL11.glVertex3f(x+-w,  y+-h,z+ d);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS); 


		GL11.glVertex3f(x+w, y+ h, z+-d);
		GL11.glVertex3f(x+w, y+ h, z+d); 
		GL11.glVertex3f(x+w,  y+-h,z+ d); 
		GL11.glVertex3f(x+w, y+-h, z+-d); 
		GL11.glEnd();
	}
}
