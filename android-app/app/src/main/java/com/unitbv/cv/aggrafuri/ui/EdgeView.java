package com.unitbv.cv.aggrafuri.ui;

import com.unitbv.cv.aggrafuri.graph.Edge;
import com.unitbv.cv.aggrafuri.graph.WeightedEdge;

public class EdgeView {
    private LineParams line = null;
    private TextParams cost = null;
    private Edge edge = null;

    public EdgeView() {}

    public LineParams getLine() {
        return line;
    }

    public void setLine(LineParams line) {
        this.line = line;
    }

    public TextParams getCost() {
        return cost;
    }

    public void setCost(TextParams cost) {
        if (edge != null)
        {
            this.cost = cost;
            if (edge instanceof WeightedEdge)
                ((WeightedEdge)edge).setWeight(Double.parseDouble(cost.getMessage()));
            else
                edge = new WeightedEdge(edge.getA(), edge.getB(), Double.parseDouble(cost.getMessage()));
        }
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}
