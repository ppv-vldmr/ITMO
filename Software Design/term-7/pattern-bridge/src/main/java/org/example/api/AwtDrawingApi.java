package org.example.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import one.util.streamex.EntryStream;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AwtDrawingApi implements DrawingApi {
    @Getter
    private final Map<Shape, Boolean> shapeToFilledMap = new HashMap<>();
    private final int width;
    private final int height;

    @Override
    public long getDrawingAreaWidth() {
        return width;
    }

    @Override
    public long getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(double x, double y, double radius) {
        shapeToFilledMap.put(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius), true);
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        shapeToFilledMap.put(new Line2D.Double(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2)), false);
    }

    @Override
    public void show() {
        final Frame frame = new AwtFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.setSize((int) getDrawingAreaWidth(), (int) getDrawingAreaHeight());
        frame.setVisible(true);
    }

    private class AwtFrame extends Frame {
        @Override
        public void paint(Graphics graphics) {
            final Graphics2D graphics2D = (Graphics2D) graphics;
            EntryStream.of(shapeToFilledMap).filterValues(x -> !x).keys().forEach(graphics2D::draw);
            EntryStream.of(shapeToFilledMap).filterValues(x -> x).keys().forEach(graphics2D::fill);
        }
    }
}
