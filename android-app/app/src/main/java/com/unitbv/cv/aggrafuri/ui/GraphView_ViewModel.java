package com.unitbv.cv.aggrafuri.ui;

import android.content.Context;
import android.graphics.RectF;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.unitbv.cv.aggrafuri.graph.Arc;
import com.unitbv.cv.aggrafuri.graph.Edge;
import com.unitbv.cv.aggrafuri.graph.GraphModel;
import com.unitbv.cv.aggrafuri.graph.GraphType;
import com.unitbv.cv.aggrafuri.graph.Node;
import com.unitbv.cv.aggrafuri.graph.WeightedArc;
import com.unitbv.cv.aggrafuri.graph.WeightedEdge;

import java.util.ArrayList;
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
        graphView_model.setGraphView_viewModel(this);
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

    public Node getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(Node selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onViewTouch(float x, float y)
    {
        boolean nodeTouched = false;

        // check if we touched a node
        for (Map.Entry<Node, NodeView> entry : graphView_model.getNodes().entrySet()) {
            ArcParams nodeParams = entry.getValue().getArc();
            RectF entryRect = new RectF(nodeParams.getLeft(), nodeParams.getTop(), nodeParams.getRight(), nodeParams.getBottom());

            // if the current node is the one touched
            if (entryRect.contains(x, y)) {
                nodeTouched = true;
                // if we have another node selected
                if (selectedNode != null) {
                    // deselect the selected node
                    // if the selectedNode is not the same as the current node, create an edge
                    // at the end deselect the current node
                    if (selectedNode != entry.getKey()) {
                        Edge edgeToFind = graphModel.getGraph().getEdge(selectedNode, entry.getKey());
                        if (edgeToFind != null) {
                            switch (graphView_model.getType()) {
                                case UNDIRECTED_WEIGHTED: {
                                    graphView.promptDialog("Edit weighted edge", "Please insert the new weight of the edge:", new AlertDialogInterface() {
                                        EditText inputView;

                                        @Override
                                        public View onBuildDialog(Context context) {
                                            inputView = new EditText(context);
                                            inputView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                            return inputView;
                                        }

                                        @Override
                                        public void onCancel() {

                                        }

                                        @Override
                                        public void onResult(View view) {
                                            ((WeightedEdge)edgeToFind).setWeight(Double.parseDouble(inputView.getText().toString()));
                                            graphView_model.getEdges().get(edgeToFind).getWeight().setMessage(Double.valueOf(inputView.getText().toString()).toString());
                                            graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                                            selectedNode = null;
                                            sendDataToViewAndInvalidate();
                                        }
                                    });
                                    break;
                                }
                                case DIRECTED_WEIGHTED: {
                                    graphView.promptDialog("Edit weighted arc", "Please insert the new weight of the arc:", new AlertDialogInterface() {
                                        EditText inputView;

                                        @Override
                                        public View onBuildDialog(Context context) {
                                            inputView = new EditText(context);
                                            inputView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                            return inputView;
                                        }

                                        @Override
                                        public void onCancel() {

                                        }

                                        @Override
                                        public void onResult(View view) {
                                            ((WeightedArc)edgeToFind).setWeight(Double.parseDouble(inputView.getText().toString()));
                                            graphView_model.getArcs().get(edgeToFind).getWeight().setMessage(Double.valueOf(inputView.getText().toString()).toString());
                                            graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                                            selectedNode = null;
                                            sendDataToViewAndInvalidate();
                                        }
                                    });
                                    break;
                                }
                            }
                        }
                        else {
                            switch (graphView_model.getType()) {
                                case UNDIRECTED: {
                                    Edge newEdge = new Edge(selectedNode, entry.getValue().getNode());
                                    graphView_model.addEdge(newEdge);
                                    graphModel.getGraph().addEdge(newEdge);
                                    graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                                    selectedNode = null;
                                    sendDataToViewAndInvalidate();
                                    break;
                                }
                                case DIRECTED: {
                                    Arc newArc = new Arc(selectedNode, entry.getValue().getNode());
                                    graphView_model.addArc(newArc);
                                    graphModel.getGraph().addEdge(newArc);
                                    graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                                    selectedNode = null;
                                    sendDataToViewAndInvalidate();
                                    break;
                                }
                                case UNDIRECTED_WEIGHTED: {
                                    graphView.promptDialog("Add weighted edge", "Please insert the weight of the edge:", new AlertDialogInterface() {
                                        EditText inputView;

                                        @Override
                                        public View onBuildDialog(Context context) {
                                            inputView = new EditText(context);
                                            inputView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                            return inputView;
                                        }

                                        @Override
                                        public void onCancel() {

                                        }

                                        @Override
                                        public void onResult(View view) {
                                            WeightedEdge newWeightedEdge = new WeightedEdge(selectedNode,
                                                    entry.getKey(), Double.parseDouble(inputView.getText().toString()));
                                            graphView_model.addWeightedEdge(newWeightedEdge);
                                            graphModel.getGraph().addEdge(newWeightedEdge);
                                            graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                                            selectedNode = null;
                                            sendDataToViewAndInvalidate();
                                        }
                                    });
                                    break;
                                }
                                case DIRECTED_WEIGHTED: {
                                    graphView.promptDialog("Add weighted arc", "Please insert the weight of the arc:", new AlertDialogInterface() {
                                        EditText inputView;

                                        @Override
                                        public View onBuildDialog(Context context) {
                                            inputView = new EditText(context);
                                            inputView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                                            return inputView;
                                        }

                                        @Override
                                        public void onCancel() {

                                        }

                                        @Override
                                        public void onResult(View view) {
                                            WeightedArc newWeightedArc = new WeightedArc(selectedNode,
                                                    entry.getValue().getNode(), Double.parseDouble(inputView.getText().toString()));
                                            graphView_model.addWeightedArc(newWeightedArc);
                                            graphModel.getGraph().addEdge(newWeightedArc);
                                            graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                                            selectedNode = null;
                                            sendDataToViewAndInvalidate();
                                        }
                                    });
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                        selectedNode = null;
                        sendDataToViewAndInvalidate();
                    }
                }
                // if we don't have another node selected
                else {
                    selectedNode = entry.getValue().getNode();
                    nodeParams.setSelected(true);
                    sendDataToViewAndInvalidate();
                }

                break;
            }
        }

        // we didn't touch a node
        if (nodeTouched == false) {
            // if we have a node already selected, then deselect it
            if (selectedNode != null) {
                graphView_model.getNodes().get(selectedNode).getArc().setSelected(false);
                selectedNode = null;
                sendDataToViewAndInvalidate();
            }
            // if we don't have a node already selected, then draw a new node
            else {
                graphView.promptDialog("Add node", "Please insert the node name:", new AlertDialogInterface() {
                    EditText inputView;

                    @Override
                    public View onBuildDialog(Context context) {
                        inputView = new EditText(context);
                        return inputView;
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onResult(View view) {
                        Node newNode = new Node(inputView.getText().toString());
                        if (!graphModel.getGraph().containsNode(newNode)) {
                            graphView_model.addNode(newNode, x, y);
                            graphModel.getGraph().addNode(newNode);
                            sendDataToViewAndInvalidate();
                        }
                    }
                });
            }
        }
    }

    public void clear() {
        graphModel.clear();
        graphView_model.clear();
        graphView.clear();
    }

    public boolean isEmpty() {
        return graphView_model.getNodes().isEmpty();
    }

    public void sendDataToViewAndInvalidate() {
        // send the view the new data then invalidate the view
        ArrayList<Object> result = graphView_model.generateParamsLists();
        ArrayList<ArcParams> arcParamsList = (ArrayList<ArcParams>)result.get(0);
        ArrayList<LineParams> lineParamsList = (ArrayList<LineParams>)result.get(1);
        ArrayList<TextParams> textParamsList = (ArrayList<TextParams>)result.get(2);
        graphView.setArcs(arcParamsList);
        graphView.setLines(lineParamsList);
        graphView.setTexts(textParamsList);
        graphView.invalidate();
    }
}
