package net.steel3d.util;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class Object3 {

	public Transform transform;
	public List<Object3> components = new ArrayList<Object3>();
	
	public Object3(Transform transform){
		this.transform = transform;
	}
	
	public void update(){
		for (int i = 0; i < components.size(); i++) {
			components.get(i).update();
		}
	}
	
	public void renderCore(){
		GL11.glPushMatrix();
		GL11.glTranslatef(transform.position.x, transform.position.y, transform.position.z);
		GL11.glScalef(transform.scale.x, transform.scale.y, transform.scale.z);
		GL11.glRotatef(transform.rotation.x, 1, 0, 0);
		GL11.glRotatef(transform.rotation.y, 0, 1, 0);
		GL11.glRotatef(transform.rotation.z, 0, 0, 1);
		render();
		GL11.glPopMatrix();
	}
	public void render(){
		for (int i = 0; i < components.size(); i++) {
			components.get(i).render();
		}
	}
	
}
