package net.steel3d.physics;

import java.util.ArrayList;
import java.util.List;

public class Physics {

	public List<Rigidbody> objects = new ArrayList<Rigidbody>();
	
	public void update(){
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).update();
			objects.get(i).colliding = false;
			for(int j = 0; j < objects.size(); j++){
				if(i != j && objects.get(i).collider.intersects(objects.get(j).collider)){
					objects.get(i).colliding = true;
				}
			}
		}
	}
}
