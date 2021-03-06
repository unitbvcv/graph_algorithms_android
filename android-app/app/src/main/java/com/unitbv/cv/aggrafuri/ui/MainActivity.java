package com.unitbv.cv.aggrafuri.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.unitbv.cv.aggrafuri.R;
import com.unitbv.cv.aggrafuri.graph.Algorithms;
import com.unitbv.cv.aggrafuri.graph.DirectedGraph;
import com.unitbv.cv.aggrafuri.graph.DirectedWeightedGraph;
import com.unitbv.cv.aggrafuri.graph.GraphModel;
import com.unitbv.cv.aggrafuri.graph.GraphType;
import com.unitbv.cv.aggrafuri.graph.Node;
import com.unitbv.cv.aggrafuri.graph.UndirectedWeightedGraph;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GraphView_ViewModel graphView_viewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set the default selection as Undirected Graph
        navigationView.getMenu().findItem(R.id.nav_graphview_undirected).setChecked(true);
        selectNavigationButton(R.id.nav_graphview_undirected);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void selectNavigationButton(int buttonID) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu navigationMenu = navigationView.getMenu();
        GraphView graphView = (GraphView) findViewById(R.id.graphView);
        if (graphView_viewModel == null) {
            graphView_viewModel = new GraphView_ViewModel(
                    new GraphModel(GraphType.UNDIRECTED),
                    graphView,
                    new GraphView_Model(GraphType.UNDIRECTED) );
        }

        switch(buttonID)
        {
            case R.id.nav_graphview_undirected: {
                graphView_viewModel.setGraphType(GraphType.UNDIRECTED);

                navigationMenu.findItem(R.id.nav_algorithm_1).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_2).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_3).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_4).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_5).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_6).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_7).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_8).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_9).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_10).setEnabled(false);
                break;
            }

            case R.id.nav_graphview_directed: {
                graphView_viewModel.setGraphType(GraphType.DIRECTED);

                navigationMenu.findItem(R.id.nav_algorithm_1).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_2).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_3).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_4).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_5).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_6).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_7).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_8).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_9).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_10).setEnabled(true);
                break;
            }

            case R.id.nav_graphview_undirected_weighted: {
                graphView_viewModel.setGraphType(GraphType.UNDIRECTED_WEIGHTED);

                navigationMenu.findItem(R.id.nav_algorithm_1).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_2).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_3).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_4).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_5).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_6).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_7).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_8).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_9).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_10).setEnabled(false);
                break;
            }

            case R.id.nav_graphview_directed_weighted: {
                graphView_viewModel.setGraphType(GraphType.DIRECTED_WEIGHTED);

                navigationMenu.findItem(R.id.nav_algorithm_1).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_2).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_3).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_4).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_5).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_6).setEnabled(false);
                navigationMenu.findItem(R.id.nav_algorithm_7).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_8).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_9).setEnabled(true);
                navigationMenu.findItem(R.id.nav_algorithm_10).setEnabled(false);
                break;
            }

            case R.id.nav_graphview_reset: {
                graphView_viewModel.clear();
                break;
            }

            // Generic Graph Traversal
            case R.id.nav_algorithm_1: {
                graphView.promptDialog("Generic Graph Traversal: Input", "Please insert the start node name:", new AlertDialogInterface() {
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
                        String input = inputView.getText().toString();
                        Node startNode = new Node(input);

                        String output;
                        if (graphView_viewModel.getGraphModel().getGraph().getNodes().contains(startNode)) {
                            output = Algorithms.GenericGraphTraversal(graphView_viewModel.getGraphModel().getGraph(), startNode).toString();
                        } else {
                            output = "Start node not found!";
                        }

                        graphView.promptDialog("Generic Graph Traversal: Output", output, new AlertDialogInterface() {
                            TextView outputView;

                            @Override
                            public View onBuildDialog(Context context) {
                                outputView = new TextView(context);
                                return outputView;
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onResult(View view) {

                            }
                        });
                    }
                });

                break;
            }

            // Breadth First Traversal
            case R.id.nav_algorithm_2: {
                graphView.promptDialog("Breadth First Traversal: Input", "Please insert the start node name:", new AlertDialogInterface() {
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
                        String input = inputView.getText().toString();
                        Node startNode = new Node(input);

                        String output;
                        if (graphView_viewModel.getGraphModel().getGraph().getNodes().contains(startNode)) {
                            output = Algorithms.BreadthFirstTraversal(graphView_viewModel.getGraphModel().getGraph(), startNode).toString();
                        } else {
                            output = "Start node not found!";
                        }

                        graphView.promptDialog("Breadth First Traversal: Output", output, new AlertDialogInterface() {
                            TextView outputView;

                            @Override
                            public View onBuildDialog(Context context) {
                                outputView = new TextView(context);
                                return outputView;
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onResult(View view) {

                            }
                        });
                    }
                });

                break;
            }

            // Depth First Traversal
            case R.id.nav_algorithm_3: {
                graphView.promptDialog("Depth First Traversal: Input", "Please insert the start node name:", new AlertDialogInterface() {
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
                        String input = inputView.getText().toString();
                        Node startNode = new Node(input);

                        String output;
                        if (graphView_viewModel.getGraphModel().getGraph().getNodes().contains(startNode)) {
                            output = Algorithms.DepthFirstTraversal(graphView_viewModel.getGraphModel().getGraph(), startNode).toString();
                        } else {
                            output = "Start node not found!";
                        }

                        graphView.promptDialog("Depth First Traversal: Output", output, new AlertDialogInterface() {
                            TextView outputView;

                            @Override
                            public View onBuildDialog(Context context) {
                                outputView = new TextView(context);
                                return outputView;
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onResult(View view) {

                            }
                        });
                    }
                });

                break;
            }

            // Prim's Algorithm
            case R.id.nav_algorithm_4: {
                graphView.promptDialog("Prim's Algorithm: Input", "Please insert the start node name:", new AlertDialogInterface() {
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
                        String input = inputView.getText().toString();
                        Node startNode = new Node(input);

                        String output;
                        if (graphView_viewModel.getGraphModel().getGraph().getNodes().contains(startNode)) {
                            output = Algorithms.PrimsAlgorithm((UndirectedWeightedGraph) graphView_viewModel.getGraphModel().getGraph(), startNode).toString();
                        } else {
                            output = "Start node not found!";
                        }

                        graphView.promptDialog("Prim's Algorithm: Output", output, new AlertDialogInterface() {
                            TextView outputView;

                            @Override
                            public View onBuildDialog(Context context) {
                                outputView = new TextView(context);
                                return outputView;
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onResult(View view) {

                            }
                        });
                    }
                });

                break;
            }

            // Kruskal's Algorithm
            case R.id.nav_algorithm_5: {
                String output;

                if (graphView_viewModel.getGraphModel().hasNodes()) {
                    output = Algorithms.KruskalAlgorithm((UndirectedWeightedGraph) graphView_viewModel.getGraphModel().getGraph()).toString();
                }
                else {
                    output = "The graph has no nodes!";
                }

                graphView.promptDialog("Kruskal's Algorithm: Output", output, new AlertDialogInterface() {
                    TextView outputView;

                    @Override
                    public View onBuildDialog(Context context) {
                        outputView = new TextView(context);
                        return outputView;
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onResult(View view) {

                    }
                });

                break;
            }

            // Boruvka's Algorithm
            case R.id.nav_algorithm_6: {
                String output;

                if (graphView_viewModel.getGraphModel().hasNodes()) {
                    output = Algorithms.BoruvkaAlgorithm((UndirectedWeightedGraph) graphView_viewModel.getGraphModel().getGraph()).toString();
                }
                else {
                    output = "The graph has no nodes!";
                }

                graphView.promptDialog("Boruvka's Algorithm: Output", output, new AlertDialogInterface() {
                    TextView outputView;

                    @Override
                    public View onBuildDialog(Context context) {
                        outputView = new TextView(context);
                        return outputView;
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onResult(View view) {

                    }
                });
                break;
            }

            // Dijkstra's Algorithm
            case R.id.nav_algorithm_7: {
                graphView.promptDialog("Dijkstra's Algorithm: Input", "Please insert the start node name:", new AlertDialogInterface() {
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
                        String input = inputView.getText().toString();
                        Node startNode = new Node(input);

                        String output;
                        if (graphView_viewModel.getGraphModel().getGraph().getNodes().contains(startNode)) {
                            output = Algorithms.DijkstraAlgorithm((DirectedWeightedGraph) graphView_viewModel.getGraphModel().getGraph(), startNode).toString();
                        } else {
                            output = "Start node not found!";
                        }

                        graphView.promptDialog("Dijkstra's Algorithm: Output", output, new AlertDialogInterface() {
                            TextView outputView;

                            @Override
                            public View onBuildDialog(Context context) {
                                outputView = new TextView(context);
                                return outputView;
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onResult(View view) {

                            }
                        });
                    }
                });

                break;
            }

            // Bellman-Ford Algorithm
            case R.id.nav_algorithm_8: {
                graphView.promptDialog("Bellman-Ford Algorithm: Input", "Please insert the start node name:", new AlertDialogInterface() {
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
                        String input = inputView.getText().toString();
                        Node startNode = new Node(input);
                        String output = Algorithms.BellmanFordAlgorithm((DirectedWeightedGraph) graphView_viewModel.getGraphModel().getGraph(), startNode).toString();

                        graphView.promptDialog("Bellman-Ford Algorithm: Output", output, new AlertDialogInterface() {
                            TextView outputView;

                            @Override
                            public View onBuildDialog(Context context) {
                                outputView = new TextView(context);
                                return outputView;
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onResult(View view) {

                            }
                        });
                    }
                });

                break;
            }

            // Floyd-Warshall Algorithm
            case R.id.nav_algorithm_9: {
                String output;

                if (graphView_viewModel.getGraphModel().hasNodes()) {
                    output = Algorithms.FloydWarshallAlgorithm((DirectedWeightedGraph) graphView_viewModel.getGraphModel().getGraph()).toString();
                } else {
                    output = "The graph has no nodes!";
                }

                graphView.promptDialog("Floyd-Warshall Algorithm: Output", output, new AlertDialogInterface() {
                    TextView outputView;

                    @Override
                    public View onBuildDialog(Context context) {
                        outputView = new TextView(context);
                        return outputView;
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onResult(View view) {

                    }
                });

                break;
            }

            // Eulerian Circuit Algorithm
            case R.id.nav_algorithm_10: {
                graphView.promptDialog("Eulerian Circuit Algorithm: Input", "Please insert the start node name:", new AlertDialogInterface() {
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
                        String input = inputView.getText().toString();
                        Node startNode = new Node(input);

                        String output;
                        if (graphView_viewModel.getGraphModel().getGraph().getNodes().contains(startNode)) {
                            output = Algorithms.EulerianCircuit((DirectedGraph) graphView_viewModel.getGraphModel().getGraph(), startNode).toString();
                        } else {
                            output = "Start node not found!";
                        }

                        graphView.promptDialog("Eulerian Circuit Algorithm: Output", output, new AlertDialogInterface() {
                            TextView outputView;

                            @Override
                            public View onBuildDialog(Context context) {
                                outputView = new TextView(context);
                                return outputView;
                            }

                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onResult(View view) {

                            }
                        });
                    }
                });

                break;
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        selectNavigationButton(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
