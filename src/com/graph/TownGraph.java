package com.graph;

import java.util.*;

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

    public String calculateDistanceBetweenElements(String[] nodeNames) {
        int distance = 0;
        for(int i = 0; i < nodeNames.length - 1; i++) {
            Node curr_node = townGraph.get(nodeNames[i]);
            int edgeLength = curr_node.getConnectionByName(nodeNames[i+1]);
            if (edgeLength == -1) {
                return "NO SUCH ROUTE";
            } else {
                distance += edgeLength;
            }
        }

        return String.valueOf(distance);
    }

    public Integer calculateNumTripsByNumStops(boolean exactLength, String begin, String end, int numStops) {
        return getNumPaths(exactLength, begin, end, numStops, new ArrayList<>());
    }

    public Integer getShortestPath(String begin, String end) {
        return getShortestPathHelper(begin, end, new ArrayList<String>(), 0);
    }

    public Integer getNumPaths(boolean exactLength, String current, String end, int numStops, List<String> stopList) {
        Node currNode = townGraph.get(current);
        stopList.add(current);
        Integer total = 0;
        if (current.equals(end) && stopList.size() != 1) {
            if (!exactLength || numStops == 0) { //If the flag to look for exact length is turned on, numStops must be 0 to add path
                total++;
            }
        }

        if (numStops > 0) {
            int newNumStops = numStops - 1;
            for (String nodeName:currNode.getNamesOfConnections()) {
                List<String> newStopList = new ArrayList<>(stopList);
                total += getNumPaths(exactLength, nodeName, end, newNumStops, newStopList);
            }
        }

        return total;
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
