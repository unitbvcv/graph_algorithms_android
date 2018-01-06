package com.unitbv.cv.aggrafuri.ui;


import com.unitbv.cv.aggrafuri.graph.GraphModel;
import com.unitbv.cv.aggrafuri.graph.GraphType;

public class GraphView_ViewModel {
    GraphModel graphModel;
    GraphView graphView;
    GraphView_Model graphView_model;

    public GraphView_ViewModel(GraphModel graphModel, GraphView view, GraphView_Model viewModel) {
        this.graphModel = graphModel;
        this.graphView = view;
        this.graphView_model = viewModel;
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
        // logica principala
    }
}
