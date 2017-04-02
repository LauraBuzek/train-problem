package com.graph;

import java.util.*;

/**
 * Created by Laura on 3/26/17.
 */
public class TownGraph {
    HashMap<String, Node> townGraph;

    public TownGraph() {
        this.townGraph = new HashMap<>();
    }

    public void addConnection(String nodeOneName, String nodeTwoName, int distance) {
        if(!townGraph.containsKey(nodeTwoName)) { createNode(nodeTwoName); }
        if(!townGraph.containsKey(nodeOneName)) { createNode(nodeOneName); }

        Node nodeOne = townGraph.get(nodeOneName);
        nodeOne.addConnection(nodeTwoName, distance);
    }

    public Integer calculateDistanceBetweenElements(String[] nodeNames) {
        int distance = 0;
        for(int i = 0; i < nodeNames.length - 1; i++) {
            Node curr_node = townGraph.get(nodeNames[i]);
            int edgeLength = curr_node.getConnection(nodeNames[i+1]);
            if (edgeLength == -1) {
                return -1;
            } else {
                distance += edgeLength;
            }
        }

        return distance;
    }

    public Integer calculateNumTripsByNumStops(String begin, String end, int numStops) {
        List<List<String>> cycles = retrievePaths(begin, end, numStops, new ArrayList<String>());



        return 1;
    }

    public List<List<String>> retrievePaths(String current, String end, int numStops, List<String> stopList) {
        Node currNode = townGraph.get(current);
        stopList.add(current);
        if (current.equals(end) && stopList.size() != 1) {
            return Arrays.asList(stopList);
        }
        List<List<String>> tripsToEnd = new ArrayList<>();
        if (numStops > 0) {
            int newNumStops = numStops - 1;
            for (String nodeName:currNode.getConnections()) {
                List<String> newStopList = new ArrayList<>(stopList);
                List<List<String>> result = retrievePaths(nodeName, end, newNumStops, newStopList);
                if(result != null) {
                    tripsToEnd.addAll(result);
                }
            }
        }

        return tripsToEnd;

    }


    private void createNode(String nodeName) {
        Node node = new Node(nodeName);
        this.townGraph.put(nodeName, node);
    }
}
