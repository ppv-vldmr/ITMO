package org.example.drawer;

import org.example.api.DrawingApi;
import org.example.model.Graph;
import lombok.RequiredArgsConstructor;
import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.geom.Point2D;
import java.util.List;

@RequiredArgsConstructor
public class GraphDrawer {
    private final DrawingApi drawingApi;

    public void draw(Graph graph) {
        final long width = drawingApi.getDrawingAreaWidth();
        final long height = drawingApi.getDrawingAreaHeight();

        final double radius = Math.min(width, height) / 3.0;
        final Point2D center = new Point2D.Double(width / 2.0, height / 2.0);
        if (graph.size() == 1) {
            drawingApi.drawCircle(center.getX(), center.getY(), 10);
            drawingApi.show();
            return;
        }

        final List<Point2D> points = IntStreamEx.range(graph.size())
                .mapToObj(vertex -> getPoint(graph, center, radius, vertex))
                .toList();
        points.forEach(point -> drawingApi.drawCircle(point.getX(), point.getY(), 10));
        StreamEx.of(graph.getEdges())
                .mapToEntry(Pair::getLeft, Pair::getRight)
                .mapKeys(points::get)
                .mapValues(points::get)
                .forKeyValue((v, u) -> drawingApi.drawLine(v.getX(), v.getY(), u.getX(), u.getY()));
        drawingApi.show();
    }

    private Point2D getPoint(Graph graph, Point2D center, double radius, int vertex) {
        final double angle = 2 * Math.PI * vertex / graph.size();
        return new Point2D.Double(center.getX() + radius * Math.cos(angle), center.getY() + radius * Math.sin(angle));
    }
}
