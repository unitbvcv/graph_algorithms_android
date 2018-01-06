package com.unitbv.cv.aggrafuri.ui;

import android.graphics.RectF;
import com.unitbv.cv.aggrafuri.graph.GraphModel;
import com.unitbv.cv.aggrafuri.graph.GraphType;
import com.unitbv.cv.aggrafuri.graph.Node;

import java.util.Map;

public class GraphView_ViewModel {
    private GraphModel graphModel;
    private GraphView graphView;
    private GraphView_Model graphView_model;

    private Node selectedNode = null;

    public GraphView_ViewModel(GraphModel graphModel, GraphView view, GraphView_Model viewModel) {
        this.graphModel = graphModel;
        this.graphView = view;
        this.graphView_model = viewModel;

        graphView.setView_viewModel(this);
    }

    public GraphModel getGraphModel() {
        return graphModel;
    }

    public GraphView getGraphView() {
        return graphView;
    }

    public GraphView_Model getGraphView_model() {
        return graphView_model;
    }

    public void setGraphType(GraphType type)
    {
        graphModel.setType(type);
        graphView_model.setType(type);
        graphView.clear();
    }

    public void onViewTouch(float x, float y)
    {
        // check if we touched a node
        for (Map.Entry<Node, NodeView> entry : graphView_model.getNodes().entrySet()) {
            ArcParams nodeParams = entry.getValue().getArc();
            RectF entryRect = new RectF(nodeParams.getLeft(), nodeParams.getTop(), nodeParams.getRight(), nodeParams.getBottom());

            // if the current node is the one touched
            if (entryRect.contains(x, y)) {
                // if we have another node selected
                if (selectedNode != null) {
                    // deselect the selected node
                    graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                    selectedNode = null;

                    // TODO: draw a line between the nodes (selectedNode to currentNode)
                }
                // if we don't have another node selected
                else {
                    selectedNode = entry.getValue().getNode();
                    nodeParams.setSelected(true);
                }

                return;
            }
        }

        // we didn't touch a node
        // if we have a node already selected, then deselect it
        if (selectedNode != null) {
            graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
            selectedNode = null;
        }
        // if we don't have a node already selected, then draw a new node
        else {
            // TODO: draw a new node
        }
    }
}
