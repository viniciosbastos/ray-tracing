package br.com.raytracing.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import br.com.raytracing.enums.ProjectionType;
import br.com.raytracing.models.shapes.Shape;

public class RayTracing {

    private Point p_vision;
    
    private Light light;

    private int distance;

    private ProjectionType type;

    private Base base;

    private Image image;
    
    private Ray[][] rayMatrix;
    
    private List<Shape> objects;

    public RayTracing(Point p_vision, Light light, int d, ProjectionType type, int nx, int ny) {
        this.p_vision = p_vision;
        this.light = light;
        this.distance = d;
        this.type = type;
        this.base = new Base(p_vision);
        this.image = new Image(10, -10, 10, -10, nx, ny);
        this.rayMatrix = new Ray[nx][ny];
        this.objects = new ArrayList<>();
        computeRays();
    }

    /**
     * @return the base
     */
    public Base getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(Base base) {
        this.base = base;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the p_vision
     */
    public Point getP_vision() {
        return p_vision;
    }

    /**
     * @param p_vision the p_vision to set
     */
    public void setP_vision(Point p_vision) {
        this.p_vision = p_vision;
    }  

    /**
     * @return the type
     */
    public ProjectionType getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(ProjectionType type) {
        this.type = type;
    }       
    
    public Light getLight() {
		return light;
	}

	public Ray[][] getRayMatrix() {
    	return this.rayMatrix;
    }
    
    public void addShape(Shape obj) {
    	this.objects.add(obj);
    }
    
    private void computeRays() {
    	if (this.type == ProjectionType.ORTOGRAFICO) {
    		this.computeOrtCase();
    	} else if (this.type == ProjectionType.OBLIQUO) {
    		this.computeOblCase();
    	}
    }
    
    private void computeOrtCase() {
    	double u, v;
    	for (int x = 0; x < this.image.getNx(); x++) {
        	for (int y = this.image.getNy()-1; y >= 0; y--) {
        		v = this.image.getLeft() + (this.image.getRight() - this.image.getLeft())*(x + 0.5)/this.image.getNx();
        		u = this.image.getBottom() + (this.image.getTop() - this.image.getBottom())*(y + 0.5)/this.image.getNy();
        		
        		rayMatrix[x][this.image.getNy()-1-y] = new Ray(this.p_vision.sum(this.base.getU().timesScalar(u)).sum(this.base.getV().timesScalar(v)), 
        								  this.base.getW().timesScalar(-1));
        	}
    	}
    }
    
    private void computeOblCase() {
    	double u, v;
    	for (int x = 0; x < this.image.getNx(); x++) {
        	for (int y = this.image.getNy()-1; y >= 0; y--) {
        		v = this.image.getLeft() + (this.image.getRight() - this.image.getLeft())*(x + 0.5)/this.image.getNx();
        		u = this.image.getBottom() + (this.image.getTop() - this.image.getBottom())*(y + 0.5)/this.image.getNy();
        		rayMatrix[x][this.image.getNy()-1-y] = new Ray(this.p_vision, 
        								  this.base.getW().timesScalar(-this.distance).sum(this.base.getU().timesScalar(u)).sum(this.base.getV().timesScalar(v)));
        		
        	}
    	}
    }
    
    public BufferedImage getResultImage() {
    	BufferedImage image = new BufferedImage(this.image.getNx(), this.image.getNy(), BufferedImage.TYPE_INT_RGB);
    	
    	double m_dist, dist;
    	int color;
		for (int x = 0; x < this.image.getNx(); x++) {    		
			for (int y = 0; y < this.image.getNy() ; y++) {
				m_dist = 100;
				color = 0;
				for (Shape shape : this.objects) {
					dist = shape.hits(rayMatrix[x][y]);
					if (dist != -1 && dist < m_dist) {
						m_dist = dist;
//						color = shape.getColorLambert(rayMatrix[x][y], dist, this.light);
						color = shape.getColorBlinnPhong(rayMatrix[x][y], dist, light, this.p_vision);
					}
				}
				image.setRGB(x, y, color);			  
			}
    	}
//		image.setRGB(0, 0, 0xffffff);
//		image.setRGB(0, 1, 0xffffff);
//		image.setRGB(0, 2, 0xffffff);
//		image.setRGB(0, 3, 0xffffff);
//		image.setRGB(0, 4, 0xffffff);
//		image.setRGB(0, 5, 0xffffff);
    	return image;
    }
}