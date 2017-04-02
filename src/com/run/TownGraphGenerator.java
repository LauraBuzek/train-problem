package com.run;

import com.graph.TownGraph;

import java.io.BufferedReader;
import java.io.IOException;

public class TownGraphGenerator {

    public static TownGraph createTownGraph(BufferedReader bufferedInput) throws IOException {
        TownGraph townGraph = new TownGraph();
        String currentLine;
        String[] trainConnections;
        while((currentLine = bufferedInput.readLine()) != null) {
            trainConnections = currentLine.split("\\s+");
            for (String connection:trainConnections) {
                String[] values = connection.split("(?!^)");
                townGraph.addConnection(values[0], values[1], Integer.valueOf(values[2]));
            }
        }
        return townGraph;
    }
}
