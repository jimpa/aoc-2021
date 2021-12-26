package aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppDay7Test {

    @Test
    void part1Test() throws IOException {
        var subject = testSubject("input-trial.txt");
        assertEquals(37, subject.getSolutionPart1());
    }

    @Test
    void part1Real() throws IOException {
        var subject = testSubject("input.txt");
        System.out.println("Part 1: " + subject.getSolutionPart1());
    }

    @Test
    void part2Test() throws IOException {
        var subject = testSubject("input-trial.txt");
        assertEquals(168, subject.getSolutionPart2());
    }

    @Test
    void part2Real() throws IOException {
        var subject = testSubject("input.txt");
        System.out.println("Part 2: " + subject.getSolutionPart2());
    }

    private AppDay7 testSubject(String inputFilename) throws IOException {
        return new AppDay7(AppDay7.parseInput(inputFilename));
    }
}
