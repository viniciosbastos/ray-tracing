package br.com.raytracing.models.shapes;

import br.com.raytracing.models.Color;
import br.com.raytracing.models.Point;
import br.com.raytracing.models.Ray;

public class Sphere implements Shape{

	private Point center;
	
	private double radius;
	
	private Color color;
	
	public Sphere(Point center, double radius, Color color) {
		this.center = center;
		this.radius = radius;
		this.color = color;
	}	
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public int getColor() {
		return this.color.getColor();
	}

	@Override
	public double hits(Ray ray) {		
		double a = ray.getDirection().dot(ray.getDirection());
		double b = ray.getDirection().timesScalar(2).dot(ray.getOrigin().sub(this.center));
		double c = ray.getOrigin().sub(this.center).dot(ray.getOrigin().sub(this.center)) - (this.radius*this.radius);
		double delta = b*b - 4*a*c;
		double t;
		
		if (delta >= 0) {			
			double t1 = (ray.getDirection().timesScalar(-1).dot(ray.getOrigin().sub(this.center)) + Math.sqrt(delta)) / ray.getDirection().dot(ray.getDirection());
			double t2 = (ray.getDirection().timesScalar(-1).dot(ray.getOrigin().sub(this.center)) - Math.sqrt(delta)) / ray.getDirection().dot(ray.getDirection());
			if (t1 < t2)
				t = t1;
			else 
				t = t2;		
		} else {
			t = -1;
		}
		
		return t;
	}


}
