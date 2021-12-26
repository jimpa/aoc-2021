package aoc;

import com.google.common.math.Quantiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppDay7 {
    private final Input input;

    public AppDay7(Input input) {
        this.input = input;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("java");
        Input input = parseInput("input.txt");
        String part = System.getenv("part") == null ? "part1" : System.getenv("part");
        if (part.equals("part2"))
            System.out.println(new AppDay7(input).getSolutionPart2());
        else
            System.out.println(new AppDay7(input).getSolutionPart1());
    }

    static Input parseInput(String filename) throws IOException {
        return Files.lines(Path.of(filename))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .reduce(new InputBuilder(), InputBuilder::accumulateLine,
                        (inputBuilder, inputBuilder2) -> inputBuilder)
                .buildInput();
    }

    public int getSolutionPart1() {
        var optimalPosition = Math.round(Quantiles.median().compute(input.positions));

        return input.positions.stream()
                .map(position -> (int) Math.abs(position - optimalPosition))
                .reduce(0, Integer::sum);
    }

    public int getSolutionPart2() {
        List<Integer> sorted = input.positions.stream().sorted().toList();
        var min = sorted.get(0);
        var max = sorted.get(sorted.size() - 1);

        int[] costs = new int[max + 1]; // The elements below min are not used.

        for (Integer position : sorted) {
            for (int positionToCheck = min; positionToCheck <= max; positionToCheck++) {
                costs[positionToCheck] += distance2Consumption(Math.abs(position - positionToCheck));
            }
        }

        // The one with the lowest accumulated cost is the optimal.
        var possibleOptimalPosition = -1;
        var lowestSeenCost = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            if (costs[i] < lowestSeenCost) {
                lowestSeenCost = costs[i];
                possibleOptimalPosition = i;
            }
        }

        var optimalPosition = possibleOptimalPosition;
        return input.positions.stream()
                .map(position -> (int) Math.abs(position - optimalPosition))
                .map(this::distance2Consumption)
                .reduce(0, Integer::sum);
    }

    Map<Integer, Integer> costs4distances = new HashMap<>();
    private int distance2Consumption(int distance) {
        return costs4distances.computeIfAbsent(distance, dist -> {
            int result = 0;
            for (int i = 1; i <= distance; i++) {
                result += i;
            }
            return result;
        });
    }

    static class InputBuilder {

        List<Integer> positions;

        public Input buildInput() {
            return new Input(positions);
        }

        public InputBuilder accumulateLine(String lineFromFile) {
            positions = Arrays.stream(lineFromFile.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return this;
        }
    }

    static class Input {
        final List<Integer> positions;

        public Input(List<Integer> positions) {
            this.positions = positions;
        }
    }
}
