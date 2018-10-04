package br.com.raytracing.models.shapes;

import br.com.raytracing.models.Light;
import br.com.raytracing.models.Point;
import br.com.raytracing.models.Ray;

public interface Shape {

	public double hits(Ray ray);
	
	public int getColorLambert(Ray ray, double distance, Light light);
	public int getColorBlinnPhong(Ray ray, double distance, Light light, Point vision);
}
