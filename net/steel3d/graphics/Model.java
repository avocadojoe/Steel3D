package net.steel3d.graphics;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class Model {

	public List<Vector3f> vectors = new ArrayList<Vector3f>();
	public List<Vector3f> vectorNormals = new ArrayList<Vector3f>();
	public List<Face> faces = new ArrayList<Face>();

	public Model(){
		
	}
	
}
