package com.unitbv.cv.aggrafuri.ui;

import com.unitbv.cv.aggrafuri.graph.Arc;
import com.unitbv.cv.aggrafuri.graph.WeightedArc;
import com.unitbv.cv.aggrafuri.graph.WeightedEdge;

public class ArcView {
    private LineParams line = null, smallLine1 = null, smallLine2 = null;
    private TextParams cost;
    private Arc arc = null;

    public ArcView() {}

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
        if (arc != null)
        {
            this.cost = cost;
            if (arc instanceof WeightedArc)
                ((WeightedArc)arc).setWeight(Double.parseDouble(cost.getMessage()));
            else
                arc = new WeightedArc(arc.getA(), arc.getB(), Double.parseDouble(cost.getMessage()));
        }
    }

    public Arc getArc() {
        return arc;
    }

    public void setArc(Arc arc) {
        this.arc = arc;
    }
}
