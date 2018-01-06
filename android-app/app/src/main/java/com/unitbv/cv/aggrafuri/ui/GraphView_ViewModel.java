package com.unitbv.cv.aggrafuri.ui;

import android.view.MotionEvent;
import android.view.View;

import com.unitbv.cv.aggrafuri.graph.GraphModel;

public class GraphView_ViewModel {
    GraphModel graphModel;
    GraphView graphView;
    GraphView_Model graphView_model;

    public GraphView_ViewModel(GraphModel graphModel, GraphView view, GraphView_Model viewModel) {

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
}
