package br.com.raytracing.models;

public class Color {

	private int r;
	
	private int g;
	
	private int b;
	
	private int color;
	
	public Color(int r, int g, int b) {
		this.r = r > 255 ? 255 : r;
		this.g = g > 255 ? 255 : g;
		this.b = b > 255 ? 255 : b;
		
		this.color = 0;
		this.color = (this.color | (this.r & 0XFF)) << 8;
		this.color = (this.color | (this.g & 0XFF)) << 8;
		this.color = (this.color | (this.b & 0XFF));
	}

	public Color(int color) {
		super();
		this.color = color;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
		
}
