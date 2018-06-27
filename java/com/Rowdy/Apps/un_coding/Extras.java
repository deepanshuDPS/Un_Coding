package com.Rowdy.Apps.un_coding;

import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.ArrayList;

public class Extras extends AppCompatActivity {

    Ids fileid;
    Toolbar toolbari;
    Integer file;
    InputStream is;
    BufferedReader bf;
    String line,entireFile="";
    String data[]=new String[200];
    ListView list;
    ArrayAdapter<String> listAdapter ;
    LinearLayout practoolbar;
    TextView qpt;
    Boolean back=false;
    FullAds fullAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
        try {
            toolbari = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            practoolbar = (LinearLayout) findViewById(R.id.practoolbar);
            setSupportActionBar(toolbari);
            int type = getIntent().getIntExtra("openExtra", 0);
            list = (ListView) findViewById(R.id.listviewiq);
            ArrayList<String> List = new ArrayList<String>();
            if (type != 3) {
                practoolbar.setVisibility(View.GONE);
                back = true;
            }
            getSupportActionBar().setHomeButtonEnabled(back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(back);
            listAdapter = new ArrayAdapter<String>(this, R.layout.customlist, List);
            fileid = new Ids();
            if (type == 0) {
                getSupportActionBar().setTitle("INTERVIEW QUESTIONS");
                toolbari.setBackgroundColor(Color.parseColor("#8000ff"));
                data = readFile(R.raw.intques, "~~~");
                for (int i = 0; i < data.length; i++) {
                    listAdapter.add(data[i]);
                }
            } else if (type == 1) {
                listAdapter = new ArrayAdapter<String>(this, R.layout.customlists, List);
                getSupportActionBar().setTitle("SAVED FILES");
                toolbari.setBackgroundColor(Color.parseColor("#00BFFF"));
                String path = Environment.getExternalStorageDirectory().toString() + "/UN_CODING";
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/UN_CODING");
                dir.mkdirs();
                File directory = new File(path);
                File[] files = directory.listFiles();
                if (files.length == 0) {
                    listAdapter.add("No Files Saved Till Now...");
                } else {
                    for (int i = 0; i < files.length; i++) {
                        listAdapter.add(files[i].getName());
                    }
                }
            } else if (type == 2) {
                getSupportActionBar().setTitle("SYMBOLS AND MORE");
                toolbari.setBackgroundColor(Color.parseColor("#88cc00"));
                data = readFile(R.raw.symbols, "##");
                for (int i = 0; i < data.length; i++) {
                    listAdapter.add(data[i]);
                }
            } else if (type == 3) {
                practoolbar.setVisibility(View.VISIBLE);
                getSupportActionBar().setTitle(null);
                toolbari.setBackgroundColor(Color.parseColor("#ffa280"));
                list.setDivider(new ColorDrawable(Color.parseColor("#0000ff")));
                list.setDividerHeight(2);
                data = readFile(R.raw.questions, "##");
                for (int i = 0; i < data.length; i++) {
                    listAdapter.add(data[i] + "\n");
                }
                qpt = (TextView) findViewById(R.id.qfp);
                Typeface face = Typeface.createFromAsset(getAssets(), "rewards.ttf");
                qpt.setTypeface(face);
            } else if (type == 4) {
                listAdapter = new ArrayAdapter<String>(this, R.layout.customlisthelp, List);
                getSupportActionBar().setTitle(" HELP ");
                toolbari.setBackgroundColor(Color.parseColor("#ff661a"));
                data=readFile(R.raw.helpinfo,"##");
                for (int i = 0; i < data.length; i++) {
                listAdapter.add(data[i]);
            }
            } else {
                fullAds=new FullAds();
                getSupportActionBar().setTitle(getType(type));
                if (type / 100 == 1)
                {
                    fullAds.startAdd(Extras.this);
                    file = fileid.getDfprt(0);
                }
                else if (type / 100 == 2)
                    file = fileid.getDfprt(1);
                else if (type / 100 == 3)
                {
                    fullAds.startAdd(Extras.this);
                    file = fileid.getDfprt(2);
                }
                else if (type / 100 == 4)
                {
                    fullAds.startAdd(Extras.this);
                    file = fileid.getDfprt(3);
                }
                else
                    file = fileid.getDfprt(4);
                data = readFile(file, "##");
                listAdapter.add(data[(type % 100) - 1]);
            }
            list.setAdapter(listAdapter);
            if (type == 1) {
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        String data = (String) parent.getItemAtPosition(position);
                        File sd = new File("/sdcard/UN_CODING/" + data);
                        Intent i2 = new Intent();
                        i2.setAction(android.content.Intent.ACTION_VIEW);
                        i2.setDataAndType(Uri.fromFile(sd), "text/plain");
                        startActivity(i2);
                    }
                });
            }
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
    public String[] readFile(int file,String rep)
    {
        is = getResources().openRawResource(file);
        bf = new BufferedReader(new InputStreamReader(is));

        try {
            while((line = bf.readLine()) != null) { // <--------- place readLine() inside loop
                entireFile += (line + "\n"); // <---------- add each line to entireFile
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return entireFile.split(rep);
    }
    public String getType(int checking)
    {
        String type;
        if(checking>500)
            type="TIP ";
        else if(checking>400)
            type="REFERENCE ";
        else if(checking>300)
            type="POINTS TO REMEMBER ";
        else if(checking>200)
            type="FACT ";
        else
            type="DEFINITION ";
        type+=(checking%100);
        return type;
    }
}
