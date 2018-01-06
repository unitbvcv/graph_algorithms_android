package com.unitbv.cv.aggrafuri.ui;

import com.unitbv.cv.aggrafuri.graph.Arc;
import com.unitbv.cv.aggrafuri.graph.WeightedArc;

public class ArcView {
    private LineParams line = null, arrow_p1 = null, arrow_p2 = null;
    private TextParams weight;
    private Arc arc = null;

    public ArcView() {}

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

    public Arc getArc() {
        return arc;
    }

    public void setArc(Arc arc) {
        this.arc = arc;
    }

    public LineParams getArrow_P1() {
        return arrow_p1;
    }

    public void setArrow_P1(LineParams arrow_p1) {
        this.arrow_p1 = arrow_p1;
    }

    public LineParams getArrow_P2() {
        return arrow_p2;
    }

    public void setArrow_P2(LineParams arrow_p2) {
        this.arrow_p2 = arrow_p2;
    }
}
