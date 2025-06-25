package org.yourcompany.yourproject.benchmarks;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MatrixBenchmark {
    private int[][] matrix;
    private int n = 1000;

    @Setup
    public void setup() {
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ThreadLocalRandom.current().nextInt(101);
            }
        }
    }

    @Benchmark
    public List<Long> testColumnProducts() {
        return IntStream.range(0, n)
            .parallel()
            .mapToObj(col -> {
                long product = 1;
                for (int row = 0; row < n; row++) {
                    product *= matrix[row][col];
                }
                return product;
            })
            .collect(Collectors.toList());
    }
}   