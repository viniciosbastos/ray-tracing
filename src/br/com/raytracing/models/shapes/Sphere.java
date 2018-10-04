package br.com.raytracing.models.shapes;

import br.com.raytracing.models.Color;
import br.com.raytracing.models.Light;
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
	public int getColorLambert(Ray ray, double distance, Light light) {
		Point p = ray.getOrigin().sum(ray.getDirection().timesScalar(distance));
		Point l = light.getSource().sub(p).divideByScalar(light.getSource().sub(p).norm());
		
		Point normal = p.sub(this.center).divideByScalar(this.radius);
		
		int r = (int) (this.color.getR()*light.getI()*Math.max(0, normal.dot(l)));
		int g = (int) (this.color.getG()*light.getI()*Math.max(0, normal.dot(l)));
		int b = (int) (this.color.getB()*light.getI()*Math.max(0, normal.dot(l)));
		
		return new Color(r, g, b).getColor();
	}

	@Override
	public int getColorBlinnPhong(Ray ray, double distance, Light light, Point vision) {
		Point p = ray.getOrigin().sum(ray.getDirection().timesScalar(distance));
		Point l = light.getSource().sub(p).divideByScalar(light.getSource().sub(p).norm());
		Point v = vision.sub(p).divideByScalar(vision.sub(p).norm());
		Point h = v.sum(l).divideByScalar(v.sum(l).norm());
		
		Point normal = p.sub(this.center).divideByScalar(this.radius);
		
		int r = (int) (this.color.getR()*light.getI()*Math.max(0, normal.dot(l)) + light.getLightColor().getR()*light.getI()*Math.pow(Math.max(0, normal.dot(h)), light.getP()));
		int g = (int) (this.color.getG()*light.getI()*Math.max(0, normal.dot(l)) + light.getLightColor().getG()*light.getI()*Math.pow(Math.max(0, normal.dot(h)), light.getP()));
		int b = (int) (this.color.getB()*light.getI()*Math.max(0, normal.dot(l)) + light.getLightColor().getB()*light.getI()*Math.pow(Math.max(0, normal.dot(h)), light.getP()));
		
		return new Color(r, g, b).getColor();
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
