package com.Rowdy.Apps.un_coding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;

public class ListofProg extends AppCompatActivity
{
    Integer check,num;
    Questions qs;
    TextView topicname;
    ImageView logo;
    Extra ex;
    Toolbar toolbarl;
    ListView list;
    ArrayAdapter<String> listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_prog);
        try {
            ex = new Extra();
            toolbarl = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbarl);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            logo = (ImageView) findViewById(R.id.logo);
            topicname = (TextView) findViewById(R.id.topicname);
            qs = new Questions();
            check = getIntent().getIntExtra("topics", 0);
            logo.setBackgroundResource(ex.getLogo(check / 100));
            topicname.setText(ex.getTopic(check));
            num = ex.getNum(check);
            list = (ListView) findViewById(R.id.listofprog);
            ArrayList<String> List = new ArrayList<String>();
            listAdapter = new ArrayAdapter<String>(this, R.layout.customlistp, List);
            for (int i = 0; i < num; i++) {
                listAdapter.add(qs.getQuestion(check, i));
            }
            list.setAdapter(listAdapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    int pass = check;
                    pass = pass * 100;
                    pass += position;
                    startActivity(new Intent(ListofProg.this, Code.class).putExtra("file", pass));

                }
            });
        }
        catch (Exception e){}
    }
}



