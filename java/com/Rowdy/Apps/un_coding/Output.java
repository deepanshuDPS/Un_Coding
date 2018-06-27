package com.Rowdy.Apps.un_coding;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;
import java.io.*;

public class Output extends AppCompatActivity {

    TextView output;
    String line,entireFile = "\n";
    InputStream is ;
    BufferedReader br;
    Outputs filebyob;
    Extra ex;
    Integer z,p,text,len;
    Toolbar toolbarc;
    Button next,prev,in,out;
    String lines[]=new String[50];
    ScrollView scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        try {
            scroll = (ScrollView) findViewById(R.id.vscrollview);
            ex = new Extra();
            next = (Button) findViewById(R.id.next);
            prev = (Button) findViewById(R.id.prev);
            in = (Button) findViewById(R.id.zin);
            out = (Button) findViewById(R.id.zout);
            output = (TextView) findViewById(R.id.output);
            p = getIntent().getIntExtra("fileo", 0);
            toolbarc = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbarc);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //scan output
            filebyob = new Outputs();
            is = getResources().openRawResource(filebyob.getOutput(p / 100, p % 100));
            br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((line = br.readLine()) != null) { // <--------- place readLine() inside loop
                    entireFile += (line + "  \n"); // <---------- add each line to entireFile
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int flag = entireFile.indexOf("#");
            if (flag > -1) {
                lines = entireFile.split("#");
                if (p / 10000 == 3 || p / 10000 == 4)
                    lines[lines.length - 1] += "\n\n....................END....................";
                text = 1;
                len = lines.length;
                settText(text);
            } else {
                output.setText(entireFile);
                next.setVisibility(View.GONE);
                prev.setVisibility(View.GONE);
            }
            getSupportActionBar().setTitle((ex.getTopic(p / 100)));
            setTextSize();
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
    void setTextSize()
    {
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)== Configuration.SCREENLAYOUT_SIZE_SMALL)
        {
            z=2;
            output.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.osmall));
        }
        else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)== Configuration.SCREENLAYOUT_SIZE_NORMAL)
        {
            z=3;
            output.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.onormal));
        }
        else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)== Configuration.SCREENLAYOUT_SIZE_LARGE)
        {
            z=4;
            output.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.olarge));
        }
        else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)== Configuration.SCREENLAYOUT_SIZE_XLARGE)
        {
            z=5;
            output.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.oxlarge));
        }
    }
    public void settText(int i) {
        String temp = "";
        for (int j = 0; j < i; j++)
        {
            temp += lines[j];
        }
        output.setText(temp+"\n\n\n\n\n\n");
        scroll.fullScroll(View.FOCUS_DOWN);
    }
    public void next(View v)
    {
        if(text<len)
            text++;
        settText(text);
    }
    public void previous(View v)
    {
        if(text>1)
            text--;
        settText(text);
    }
    public void zoomIn(View v)
    {
        z++;
        switch (z)
        {
            case 1:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxsmall));
                break;
            case 2:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.osmall));
                out.setBackgroundResource(R.drawable.zoomout);
                out.setClickable(true);
                break;
            case 3:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.onormal));
                break;
            case 4:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.olarge));
                break;
            case 5:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxlarge));
                break;
            case 6:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxxlarge));
                in.setBackgroundResource(R.drawable.trans);
                in.setClickable(false);
            case 7: z--;
                break;
        }
    }
    public void zoomOut(View v)
    {
        z--;
        switch (z)
        {
            case 0: z++;
                break;
            case 1:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxsmall));
                out.setBackgroundResource(R.drawable.trans);
                out.setClickable(false);
                break;
            case 2:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.osmall));
                break;
            case 3:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.onormal));
                break;
            case 4:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.olarge));
                in.setBackgroundResource(R.drawable.zoomin);
                in.setClickable(true);
                break;
            case 5:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxlarge));
                break;
            case 6:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxxlarge));
                break;
        }
    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("size",z);
        outState.putInt("text",text);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        z=savedInstanceState.getInt("size");
        text=savedInstanceState.getInt("text");
        getState(z);
        if(entireFile.indexOf("#")>-1)
            settText(text);
    }
    public void getState(int z)
    {
        switch (z)
        {
            case 1:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxsmall));
                out.setBackgroundResource(R.drawable.trans);
                out.setClickable(false);
                break;
            case 2:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.osmall));
                break;
            case 3:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.onormal));
                break;
            case 4:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.olarge));
                break;
            case 5:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxlarge));
                break;
            case 6:
                output.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.oxxlarge));
                in.setBackgroundResource(R.drawable.trans);
                in.setClickable(false);
        }
    }
}
