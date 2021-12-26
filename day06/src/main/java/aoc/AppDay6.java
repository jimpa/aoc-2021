package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class AppDay6 {
    private final Input input;

    public AppDay6(Input input) {
        this.input = input;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("java");
        Input input = parseInput("input.txt");
        String part = System.getenv("part") == null ? "part1" : System.getenv("part");
        if (part.equals("part2"))
            System.out.println(new AppDay6(input).getSolutionPart2());
        else
            System.out.println(new AppDay6(input).getSolutionPart1());
    }

    static Input parseInput(String filename) throws IOException {
        return Files.lines(Path.of(filename))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .reduce(new InputBuilder(), InputBuilder::accumulateLine,
                        (inputBuilder, inputBuilder2) -> inputBuilder)
                .buildInput();
    }

    public long getSolutionPart1() {
        for (int generation = 0; generation < 80; generation++) {
            long currentCountZero = input.fishes[0];

            for (int fishIndex = 1; fishIndex < 10; fishIndex++) {
                input.fishes[fishIndex - 1] = input.fishes[fishIndex];
            }

            input.fishes[6] += currentCountZero; // The old resets.
            input.fishes[8] += currentCountZero; // The new ones.
        }

        return Arrays.stream(input.fishes).reduce(0, Long::sum);
    }

    public long getSolutionPart2() {
        for (int generation = 0; generation < 256; generation++) {
            long currentCountZero = input.fishes[0];

            for (int fishIndex = 1; fishIndex < 10; fishIndex++) {
                input.fishes[fishIndex - 1] = input.fishes[fishIndex];
            }

            input.fishes[6] += currentCountZero; // The old resets.
            input.fishes[8] += currentCountZero; // The new ones.
        }

        return Arrays.stream(input.fishes).reduce(0, Long::sum);
    }

    static class InputBuilder {

        long[] fishes = new long[10];

        public Input buildInput() {
            return new Input(fishes);
        }

        public InputBuilder accumulateLine(String lineFromFile) {
            List<String> strings = Arrays.asList(lineFromFile.split(","));
            strings.stream().map(Integer::parseInt).forEach(fish -> fishes[fish]++);
            return this;
        }
    }

    static class Input {
        final long[] fishes;

        public Input(long[] fishes) {
            this.fishes = fishes;
        }
    }
}
