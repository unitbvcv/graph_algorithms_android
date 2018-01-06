package com.unitbv.cv.aggrafuri.ui;

import android.view.View;

import com.unitbv.cv.aggrafuri.graph.Arc;
import com.unitbv.cv.aggrafuri.graph.Edge;
import com.unitbv.cv.aggrafuri.graph.GraphType;
import com.unitbv.cv.aggrafuri.graph.Node;
import com.unitbv.cv.aggrafuri.graph.WeightedArc;
import com.unitbv.cv.aggrafuri.graph.WeightedEdge;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphView_Model {

    private HashMap<Node, NodeView> nodes = new HashMap<>();
    private HashMap<Edge, EdgeView> edges = new HashMap<>();
    private HashMap<Arc, ArcView> arcs = new HashMap<>();

    private GraphType type = GraphType.NOT_SET;

    public GraphView_Model(GraphType type)
    {
        this.type = type;
    }

    public GraphType getType() {
        return type;
    }

    public void setType(GraphType type) {
        this.type = type;
        clear();
    }

    public void clear()
    {
        nodes.clear();
        edges.clear();
        arcs.clear();
    }

    public HashMap<Node, NodeView> getNodes() {
        return nodes;
    }

    public HashMap<Edge, EdgeView> getEdges() {
        return edges;
    }

    public HashMap<Arc, ArcView> getArcs() {
        return arcs;
    }

    public void addNode(Node node, float x, float y)
    {
        // trebuie construit un TextParams cu id-ul nodului, x si y
        // trebuie construit un ArcParams raportat la x si y si folosind raza
    }

    public void addEdge(Edge edge)
    {
        // trebuie construit LineParams: se afla pe care 2 noduri le leaga edge-ul, se afla coordonatele lor si se calculeaza punctele
    }

    public void addArc(Arc arc)
    {
        // trebuie construit LineParams: se afla pe care 2 noduri le leaga arcul, se afla coordonatele lor si se calculeaza punctele
        // trebuie construit LineParams pt arrow parts
    }

    public void addWeightedEdge(WeightedEdge weightedEdge)
    {
        // trebuie construit LineParams: se afla pe care 2 noduri le leaga edge-ul, se afla coordonatele lor si se calculeaza punctele
        // trebuie construit un TextParams cu costul edge-ului, x si y
        // trebuie calculate x si y in functie de unghiul liniei
    }

    public void addWeightedArc(WeightedArc weightedArc)
    {
        // trebuie construit LineParams: se afla pe care 2 noduri le leaga arcul, se afla coordonatele lor si se calculeaza punctele
        // trebuie construit LineParams pt arrow parts
        // trebuie construit un TextParams cu costul edge-ului, x si y
        // trebuie calculate x si y in functie de unghiul liniei
    }

    public ArrayList<Object> generateParamsLists()
    {
        ArrayList<Object> result = new ArrayList<>();

        ArrayList<ArcParams> arcParamsList = new ArrayList<>();
        ArrayList<LineParams> lineParamsList = new ArrayList<>();
        ArrayList<TextParams> textParamsList = new ArrayList<>();

        nodes.forEach((node, nodeView) -> {
            arcParamsList.add(nodeView.getArc());
            textParamsList.add(nodeView.getText());
        });

        edges.forEach((edge, edgeView) -> {
            lineParamsList.add(edgeView.getLine());
            TextParams weightParams = edgeView.getWeight();
            if (weightParams != null)
                textParamsList.add(weightParams);
        });

        arcs.forEach((arc, arcView) -> {
            lineParamsList.add(arcView.getLine());
            lineParamsList.add(arcView.getArrow_P1());
            lineParamsList.add(arcView.getArrow_P2());
            TextParams weightParams = arcView.getWeight();
            if (weightParams != null)
                textParamsList.add(weightParams);
        });

        result.add(arcParamsList);
        result.add(lineParamsList);
        result.add(textParamsList);
        return result;
    }

}
