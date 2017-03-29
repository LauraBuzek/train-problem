package com.graph;

import java.util.HashMap;

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
        Node beginNode = townGraph.get(begin);

        int numTrips = 0;
        if (numStops > 1) {
            numStops -= 1;
            for (String nodeName : beginNode.getConnections()) {
                numTrips += calculateNumTripsByNumStopsHelper(nodeName, end, numStops);
            }
        }
        return numTrips;
    }

    public Integer calculateNumTripsByNumStopsHelper(String current, String end, int numStops) {
        Node currNode = townGraph.get(current);

        int numTrips = 0;
        if (current.equals(end)) {
            numTrips += 1;
        }

        if (numStops > 0) {
            int newNumStops = numStops - 1;
            for (String nodeName:currNode.getConnections()) {
                numTrips += calculateNumTripsByNumStopsHelper(nodeName, end, newNumStops);
            }
        }

        return numTrips;

    }


    private void createNode(String nodeName) {
        Node node = new Node(nodeName);
        this.townGraph.put(nodeName, node);
    }
}
