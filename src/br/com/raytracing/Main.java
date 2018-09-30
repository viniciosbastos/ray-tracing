package br.com.raytracing;

import br.com.raytracing.enums.ProjectionType;
import br.com.raytracing.models.Color;
import br.com.raytracing.models.Point;
import br.com.raytracing.models.RayTracing;
import br.com.raytracing.models.shapes.Polygon;
import br.com.raytracing.models.shapes.Sphere;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("RayTracing");
        
        
        RayTracing rayTracing = new RayTracing(new Point(0,0,1), 2, ProjectionType.ORTOGRAFICO, 100, 100);
        rayTracing.addShape(new Sphere(new Point(0,0,5), 3, new Color((byte) 255, (byte) 0, (byte) 0)));
//        rayTracing.addShape(new Polygon(new Color((byte) 255, (byte) 0, (byte) 0), new Point(-3,3,5), new Point(3,3,5), new Point(3,-3,5), new Point(-3,-3,5)));
        rayTracing.addShape(new Polygon(new Color((byte) 0, (byte) 255, (byte) 0), new Point(-3,3,5), new Point(3,3,5), new Point(3,-3,5), new Point(-3,-3,5)));
        
        Image image = SwingFXUtils.toFXImage(rayTracing.getResultImage(), null);
        ImageView imageView = new ImageView(image);
        HBox hbox = new HBox(imageView);
        
        
        Scene scene = new Scene(hbox, 200, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}