package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class AppDay5 {
    private final Input input;

    public AppDay5(Input input) {
        this.input = input;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("java");
        Input input = parseInput("input.txt");
        String part = System.getenv("part") == null ? "part1" : System.getenv("part");
        if (part.equals("part2"))
            System.out.println(new AppDay5(input).getSolutionPart2());
        else
            System.out.println(new AppDay5(input).getSolutionPart1());
    }

    static Input parseInput(String filename) throws IOException {
        return Files.lines(Path.of(filename))
                .reduce(new InputBuilder(), InputBuilder::accumulateLine,
                        (inputBuilder, inputBuilder2) -> inputBuilder)
                .buildInput();
    }

    static List<Coordinate> drawLine(int x1, int y1, int x2, int y2) {
        int x, y;
        int dx, dy;
        int incx, incy;
        int balance;
        List<Coordinate> result = new ArrayList<>();

        if (x2 >= x1) {
            dx = x2 - x1;
            incx = 1;
        } else {
            dx = x1 - x2;
            incx = -1;
        }

        if (y2 >= y1) {
            dy = y2 - y1;
            incy = 1;
        } else {
            dy = y1 - y2;
            incy = -1;
        }

        x = x1;
        y = y1;

        if (dx >= dy) {
            dy <<= 1;
            balance = dy - dx;
            dx <<= 1;

            while (x != x2) {
                result.add(new Coordinate(x, y));
                if (balance >= 0) {
                    y += incy;
                    balance -= dx;
                }
                balance += dy;
                x += incx;
            }
            result.add(new Coordinate(x, y));
        } else {
            dx <<= 1;
            balance = dx - dy;
            dy <<= 1;

            while (y != y2) {
                result.add(new Coordinate(x, y));
                if (balance >= 0) {
                    x += incx;
                    balance -= dy;
                }
                balance += dx;
                y += incy;
            }
            result.add(new Coordinate(x, y));
        }

        return result;
    }

    public int getSolutionPart1() {
        Map<Coordinate, AtomicInteger> foundCoordinates = new HashMap<>();
        input.lines.stream()
                .filter(line -> line.isHorizontal() || line.isVertical())
                .flatMap(Line::drawStraightLine)
                .forEach(coordinate -> {
                    AtomicInteger counter = foundCoordinates.getOrDefault(coordinate, new AtomicInteger(0));
                    counter.addAndGet(1);
                    foundCoordinates.put(coordinate, counter);
                });

        return getCountOfTwoPlus(foundCoordinates);
    }

    public int getSolutionPart2() {
        Map<Coordinate, AtomicInteger> foundCoordinates = new HashMap<>();
        input.lines.stream()
                .filter(line -> line.isHorizontal() || line.isVertical() || line.isDiagonal())
                .flatMap(Line::drawStraightLine)
                .forEach(coordinate -> {
                    AtomicInteger counter = foundCoordinates.getOrDefault(coordinate, new AtomicInteger(0));
                    counter.addAndGet(1);
                    foundCoordinates.put(coordinate, counter);
                });

        return getCountOfTwoPlus(foundCoordinates);
    }

    private int getCountOfTwoPlus(Map<Coordinate, AtomicInteger> foundCoordinates) {
        return (int) foundCoordinates.values().stream()
                .map(AtomicInteger::intValue)
                .filter(value -> value >= 2)
                .count();
    }

    static class InputBuilder {

        private final List<Line> accumulatedLines = new ArrayList<>();

        public Input buildInput() {
            return new Input(accumulatedLines);
        }

        public InputBuilder accumulateLine(String lineFromFile) {
            accumulatedLines.add(new Line(lineFromFile));

            return this;
        }
    }

    static class Input {
        final List<Line> lines;

        public Input(List<Line> lines) {
            this.lines = lines;
        }
    }

    static class Line {
        final Coordinate start;
        final Coordinate end;

        Line(String lineFromInput) {
            String[] s = lineFromInput.split("[, ]");
            start = new Coordinate(s[0], s[1]);
            end = new Coordinate(s[3], s[4]);
        }

        boolean isHorizontal() {
            return start.x == end.x;
        }

        boolean isVertical() {
            return start.y == end.y;
        }

        public boolean isDiagonal() {
            return (diff(start.x, end.x) == diff(start.y, end.y));
        }

        private int diff(int one, int two) {
            if (one > two) {
                return one - two;
            }

            return two - one;
        }

        public Stream<Coordinate> drawStraightLine() {
            return drawLine(start.x, start.y, end.x, end.y).stream();
        }
    }

    static class Coordinate {
        final int x;
        final int y;

        public Coordinate(String x, String y) {
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
        }

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coordinate that)) return false;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
