/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppDay2 {
    private final List<Map.Entry<String, Integer>> input;

    public AppDay2(List<Map.Entry<String, Integer>> input) {
        this.input = input;
    }

    public int getSolutionPart1() {
        int horizontalPosition = 0;
        int depth = 0;
        for (Map.Entry<String, Integer> entry : input) {
            if ("forward".equalsIgnoreCase(entry.getKey())) {
                horizontalPosition += entry.getValue();
            }
            if ("down".equalsIgnoreCase(entry.getKey())) {
                depth += entry.getValue();
            }
            if ("up".equalsIgnoreCase(entry.getKey())) {
                depth -= entry.getValue();
            }
        }

        return horizontalPosition * depth;
    }

    public int getSolutionPart2() {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;
        for (Map.Entry<String, Integer> entry : input) {
            if ("forward".equalsIgnoreCase(entry.getKey())) {
                horizontalPosition += entry.getValue();
                depth += aim * entry.getValue();
            }
            if ("down".equalsIgnoreCase(entry.getKey())) {
                aim += entry.getValue();
            }
            if ("up".equalsIgnoreCase(entry.getKey())) {
                aim -= entry.getValue();
            }
        }

        return horizontalPosition * depth;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("java");
        List<Map.Entry<String, Integer>> input = parseInput("input.txt");
        String part = System.getenv("part") == null ? "part1" : System.getenv("part");
        if (part.equals("part2"))
            System.out.println(new AppDay2(input).getSolutionPart2());
        else
            System.out.println(new AppDay2(input).getSolutionPart1());
    }

    private static List<Map.Entry<String, Integer>> parseInput(String filename) throws IOException {
        return Files.lines(Path.of(filename))
                .map(line -> {
                    return new Map.Entry<String, Integer>() {

                        @Override
                        public String getKey() {
                            return line.split(" ")[0];
                        }

                        @Override
                        public Integer getValue() {
                            return Integer.parseInt(line.split(" ")[1]);
                        }

                        @Override
                        public Integer setValue(Integer value) {
                            throw new UnsupportedOperationException();
                        }
                    };
                })
                .collect(Collectors.toList());
    }
}
