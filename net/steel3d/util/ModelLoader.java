package net.steel3d.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.lwjgl.util.vector.Vector3f;

import net.steel3d.graphics.Face;
import net.steel3d.graphics.Model;

public class ModelLoader {

	public static Model loadModel(@SuppressWarnings("rawtypes") Class clazz,String file) throws FileNotFoundException, IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader( 
        		clazz.getResourceAsStream(file)));
		Model model = new Model();
		String line;
		while((line = reader.readLine())!=null){
			if(line.startsWith("v ")){
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				model.vectors.add(new Vector3f(x,y,z));
			}else if(line.startsWith("vn ")){
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[2]);
				float z = Float.valueOf(line.split(" ")[3]);
				model.vectorNormals.add(new Vector3f(x,y,z));
			}else if(line.startsWith("f")){
				float x = Float.valueOf(line.split(" ")[1].split("/")[0]);
				float y = Float.valueOf(line.split(" ")[2].split("/")[0]);
				float z = Float.valueOf(line.split(" ")[3].split("/")[0]);
				Vector3f vertexs = new Vector3f(x,y,z);
				
				float x1 = Float.valueOf(line.split(" ")[1].split("/")[2]);
				float y1 = Float.valueOf(line.split(" ")[2].split("/")[2]);
				float z1 = Float.valueOf(line.split(" ")[3].split("/")[2]);
				Vector3f normals = new Vector3f(x1,y1,z1);
				
				Face face = new Face(vertexs, normals);
				model.faces.add(face);
			}
		}
		return model;
	}
}
