package com.run;

import com.graph.TownGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Trains {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter file path for the desired input graph:");
        String filename = scanner.next();
        FileReader input = null;
        BufferedReader bufferedInput = null;

        try {
            //"src/com/resource/input_graph.txt"
            input = new FileReader(filename);
            bufferedInput = new BufferedReader(input);

            TownGraph townGraph = TownGraphGenerator.createTownGraph(bufferedInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {
            System.out.println("Request ")
    }




}
