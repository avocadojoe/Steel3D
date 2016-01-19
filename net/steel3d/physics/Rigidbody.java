package net.steel3d.physics;

import org.lwjgl.util.vector.Vector3f;

import net.steel3d.util.Object3;
import net.steel3d.util.Transform;

public class Rigidbody extends Object3 {

	public float mass = 1;
	public float drag = 0.1F;
	public float gravity = 0.1F;
	public Transform now;
	public Transform old;
	public Vector3f acceleration;
	public Vector3f velocity;
	public boolean colliding = false;
	
	public AABB collider;
	
	public boolean[] freezePosition;
	public boolean[] freezeRotation;

	
	public Rigidbody(Transform transform){
		super(transform);
		now = transform;
		old = transform;
		acceleration = new Vector3f(0,0,0);
		velocity = new Vector3f(0,0,0);
		collider = new AABB(transform.position,transform.scale);
		collider.center(transform.position);
		freezePosition = new boolean[2];
		for(int i = 0; i < freezePosition.length;i++){
			freezePosition[i] = false;
		}
		
		freezeRotation = new boolean[2];
		for(int j = 0; j < freezeRotation.length;j++){
			freezeRotation[j] = false;
		}
	}
	
	public void update(){
		super.update();
		this.applyForce(new Vector3f(0,gravity,0));
		collider.center(this.transform.position);
		if(this.velocity.x<10||this.velocity.y<10||this.velocity.z<10 && !colliding){
			this.velocity.x += this.acceleration.x;
			this.velocity.y += this.acceleration.y;
			this.velocity.z += this.acceleration.z;
		}
		this.acceleration.x -= this.drag;
		this.acceleration.y -= this.drag;
		this.acceleration.z -= this.drag;
	
	}
	
	
	
	public void applyForce(Vector3f force){
		if(colliding){
		float forceX = force.x/this.mass;
		float forceY = force.y/this.mass;
		float forceZ = force.z/this.mass;
		this.acceleration.x += forceX;
		this.acceleration.y += forceY;
		this.acceleration.z += forceZ;
		}
	}
	
	
}
