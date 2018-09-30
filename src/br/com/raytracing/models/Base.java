package br.com.raytracing.models;

public class Base {

    private Point w;
    
    private Point u;

    private Point v;

    public Base(Point w, Point u, Point v) {
        this.w = w;
        this.u = u;
        this.v = v;
    }

    public Base(Point p_vision) {
        double mod_vision = p_vision.norm();
        this.w = p_vision.divideByScalar(mod_vision).timesScalar(-1);

        Point _t = new Point(p_vision);
        _t.setX(1);

        this.u = _t.cross(this.w).divideByScalar(_t.cross(this.w).norm());
        this.v = this.w.cross(this.u);
    }

    /**
     * @return the u
     */
    public Point getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(Point u) {
        this.u = u;
    }

    /**
     * @return the v
     */
    public Point getV() {
        return v;
    }

    /**
     * @param v the v to set
     */
    public void setV(Point v) {
        this.v = v;
    }

    /**
     * @return the w
     */
    public Point getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(Point w) {
        this.w = w;
    }    

    @Override
    public String toString() {
        return String.format("w: %s, u: %s, v: %s", this.w.toString(), this.u.toString(), this.v.toString());
    }
}