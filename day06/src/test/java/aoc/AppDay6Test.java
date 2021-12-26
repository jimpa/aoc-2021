package aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppDay6Test {


    @Test
    void part1Test() throws IOException {
        AppDay6 classUnderTest = new AppDay6(AppDay6.parseInput("input-trial.txt"));
        assertEquals(5934L, classUnderTest.getSolutionPart1());
    }

    @Test
    void part1Real() throws IOException {
        AppDay6 classUnderTest = new AppDay6(AppDay6.parseInput("input.txt"));
        System.out.println("Part 1: " + classUnderTest.getSolutionPart1());
    }

    @Test
    void part2Test() throws IOException {
        AppDay6 classUnderTest = new AppDay6(AppDay6.parseInput("input-trial.txt"));
        assertEquals(26984457539L, classUnderTest.getSolutionPart2());
    }

    @Test
    void part2Real() throws IOException {
        AppDay6 classUnderTest = new AppDay6(AppDay6.parseInput("input.txt"));
        System.out.println("Part 2: " + classUnderTest.getSolutionPart2());
    }
}
