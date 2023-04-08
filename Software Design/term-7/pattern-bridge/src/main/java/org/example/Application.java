package org.example;

import org.example.api.AwtDrawingApi;
import org.example.api.DrawingApi;
import org.example.api.FxDrawingApi;
import org.example.drawer.GraphDrawer;
import org.example.model.Graph;
import org.example.model.ListGraph;
import org.example.model.MatrixGraph;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.*;

public class Application {
    public static void main(String[] args) throws ParseException, IOException {
        final Options options = new Options();
        options.addOption("d", "drawingApi", true, "drawing api");
        options.addOption("t", "graphType", true, "graph type");
        options.addOption("f", "file", true, "file");
        options.addOption("w", "width", true, "width");
        options.addOption("h", "height", true, "height");
        final CommandLineParser commandLineParser = new PosixParser();
        final CommandLine commandLine = commandLineParser.parse(options, args);

        BiFunction<Integer, Integer, DrawingApi> drawingApiBuilder = AwtDrawingApi::new;
        Class<? extends Graph> graphType = ListGraph.class;
        File file = new File("input.txt");
        int width = 500;
        int height = 500;
        for (Option option : commandLine.getOptions()) {
            switch (option.getOpt()) {
                case "d":
                    switch (option.getValue()) {
                        case "awt":
                            drawingApiBuilder = AwtDrawingApi::new;
                            break;
                        case "fx":
                            drawingApiBuilder = FxDrawingApi::new;
                            break;
                        default:
                            throw new IllegalArgumentException("This drawing api is not supported yet.");
                    }
                    break;
                case "t":
                    switch (option.getValue()) {
                        case "list":
                            graphType = ListGraph.class;
                            break;
                        case "matrix":
                            graphType = MatrixGraph.class;
                            break;
                        default:
                            throw new IllegalArgumentException("This graph type is not supported yet.");
                    }
                    break;
                case "f":
                    file = new File(option.getValue());
                    break;
                case "w":
                    width = Integer.parseInt(option.getValue());
                    break;
                case "h":
                    height = Integer.parseInt(option.getValue());
                    break;
            }
        }

        final DrawingApi drawingApi = drawingApiBuilder.apply(width, height);
        final GraphDrawer graphDrawer = new GraphDrawer(drawingApi);

        final List<String> lines = Files.lines(file.toPath()).collect(toList());
        final Graph graph = ListGraph.class.equals(graphType) ? parseListGraph(lines) : parseMatrixGraph(lines);
        graphDrawer.draw(graph);
    }

    public static ListGraph parseListGraph(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Cannot create graph without size.");
        }

        final int size = Integer.parseInt(lines.get(0));
        final List<Integer> firstValues = new ArrayList<>();
        final List<Integer> secondValues = new ArrayList<>();
        for (String line : lines.subList(1, lines.size())) {
            final String[] numbers = line.split("\\s+");
            if (numbers.length != 2) {
                throw new IllegalArgumentException("Expected two number, but found " + numbers.length + ".");
            }
            firstValues.add(Integer.parseInt(numbers[0]));
            secondValues.add(Integer.parseInt(numbers[1]));
        }

        return new ListGraph(size, firstValues, secondValues);
    }

    public static MatrixGraph parseMatrixGraph(List<String> lines) {
        final List<List<Character>> matrix = lines.stream()
                .map(line -> Arrays.stream(line.split("\\s+")).map(x -> x.charAt(0)).collect(toList()))
                .collect(toList());
        if (matrix.isEmpty() || matrix.stream().allMatch(List::isEmpty)) {
            throw new IllegalArgumentException("Cannot create graph without size.");
        }

        final Set<Character> uniqueCharacters = matrix.stream().flatMap(List::stream).collect(toSet());
        if (uniqueCharacters.size() > 2) {
            throw new IllegalArgumentException("Wrong adjacency matrix.");
        }

        final Character trueCharacter = uniqueCharacters.stream().max(Character::compare).orElse('1');
        return matrix.stream()
                .map(line -> line.stream().map(trueCharacter::equals).collect(toList()))
                .collect(collectingAndThen(toList(), MatrixGraph::new));
    }
}
