package com.unitbv.cv.aggrafuri.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.unitbv.cv.aggrafuri.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set the default selection as Undirected Graph
        navigationView.getMenu().findItem(R.id.nav_graphview_undirected).setChecked(true);
        setNavigationButtonsState(R.id.nav_graphview_undirected);
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

    private void setNavigationButtonsState(int viewID) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu navigationMenu = navigationView.getMenu();
        GraphView graphView = (GraphView) findViewById(R.id.graphView);

        switch(viewID)
        {
            case R.id.nav_graphview_undirected:
                graphView.clear();
                graphView.setGraphType(GraphView.GraphViewType.UNDIRECTED);

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

            case R.id.nav_graphview_directed:
                graphView.clear();
                graphView.setGraphType(GraphView.GraphViewType.DIRECTED);

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

            case R.id.nav_graphview_undirected_weighted:
                graphView.clear();
                graphView.setGraphType(GraphView.GraphViewType.UNDIRECTED_WEIGHTED);

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

            case R.id.nav_graphview_directed_weighted:
                graphView.clear();
                graphView.setGraphType(GraphView.GraphViewType.DIRECTED_WEIGHTED);

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

            case R.id.nav_graphview_reset:
                graphView.clear();
                break;

            case R.id.nav_algorithm_1:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Title");

                // Set up the input
                final EditText input = new EditText(this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                break;

            case R.id.nav_algorithm_2:
                break;

            case R.id.nav_algorithm_3:
                break;

            case R.id.nav_algorithm_4:
                break;

            case R.id.nav_algorithm_5:
                break;

            case R.id.nav_algorithm_6:
                break;

            case R.id.nav_algorithm_7:
                break;

            case R.id.nav_algorithm_8:
                break;

            case R.id.nav_algorithm_9:
                break;

            case R.id.nav_algorithm_10:
                break;

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        setNavigationButtonsState(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
