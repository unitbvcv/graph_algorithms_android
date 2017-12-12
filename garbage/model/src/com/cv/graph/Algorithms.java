package com.cv.graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Algorithms {
    public class GenericGraphTraversalResult {
        private HashMap<Node, Node> predecessors = new HashMap<>();
        private HashMap<Node, Integer> orders = new HashMap<>();

        public HashMap<Node, Node> getPredecessors() {
            return predecessors;
        }

        public HashMap<Node, Integer> getOrders() {
            return orders;
        }
    }

    public class BreadthFirstTraversalResult {
        private HashMap<Node, Node> predecessors = new HashMap<>();
        private HashMap<Node, Integer> roadLengths = new HashMap<>();

        public HashMap<Node, Node> getPredecessors() {
            return predecessors;
        }

        public HashMap<Node, Integer> getRoadLengths() {
            return roadLengths;
        }
    }

    public class DepthFirstTraversalResult {
        private HashMap<Node, Node> predecessors = new HashMap<>();
        private HashMap<Node, Integer> visitedTimes = new HashMap<>();
        private HashMap<Node, Integer> analizedTimes = new HashMap<>();

        public HashMap<Node, Node> getPredecessor() {
            return predecessors;
        }

        public HashMap<Node, Integer> getVisitedTime() {
            return visitedTimes;
        }

        public HashMap<Node, Integer> getAnalizedTime() {
            return analizedTimes;
        }
    }

    public class MinimumPartialTreeResult {
        private ArrayList<WeightedEdge> treeEdges = new ArrayList<>();

        public ArrayList<WeightedEdge> getTreeEdges() {
            return treeEdges;
        }
    }

    public class BellmanFordDijkstraResult {
        private HashMap<Node, Integer> distances = new HashMap<>();
        private HashMap<Node, Integer> predecessors = new HashMap<>();

        public HashMap<Node, Integer> getDistances() {
            return distances;
        }

        public HashMap<Node, Integer> getPredecessors() {
            return predecessors;
        }
    }

    public class FloydWarshallResult {
        private HashMap<Node, HashMap<Node, Integer>> distances = new HashMap<>();
        private HashMap<Node, HashMap<Node, Integer>> predecessors = new HashMap<>();

        public HashMap<Node, HashMap<Node, Integer>> getDistances() {
            return distances;
        }

        public HashMap<Node, HashMap<Node, Integer>> getPredecessors() {
            return predecessors;
        }
    }

    public class EulerianResult {
        private ArrayList<Arc> eulerianRoad = new ArrayList<>();

        public ArrayList<Arc> getEulerianRoad() {
            return eulerianRoad;
        }
    }
}
