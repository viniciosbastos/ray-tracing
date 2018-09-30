package br.com.raytracing.models;

public class Ray {
    private Point origin;

    private Point direction;

    public Ray(Point origin, Point direction) {
        this.origin = origin;
        this.direction = direction;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    /**
     * @return the origin
     */
    public Point getOrigin() {
        return origin;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Point direction) {
        this.direction = direction;
    }

    /**
     * @return the direction
     */
    public Point getDirection() {
        return direction;
    }
}