package com.graph;

import javafx.util.Pair;

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
            int edgeLength = curr_node.getConnectionByName(nodeNames[i+1]);
            if (edgeLength == -1) {
                return -1;
            } else {
                distance += edgeLength;
            }
        }

        return distance;
    }

    public Integer calculateNumTripsByNumStops(String begin, String end, int numStops) {
        return retrievePaths(begin, end, numStops, new ArrayList<>()).size();
    }

    public List<List<String>> retrievePaths(String current, String end, int numStops, List<String> stopList) {
        Node currNode = townGraph.get(current);
        stopList.add(current);
        List<List<String>> tripsToEnd = new ArrayList<>();
        if (current.equals(end) && stopList.size() != 1) {
            tripsToEnd.add(stopList);
        }

        if (numStops > 0) {
            int newNumStops = numStops - 1;
            for (String nodeName:currNode.getNamesOfConnections()) {
                List<String> newStopList = new ArrayList<>(stopList);
                List<List<String>> result = retrievePaths(nodeName, end, newNumStops, newStopList);
                if(result != null) {
                    tripsToEnd.addAll(result);
                }
            }
        }

        return tripsToEnd;
    }

    public Integer getShortestPath(String begin, String end) {
        return getShortestPathHelper(begin, end, new ArrayList<String>(), 0);
    }

    private Integer getShortestPathHelper(String current, String end, List<String> stopList, int stopSum) {
        Node currNode = townGraph.get(current);

        if(stopList.size() > 0 && current.equals(end)) { //Returns sum if the path is found
            return stopSum;
        }

        if (stopList.size() > 0 && stopList.contains(current)) { //Stops in the event of finding a cycle
            return null;
        }

        stopList.add(current);

        Integer shortestPath = null;
        for (Map.Entry<String, Integer> connection:currNode.getConnections().entrySet()) {
            List<String> newStopList = new ArrayList<>(stopList);
            int newStopSum = stopSum + connection.getValue();
            Integer newPathLength = getShortestPathHelper(connection.getKey(), end, newStopList, newStopSum);

            if (shortestPath == null ||
                    (newPathLength != null && newPathLength < shortestPath)) { //If two paths are the same length, only one is returned
                shortestPath = newPathLength;
            }
        }

        return shortestPath;
    }

    private void createNode(String nodeName) {
        Node node = new Node(nodeName);
        this.townGraph.put(nodeName, node);
    }
}
