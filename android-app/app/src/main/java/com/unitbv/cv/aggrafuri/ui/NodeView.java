package com.unitbv.cv.aggrafuri.ui;

import com.unitbv.cv.aggrafuri.graph.Node;

class NodeView {
    private ArcParams arc = null;
    private TextParams text = null;
    private Node node = null;

    public NodeView() {}

    public ArcParams getArc() {
        return arc;
    }

    public void setArc(ArcParams arc) {
        this.arc = arc;
    }

    public TextParams getText() {
        return text;
    }

    public void setText(TextParams text) {
        this.text = text;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
