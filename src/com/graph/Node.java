package com.graph;

import java.util.Arrays;
import java.util.HashMap;

public class Node {
    private String name;
    private HashMap<String, Integer> connections;

    protected Node(String name) {
        this.name = name;
        this.connections = new HashMap<>();
    }

    protected void addConnection(String nodeName, int distance) {
        this.connections.put(nodeName, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConnectionByName(String name) {
        if(connections.containsKey(name)) {
            return connections.get(name);
        } else {
            return -1;
        }
    }

    public String[] getNamesOfConnections() {
        Object[] connectionNames = connections.keySet().toArray();
        String[] stringNames = Arrays.copyOf(connectionNames, connectionNames.length, String[].class);

        return stringNames;
    }

    public HashMap<String, Integer> getConnections() {
        return connections;
    }

}
