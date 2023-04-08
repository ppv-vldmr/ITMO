package org.example.api;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class FxDrawingApi implements DrawingApi {
    private static final AtomicReference<Canvas> CANVAS = new AtomicReference<>(new Canvas(500, 500));
    private final List<Consumer<GraphicsContext>> drawingTasks = new ArrayList<>();

    public FxDrawingApi(int width, int height) {
        CANVAS.set(new Canvas(width, height));
    }

    @Override
    public long getDrawingAreaWidth() {
        return (long) CANVAS.get().getWidth();
    }

    @Override
    public long getDrawingAreaHeight() {
        return (long) CANVAS.get().getHeight();
    }

    @Override
    public void drawCircle(double x, double y, double radius) {
        drawingTasks.add(graphicsContext -> graphicsContext.fillOval(x - radius, y - radius, 2 * radius, 2 * radius));
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        drawingTasks.add(graphicsContext -> graphicsContext.strokeLine(x1, y1, x2, y2));
    }

    @Override
    public void show() {
        final GraphicsContext graphicsContext = CANVAS.get().getGraphicsContext2D();
        drawingTasks.forEach(drawingTask -> drawingTask.accept(graphicsContext));
        FxDrawer.launch(FxDrawer.class);
    }

    public static class FxDrawer extends Application {
        @Override
        public void start(Stage stage) {
            final Group group = new Group();
            group.getChildren().add(CANVAS.get());

            final Scene scene = new Scene(group, Color.WHITE);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(windowEvent -> System.exit(0));
        }
    }
}
