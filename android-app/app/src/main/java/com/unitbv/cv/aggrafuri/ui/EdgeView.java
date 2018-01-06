package com.unitbv.cv.aggrafuri.ui;

import com.unitbv.cv.aggrafuri.graph.Edge;
import com.unitbv.cv.aggrafuri.graph.WeightedEdge;

public class EdgeView {
    private LineParams line = null;
    private TextParams weight = null;
    private Edge edge = null;

    public EdgeView() {}

    public LineParams getLine() {
        return line;
    }

    public void setLine(LineParams line) {
        this.line = line;
    }

    public TextParams getWeight() {
        return weight;
    }

    public void setWeight(TextParams weight) {
        this.weight = weight;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}
