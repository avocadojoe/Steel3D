package net.steel3d.util;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import net.steel3d.graphics.Face;
import net.steel3d.graphics.Mesh;

public class MeshRenderer extends Object3{

	public Mesh mesh;
	
	public MeshRenderer(Mesh mesh,Transform transform) {
		super(transform);
		this.mesh = mesh;
	}

	public void render(){
		super.render();
		Mesh model = mesh;
		GL11.glPushMatrix();
		GL11.glTranslatef(transform.position.x, transform.position.y, transform.position.z);
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
	
}
