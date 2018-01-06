package com.unitbv.cv.aggrafuri.ui;

import android.support.annotation.NonNull;
import android.view.View;

import com.unitbv.cv.aggrafuri.graph.Arc;
import com.unitbv.cv.aggrafuri.graph.Edge;
import com.unitbv.cv.aggrafuri.graph.GraphType;
import com.unitbv.cv.aggrafuri.graph.Node;
import com.unitbv.cv.aggrafuri.graph.WeightedArc;
import com.unitbv.cv.aggrafuri.graph.WeightedEdge;
import com.unitbv.cv.aggrafuri.utils.Math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

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
        NodeView newNode = new NodeView();

        // oh no! magic numbers!
        TextParams nodeID = new TextParams(node.getID(), x - 10, y - 10);

        ArcParams arcParams = new ArcParams(x - GraphView.NODE_CIRCLE_RADIUS, y - GraphView.NODE_CIRCLE_RADIUS,
                x + GraphView.NODE_CIRCLE_RADIUS, y + GraphView.NODE_CIRCLE_RADIUS, 0.0f, 360.0f, false);

        newNode.setNode(node);
        newNode.setArc(arcParams);
        newNode.setText(nodeID);

        nodes.put(node, newNode);
    }

    public void addEdge(Edge edge)
    {
        EdgeView newEdge = new EdgeView();

        LineParams lineParams = getLineParams(edge);

        newEdge.setEdge(edge);
        newEdge.setLine(lineParams);

        edges.put(edge, newEdge);
    }

    @NonNull
    private LineParams getLineParams(Edge edge) {
        ArcParams node1 = nodes.get(edge.getA()).getArc();
        ArcParams node2 = nodes.get(edge.getB()).getArc();

        Vector<Integer> node1Center = Math.centerOfRectangle((int) node1.getLeft(),(int) node1.getTop(),(int) node1.getRight(),(int) node1.getBottom());
        Vector<Integer> node2Center = Math.centerOfRectangle((int) node2.getLeft(),(int) node2.getTop(),(int) node2.getRight(),(int) node2.getBottom());

        Vector<Float> tangentPoints = Math.generateCirclesConnectionPoints(node1Center.get(0), node1Center.get(1), GraphView.NODE_CIRCLE_RADIUS,
                node2Center.get(0), node2Center.get(1), GraphView.NODE_CIRCLE_RADIUS);

        LineParams lineParams = new LineParams();

        lineParams.setStartX(tangentPoints.get(0));
        lineParams.setStartY(tangentPoints.get(1));
        lineParams.setStopX(tangentPoints.get(2));
        lineParams.setStopY(tangentPoints.get(3));
        return lineParams;
    }

    public void addArc(Arc arc)
    {
        ArcView newArc = new ArcView();

        // trebuie construit LineParams: se afla pe care 2 noduri le leaga arcul, se afla coordonatele lor si se calculeaza punctele
        LineParams lineParams = getLineParams(arc);

        // trebuie construit LineParams pt arrow parts
        Vector<LineParams> arrowLegLineParams = getArrowLegsLineParams(lineParams);

        newArc.setArc(arc);
        newArc.setLine(lineParams);
        newArc.setArrow_P1(arrowLegLineParams.get(0));
        newArc.setArrow_P2(arrowLegLineParams.get(1));

        arcs.put(arc, newArc);
    }

    private Vector<LineParams> getArrowLegsLineParams(LineParams lineParams)
    {
        Vector<LineParams> result = new Vector<>(2);

        LineParams arrow1 = new LineParams();
        LineParams arrow2 = new LineParams();

        Vector<Float> arrowLegsCoords = Math.generateArrowLegsCoordinates(lineParams.getStartX(), lineParams.getStartY(),
                lineParams.getStopX(), lineParams.getStopY(), GraphView.ARROW_LEG_LENGTH, GraphView.ARROW_LEG_ANGLE);

        arrow1.setStartX(lineParams.getStopX());
        arrow1.setStartY(lineParams.getStopY());
        arrow1.setStopX(arrowLegsCoords.get(0));
        arrow1.setStopY(arrowLegsCoords.get(1));

        arrow2.setStartX(lineParams.getStopX());
        arrow2.setStartY(lineParams.getStopY());
        arrow2.setStopX(arrowLegsCoords.get(2));
        arrow2.setStopY(arrowLegsCoords.get(3));

        result.add(arrow1);
        result.add(arrow2);
        return result;
    }

    public void addWeightedEdge(WeightedEdge weightedEdge)
    {
        EdgeView newEdge = new EdgeView();

        // trebuie construit LineParams: se afla pe care 2 noduri le leaga edge-ul, se afla coordonatele lor si se calculeaza punctele
        LineParams lineParams = getLineParams(weightedEdge);

        // trebuie construit un TextParams cu costul edge-ului, x si y
        // trebuie calculate x si y in functie de unghiul liniei
        TextParams weightParams = new TextParams();


        newEdge.setEdge(weightedEdge);
        newEdge.setLine(lineParams);
        newEdge.setWeight(weightParams);
        edges.put(weightedEdge, newEdge);
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
