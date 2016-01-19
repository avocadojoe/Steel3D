package net.steel3d.physics;

import org.lwjgl.util.vector.Vector3f;

public class AABB {

	public Vector3f position;
	public Vector3f size;
	
	public AABB(Vector3f position, Vector3f size){
		this.position = position;
		this.size = position;
	}
	
	public boolean intersects(AABB aabb){
		 float tw = this.size.x;
		 float th = this.size.y;
		 float td = this.size.z;

		 float rw = aabb.size.x;
		 float rh = aabb.size.y;
		 float rd = aabb.size.z;

	        if (rw <= 0 || rh <= 0 || rd <= 0 || tw <= 0 || th <= 0 || td <= 0) {
	            return false;
	        }
	        float tx = this.position.x;
	        float ty = this.position.y;
	        float tz = this.position.z;

	        float rx = aabb.position.x;
	        float ry = aabb.position.y;
	        float rz = aabb.position.z;

	        rw += rx;
	        rh += ry;
	        rd += rz;
	        tw += tx;
	        th += ty;
	        td += tz;
	        return ((rw < rx || rw > tx) &&
	                (rh < ry || rh > ty) &&
	                (rd < rz || rd > tz) &&
	                (tw < tx || tw > rx) &&
	                (th < ty || th > ry) && 
	                (td < tz || td > rz));
	}
	
	public void center(Vector3f coords){
		this.position.x = coords.x - (this.size.x/2);
		this.position.y = coords.y - (this.size.y/2);
		this.position.z = coords.z - (this.size.z/2);
	}

}
