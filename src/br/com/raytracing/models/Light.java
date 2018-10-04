package br.com.raytracing.models;

public class Light {

	private Color lightColor;
	
	private double i;
	
	private Point source;
	
	private double p;

	public Light(Color lightColor, double i, Point source, double p) {
		super();
		this.lightColor = lightColor;
		this.i = i;
		this.source = source;
		this.p = p;
	}

	public Light(Color lightColor, double i, Point source) {
		super();
		this.lightColor = lightColor;
		this.i = i;
		this.source = source;
	}

	public Color getLightColor() {
		return lightColor;
	}

	public void setLightColor(Color lightColor) {
		this.lightColor = lightColor;
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public Point getSource() {
		return source;
	}

	public void setSource(Point source) {
		this.source = source;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}		
}
