package com.run;

import com.graph.TownGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Laura on 3/26/17.
 */
public class Trains {
    public static void main(String args[]) {
        FileReader input = null;
        BufferedReader bufferedInput = null;

        try {
            input = new FileReader("src/resource/input_graph.txt");
            bufferedInput = new BufferedReader(input);

            TownGraph townGraph = TownGraphGenerator.createTownGraph(bufferedInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
