package br.com.raytracing.models;

public class Point {
    private double x;

    private double y;

    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
        this.z = p.getZ();
    }

	/**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }
    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * @return the y
     */
    public double getY() {
        return y;
    }
    /**
     * @param z the z to set
     */
    public void setZ(double z) {
        this.z = z;
    }
    
    /**
     * @return the z
     */
    public double getZ() {
        return z;
    }    

    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", this.x, this.y, this.z);
    }
    
    public Point sum(Point p) {
    	return new Point(this.getX() + p.getX(), this.getY() + p.getY(), this.getZ() + p.getZ());
    }
    
    public Point sub(Point p) {
    	return new Point(this.getX() - p.getX(), this.getY() - p.getY(), this.getZ() - p.getZ());
    }
    
    public Point cross(Point p) {
        double x = this.getY()*p.getZ() - this.getZ()*p.getY();
        double y = this.getZ()*p.getX() - this.getX()*p.getZ();
        double z = this.getX()*p.getY() - this.getY()*p.getX();

        return new Point(x, y, z);
    }

    public double dot(Point p) {
    	return this.getX()*p.getX() + this.getY()*p.getY() + this.getZ()*p.getZ();
    }
    
    public double norm() {
    	return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
    }
    
    public Point divideByScalar(double n) {
    	return new Point(this.getX()/n, this.getY()/n, this.getZ()/n);
    }
    
    public Point timesScalar(double n) {
    	return new Point(this.getX()*n, this.getY()*n, this.getZ()*n);
    }
    
    
    
}