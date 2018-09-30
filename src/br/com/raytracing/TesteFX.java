package br.com.raytracing;

import br.com.raytracing.enums.ProjectionType;
import br.com.raytracing.models.Point;
import br.com.raytracing.models.Ray;
import br.com.raytracing.models.RayTracing;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TesteFX extends Application {

    private Parent createContent() throws Exception {
        Translate pivot = new Translate();
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);

        // Create and position camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                pivot,
                yRotate,
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -50)
        );

        // animate the camera position.
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0), 
                        new KeyValue(yRotate.angleProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(15), 
                        new KeyValue(yRotate.angleProperty(), 360)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Build the Scene Graph

        RayTracing rayTracing = new RayTracing(new Point(0,0,1), 2, ProjectionType.OBLIQUO, 10, 10);
        Group root = new Group();       
        root.getChildren().add(camera);
        
        Sphere vision = new Sphere(0.3);
        vision.setMaterial(new PhongMaterial(Color.FORESTGREEN));
        vision.setTranslateX(0);
        vision.setTranslateY(0);
        vision.setTranslateZ(1);
        root.getChildren().add(vision);

        Ray[][] matrix = rayTracing.getRayMatrix();
        for (int i = 0; i < rayTracing.getImage().getNx(); i++) {
            for (int j = 0; j < rayTracing.getImage().getNy(); j++) {            	
                Sphere sphere = new Sphere(0.1);
                sphere.setMaterial(new PhongMaterial(Color.FORESTGREEN));

                Point p = matrix[i][j].getDirection();
                p = p.sum(rayTracing.getBase().getW().timesScalar(2));
                sphere.setTranslateX(p.getX());
                sphere.setTranslateY(p.getY());
                sphere.setTranslateZ(p.getZ());
                
                root.getChildren().add(sphere);
            }
        }
        
        // set the pivot for the camera position animation base upon mouse clicks on objects
        root.getChildren().stream()
                .filter(node -> !(node instanceof Camera))
                .forEach(node ->
                        node.setOnMouseClicked(event -> {
                            pivot.setX(node.getTranslateX());
                            pivot.setY(node.getTranslateY());
                            pivot.setZ(node.getTranslateZ());
                        })
                );

        // Use a SubScene
        SubScene subScene = new SubScene(
                root,
                600,600,
                true,
                SceneAntialiasing.BALANCED
        );
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(camera);
        Group group = new Group();
        group.getChildren().add(subScene);

        return group;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}