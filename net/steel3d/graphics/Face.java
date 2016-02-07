package net.steel3d.graphics;

import org.lwjgl.util.vector.Vector4f;

import net.steel3d.util.Material;

public class Face {

	public Vector4f vertex = new Vector4f();
	public Vector4f normal = new Vector4f();
	public Material mat;

	public Face(Vector4f vertex,Vector4f normal,Material mat){
		this.vertex = vertex;
		this.normal = normal;
		this.mat = mat;
	}
}
