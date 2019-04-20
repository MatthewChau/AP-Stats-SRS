package app;

import java.util.Random;
import java.util.stream.*;

public class App {

    public static void main(String[] args) {

        Random _generator = new Random();

        int[] heights = {
            65, 63, 58, 71, 56, 64, 64, 63, 65, 66,
            66, 70, 51, 55, 57, 64, 63, 69, 59, 60,
            62, 62, 67, 66, 64, 63, 72, 68, 66, 63,
            62, 65, 56, 59, 66, 68, 62, 61, 58, 59,
            66, 67, 65, 64, 66, 69, 70, 70, 68, 65,
            63, 65, 67, 68, 69, 69, 71, 72, 55, 58,
            61, 64, 68, 65, 66, 69, 59, 70, 70, 60,
            60, 65, 68, 67, 63, 61, 58, 68, 65, 69};

        int SAMPLE_SIZE = 10;

        int[] _codes = new int[SAMPLE_SIZE];

        int[] _values = new int[SAMPLE_SIZE];

        double[] _partials = new double[SAMPLE_SIZE];

        boolean reset = false;
        
        for (int i=0; i < SAMPLE_SIZE; i++) {

            reset = false;

            _codes[i] = _generator.nextInt(80);
            

            // Throw out repeats
            for (int p=i; p > 0; p--) {
                if (_codes[i] == _codes[p - 1]) {
                    i--;
                    reset = true;
                    System.out.println("Repeat");
                }
            }

            if (!reset) {
                _values[i] = heights[_codes[i]];
                System.out.println("code " + i + " " + _codes[i]);
                System.out.println(" value " + _values[i]);
            }
        }

        //for (int l=0; l < SAMPLE_SIZE; l++) {
        //    System.out.print(_codes[l] + " ");
        //}

        //System.out.println(" ");

        //for (int q=0; q < SAMPLE_SIZE; q++) {
        //    System.out.print(_values[q] + " ");
        //}

        int sum = IntStream.of(_values).sum();
        double mean = sum / _values.length;

        System.out.println("Mean " + mean);

        for (int i=0; i < SAMPLE_SIZE; i++) {
            _partials[i] = Math.pow(_values[i] - mean, 2);
        }

        double partialSum = DoubleStream.of(_partials).sum();
        double stdDev = partialSum / (SAMPLE_SIZE - 1);

        System.out.println("Standard Deviation " + stdDev);
    }
    
}