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
        if (arc != null)
        {
            this.weight = weight;
            if (arc instanceof WeightedArc)
                ((WeightedArc)arc).setWeight(Double.parseDouble(weight.getMessage()));
            else
                arc = new WeightedArc(arc.getA(), arc.getB(), Double.parseDouble(weight.getMessage()));
        }
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

    public void setArrow_p1(LineParams arrow_p1) {
        this.arrow_p1 = arrow_p1;
    }

    public LineParams getArrow_P2() {
        return arrow_p2;
    }

    public void setArrow_P2(LineParams arrow_p2) {
        this.arrow_p2 = arrow_p2;
    }
}
