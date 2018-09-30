package br.com.raytracing.models;

public class Image {

    private int right;

    private int left;

    private int top;

    private int bottom;

    private int nx;

    private int ny;

    public Image() {}

    public Image(int r, int l, int t, int b, int nx, int ny) {
        this.right = r;
        this.left = l;
        this.top = t;
        this.bottom = b;        
        this.nx = nx;
        this.ny = ny;        
    }

    /**
     * @param right the right to set
     */
    public void setRight(int right) {
        this.right = right;
    }

    /**
     * @return the right
     */
    public int getRight() {
        return right;
    }
    /**
     * @param left the left to set
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * @return the left
     */
    public int getLeft() {
        return left;
    }

    /**
     * @param top the top to set
     */
    public void setTop(int top) {
        this.top = top;
    }

    /**
     * @return the top
     */
    public int getTop() {
        return top;
    }

    /**
     * @param bottom the bottom to set
     */
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    /**
     * @return the bottom
     */
    public int getBottom() {
        return bottom;
    }    

    /**
     * @param nx the nx to set
     */
    public void setNx(int nx) {
        this.nx = nx;
    }

    /**
     * @return the nx
     */
    public int getNx() {
        return nx;
    }

    /**
     * @param ny the ny to set
     */
    public void setNy(int ny) {
        this.ny = ny;
    }

    /**
     * @return the ny
     */
    public int getNy() {
        return ny;
    }
    
}