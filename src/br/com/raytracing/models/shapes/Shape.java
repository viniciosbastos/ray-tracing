package br.com.raytracing.models.shapes;

import br.com.raytracing.models.Ray;

public interface Shape {

	public double hits(Ray ray);
	
	public int getColor();
}
