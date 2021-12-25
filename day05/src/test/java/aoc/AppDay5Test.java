package aoc;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppDay5Test {

    @Test
    void part1Test() throws IOException {
        AppDay5 classUnderTest = new AppDay5(AppDay5.parseInput("input-trial.txt"));
        assertEquals(5, classUnderTest.getSolutionPart1());
    }

    @Test
    void part1Real() throws IOException {
        AppDay5 classUnderTest = new AppDay5(AppDay5.parseInput("input.txt"));
        System.out.println("Part 1: " + classUnderTest.getSolutionPart1());
    }

    @Test
    void part2Test() throws IOException {
        AppDay5 classUnderTest = new AppDay5(AppDay5.parseInput("input-trial.txt"));
        assertEquals(12, classUnderTest.getSolutionPart2());
    }

    @Test
    void part2Real() throws IOException {
        AppDay5 classUnderTest = new AppDay5(AppDay5.parseInput("input.txt"));
        System.out.println("Part 2: " + classUnderTest.getSolutionPart2());
    }
}
