package com.Rowdy.Apps.un_coding;

import android.content.*;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.util.*;
import android.support.v7.widget.Toolbar;

public class DFPRT extends AppCompatActivity
{
    Toolbar toolbarr;
    ExpandableListView eListView;
    ExpandableListAdapter eListAdapter;
    List<String> eListTitle;
    HashMap<String, List<String>> eListDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfprt);
        try
        {
            toolbarr = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbarr);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(null);
            eListView = (ExpandableListView) findViewById(R.id.elistview);
            eListDetail = getData();
            eListTitle = new ArrayList<String>(eListDetail.keySet());
            java.util.Collections.sort(eListTitle, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return extractInt(o1) - extractInt(o2);
                }

                int extractInt(String s) {
                    String num = s.replaceAll("\\D", "");
                    // return 0 if no digits found
                    return num.isEmpty() ? 0 : Integer.parseInt(num);
                }
            });
            eListAdapter = new CexListView(this, eListTitle, eListDetail);
            eListView.setAdapter(eListAdapter);
            eListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    //  on expanded any function
                }
            });

            eListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int groupPosition) {
                    //  on collapse any function
                }
            });
            eListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    int passType = getType(eListDetail.get(eListTitle.get(groupPosition)).get(childPosition).toString());
                    startActivity(new Intent(DFPRT.this, Extras.class).putExtra("openExtra", passType));
                    return true;
                }
            });
        }
        catch (Exception e){}
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    public HashMap<String, List<String>> getData()
    {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        for(int i=1;i<=30;i++)
        {
            String reward="D.F.P.R.T. "+i;
            List<String> dfptr = new ArrayList<String>();
            for(int j=1;j<=5;j++)
            {
                switch (j)
                {
                    case 1: dfptr.add("\t\t\tDEFINITION "+i);
                        break;
                    case 2: dfptr.add("\t\t\tFACT "+i);
                        break;
                    case 3: dfptr.add("\t\t\tPOINTS TO REMEMBER "+i);
                        break;
                    case 4: dfptr.add("\t\t\tREFERENCE "+i);
                        break;
                    case 5: dfptr.add("\t\t\tTIP "+i);
                        break;
                }
            }
            expandableListDetail.put(reward,dfptr);
        }
        return expandableListDetail;
    }
     public int getType(String clicked)
    {
        int type;
        if(clicked.indexOf("DEFINITION")>-1)
            type=1;
        else if(clicked.indexOf("FACT")>-1)
            type=2;
        else if(clicked.indexOf("POINT")>-1)
            type=3;
        else if(clicked.indexOf("REFERENCE")>-1)
            type=4;
        else
            type=5;

        for(int i=30;i>0;i--)
        {
            if(clicked.indexOf(""+i)>-1)
            {
                type=type*100+i;
                break;
            }
        }
        return type;
    }
    public void info(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DFPRT.this);
        alertDialogBuilder.setTitle("D.F.P.R.T");
        alertDialogBuilder.setMessage(R.string.dfprt).setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
