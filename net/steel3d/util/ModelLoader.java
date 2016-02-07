package net.steel3d.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import net.steel3d.graphics.Face;
import net.steel3d.graphics.Mesh;

public class ModelLoader {

	public static Mesh loadModel(@SuppressWarnings("rawtypes") Class clazz,String file) throws IOException, URISyntaxException{
		BufferedReader reader = new BufferedReader(new InputStreamReader( 
        		clazz.getResourceAsStream(file)));
		Mesh model = new Mesh();
		String line;
		String mtlFile = "";
		Material mat = null;
		List<Material> materials = new ArrayList<Material>();
		while((line = reader.readLine())!=null){
			
			if(line.startsWith("mtllib ")){
				mtlFile = line.substring(7);
				File objfileloc = new File(clazz.getResource(file).toURI());
				BufferedReader reader2 = new BufferedReader(new FileReader(objfileloc.getParent()+"/"+mtlFile));
				System.out.println(objfileloc.getParent());
				String line2 = "";
				while((line2 = reader2.readLine())!=null){
					if(line2.startsWith("newmtl ")){
						Material mtl = new Material(line2.substring(7));
						mtl.color = new Vector3f(1,1,1);
						materials.add(mtl);
					}
					if(line2.startsWith("map_Kd ")){
						if(materials.size() > 0){
							materials.get(materials.size()-1).tex = ImageLoader.loadImage(line2.substring(7));
						}
					}
				}
				reader2.close();
			}
			if(line.startsWith("usemtl ")){
				for(int i = 0; i < materials.size();i++){
					if(materials.get(i).s == line.substring(7)){
						mat = materials.get(i);
					}
				}
			}
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
				Vector4f vertexs = new Vector4f(x,y,z,0);
				float x1 = Float.valueOf(line.split(" ")[1].split("/")[2]);
				float y1 = Float.valueOf(line.split(" ")[2].split("/")[2]);
				float z1 = Float.valueOf(line.split(" ")[3].split("/")[2]);

				Vector4f normals = new Vector4f(x1,y1,z1,0);
				
				Face face = new Face(vertexs, normals,mat);
				model.faces.add(face);
			}
		}
		return model;
	}
}
