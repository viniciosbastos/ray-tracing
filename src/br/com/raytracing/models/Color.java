package br.com.raytracing.models;

public class Color {

	private byte r;
	
	private byte g;
	
	private byte b;
	
	private int color;
	
	public Color(byte r, byte g, byte b) {
		this.r = r;
		this.g = g;
		this.b = b;
		
		this.color = 0;
		this.color = (this.color | (this.r & 0XFF)) << 8;
		this.color = (this.color | (this.g & 0XFF)) << 8;
		this.color = (this.color | (this.b & 0XFF));
	}

	public Color(int color) {
		super();
		this.color = color;
	}

	public byte getR() {
		return r;
	}

	public void setR(byte r) {
		this.r = r;
	}

	public byte getG() {
		return g;
	}

	public void setG(byte g) {
		this.g = g;
	}

	public byte getB() {
		return b;
	}

	public void setB(byte b) {
		this.b = b;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
		
}
