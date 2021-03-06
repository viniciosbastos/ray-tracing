package br.com.raytracing.models.shapes;

import br.com.raytracing.models.Color;
import br.com.raytracing.models.Light;
import br.com.raytracing.models.Point;
import br.com.raytracing.models.Ray;
import br.com.raytracing.utils.MathUtils;

public class Polygon implements Shape{

	private Point[] points;
	
	private Color color;
	
	private Point normal;
	
	public Polygon(Color color, Point ...points) {
		this.points = points;
		this.color = color;
		
		Point p1 = points[1].sub(points[0]);
		Point p2 = points[2].sub(points[0]);
		
		this.normal = p1.cross(p2).divideByScalar(p1.cross(p2).norm());
	}

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}
		
	public Point getNormal() {
		return normal;
	}

	@Override
	public int getColorLambert(Ray ray, double distance, Light light) {
		Point p = ray.getOrigin().sum(ray.getDirection().timesScalar(distance));
		Point l = light.getSource().sub(p).divideByScalar(light.getSource().sub(p).norm());

		int r = (int) (this.color.getR()*light.getI()*Math.max(0, this.normal.dot(l)));
		int g = (int) (this.color.getG()*light.getI()*Math.max(0, this.normal.dot(l)));
		int b = (int) (this.color.getB()*light.getI()*Math.max(0, this.normal.dot(l)));
		
		return new Color(r, g, b).getColor();
	}

	@Override
	public int getColorBlinnPhong(Ray ray, double distance, Light light, Point vision) {
		Point p = ray.getOrigin().sum(ray.getDirection().timesScalar(distance));
		Point l = light.getSource().sub(p).divideByScalar(light.getSource().sub(p).norm());
		Point v = vision.sub(p).divideByScalar(vision.sub(p).norm());
		Point h = v.sum(l).divideByScalar(v.sum(l).norm());
		
		int r = (int) (this.color.getR()*light.getI()*Math.max(0, this.normal.dot(l)) + light.getLightColor().getR()*light.getI()*Math.pow(Math.max(0, this.normal.dot(h)), light.getP()));
		int g = (int) (this.color.getG()*light.getI()*Math.max(0, this.normal.dot(l)) + light.getLightColor().getG()*light.getI()*Math.pow(Math.max(0, this.normal.dot(h)), light.getP()));
		int b = (int) (this.color.getB()*light.getI()*Math.max(0, this.normal.dot(l)) + light.getLightColor().getB()*light.getI()*Math.pow(Math.max(0, this.normal.dot(h)), light.getP()));
		
		return new Color(r, g, b).getColor();
	}


	@Override
	public double hits(Ray ray) {
		double distance = 0;
		
		Point a = this.points[0];
		Point b = null;
		Point c = null;
		for (int i = 2; i < this.points.length; i++) {
			b = this.points[i-1];
			c = this.points[i];
			
			double det_matrix = MathUtils.det(new double[][] {{a.getX() - b.getX(), a.getX() - c.getX(), ray.getDirection().getX()},
					   			 								{a.getY() - b.getY(), a.getY() - c.getY(), ray.getDirection().getY()},
					   			 								{a.getZ() - b.getZ(), a.getZ() - c.getZ(), ray.getDirection().getZ()}});

			double det_matrix_beta = MathUtils.det(new double[][] {{a.getX() - ray.getOrigin().getX(), a.getX() - c.getX(), ray.getDirection().getX()},
													   				 {a.getY() - ray.getOrigin().getY(), a.getY() - c.getY(), ray.getDirection().getY()},
  												   				     {a.getZ() - ray.getOrigin().getZ(), a.getZ() - c.getZ(), ray.getDirection().getZ()}});
 
			double det_matrix_alfa = MathUtils.det(new double[][] {{a.getX() - b.getX(), a.getX() - ray.getOrigin().getX(), ray.getDirection().getX()},
					   				  								 {a.getY() - b.getY(), a.getY() - ray.getOrigin().getY(), ray.getDirection().getY()},
					   				  								 {a.getZ() - b.getZ(), a.getZ() - ray.getOrigin().getZ(), ray.getDirection().getZ()}});

			double det_matrix_t = MathUtils.det(new double[][] {{a.getX() - b.getX(), a.getX() - c.getX(), a.getX() - ray.getOrigin().getX()},
					   			   						          {a.getY() - b.getY(), a.getY() - c.getY(), a.getY() - ray.getOrigin().getY()},
					   			   						          {a.getZ() - b.getZ(), a.getZ() - c.getZ(), a.getZ() - ray.getOrigin().getZ()}});

			double beta = det_matrix_beta / det_matrix;  
			double alfa = det_matrix_alfa / det_matrix;
			double t = det_matrix_t / det_matrix;
			
			if (t < 0 || t > 100) 
				distance = -1;
			else if ( alfa < 0 || alfa > 1)
				distance = -1;
			else if (beta < 0 || beta > (1 - alfa))
				distance = -1;
			else {
				distance = t;
				break;
			}
			
		}
		
		return distance;
	}

}
