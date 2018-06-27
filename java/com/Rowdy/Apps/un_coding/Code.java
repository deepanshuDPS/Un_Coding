package com.Rowdy.Apps.un_coding;

import android.app.Dialog;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.*;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.*;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;
import java.io.*;

public class Code extends AppCompatActivity
{
    TextView code,ptext;
    ImageView logo;
    String line,exten,entireFile = "\n";
    InputStream is ;
    BufferedReader br;
    Programs filebyob;
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    NavigationView nv;
    Extra ex;
    RelativeLayout back;
    Integer z,c,p;
    Toolbar toolbarc;
    View fl;
    Button in,out;
    FullAds fullAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        try {
            fullAds = new FullAds();
            in = (Button) findViewById(R.id.zin);
            out = (Button) findViewById(R.id.zout);
            ex = new Extra();
            back = (RelativeLayout) findViewById(R.id.code_back);
            code = (TextView) findViewById(R.id.code);
            p = getIntent().getIntExtra("file", 0);
            fl = (View) findViewById(R.id.fl);
            toolbarc = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbarc);
            dl = (DrawerLayout) findViewById(R.id.dl1);
            abdt = new ActionBarDrawerToggle(Code.this, dl, R.string.app_name, R.string.app_name);
            dl.setDrawerListener(abdt);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            nv = (NavigationView) findViewById(R.id.nv1);
            View header = nv.getHeaderView(0);
            logo = (ImageView) header.findViewById(R.id.plogo);
            ptext = (TextView) header.findViewById(R.id.ptext);
            //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
            nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                // This method will trigger on item Click of navigation menu
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {


                    //Checking if the item is in checked state or not, if not make it in checked state
                    if (menuItem.isChecked()) menuItem.setChecked(false);
                    else menuItem.setChecked(true);
                    //Closing drawer on item click
                    dl.closeDrawers();
                    //Check to see which item was being clicked and perform appropriate action
                    switch (menuItem.getItemId()) {
                        case R.id.output:
                            startActivity(new Intent(Code.this, Output.class).putExtra("fileo", p));
                            return true;
                        case R.id.back:
                            if (c == 0) {
                                back.setBackgroundColor(Color.parseColor("#202020"));
                                code.setBackgroundColor(Color.parseColor("#202020"));
                                code.setTextColor(Color.parseColor("#F5F5DC"));
                                fl.setBackgroundResource(R.drawable.shadowb);
                                c = 1;
                            } else {
                                back.setBackgroundColor(Color.WHITE);
                                code.setBackgroundColor(Color.WHITE);
                                code.setTextColor(Color.BLACK);
                                fl.setBackgroundResource(R.drawable.shadow);
                                c = 0;
                            }
                            return true;
                        case R.id.save:
                            askForSave();
                            return true;
                        default:
                            return true;
                    }
                }
            });

            //scan prog
            filebyob = new Programs();
            is = getResources().openRawResource(filebyob.getProgram(p / 100, p % 100));
            br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((line = br.readLine()) != null) { // <--------- place readLine() inside loop
                    entireFile += (line + "  \n"); // <---------- add each line to entireFile
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Typeface face = Typeface.createFromAsset(getAssets(), "code.ttf");
            code.setText(entireFile);
            code.setTypeface(face);
            getSupportActionBar().setTitle(ex.getTopic(p / 100));
            logo.setBackgroundResource(ex.getLogo(p / 10000));
            ptext.setText(ex.getLogoText(p / 10000));
            ptext.setTypeface(face);
            setTextSize();
        }
        catch (Exception e) {}
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        abdt.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        abdt.onConfigurationChanged(newConfig);
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (abdt.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void setTextSize()
    {
        c=0;
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)== Configuration.SCREENLAYOUT_SIZE_SMALL)
        {
            z=2;
            code.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.small));
        }
        else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)== Configuration.SCREENLAYOUT_SIZE_NORMAL)
        {
            z=3;
            code.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.normal));
        }
        else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)== Configuration.SCREENLAYOUT_SIZE_LARGE)
        {
            z=4;
            code.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large));
        }
        else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)== Configuration.SCREENLAYOUT_SIZE_XLARGE)
        {
            z=5;
            code.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.xlarge));
        }
    }
    public void zoomIn(View v)
    {
        z++;
        switch (z)
        {
            case 1:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xsmall));
                break;
            case 2:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.small));
                out.setBackgroundResource(R.drawable.zoomout);
                out.setClickable(true);
                break;
            case 3:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.normal));
                break;
            case 4:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.large));
                break;
            case 5:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xlarge));
                break;
            case 6:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xxlarge));
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
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xsmall));
                out.setBackgroundResource(R.drawable.trans);
                out.setClickable(false);
                break;
            case 2:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.small));
                break;
            case 3:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.normal));
                break;
            case 4:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.large));
                in.setBackgroundResource(R.drawable.zoomin);
                in.setClickable(true);
                break;
            case 5:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xlarge));
                break;
            case 6:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xxlarge));
                break;
        }
    }
    public void askForSave()
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Dialog dialog = new Dialog(Code.this);
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.save_file);
        dialog.setCancelable(false);
        final TextView title=(TextView) dialog.findViewById(R.id.titlesave);
        final EditText filename= (EditText) dialog.findViewById(R.id.filename);
        Button wex=(Button) dialog.findViewById(R.id.wex);
        Button wtex=(Button) dialog.findViewById(R.id.wtex);
        Typeface titlefont= Typeface.createFromAsset(getAssets(), "code.ttf");
        Typeface buttonfont= Typeface.createFromAsset(getAssets(), "rewards.ttf");
        title.setTypeface(titlefont);
        wex.setTypeface(buttonfont);
        wtex.setTypeface(buttonfont);
        wex.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
                String fn=filename.getText().toString().replaceAll("[^a-zA-Z0-9_]","");
                if(!fn.equals("")) {
                    switch (p / 10000)
                    {
                        case 1:
                            exten = ".c";
                            break;
                        case 2:
                            exten = ".cpp";
                            break;
                        case 3:
                            exten = ".java";
                            break;
                        case 4:
                            exten = ".py";
                            break;
                    }
                    saveFile(fn, exten);
                    Toast.makeText(Code.this,"File Saved",Toast.LENGTH_LONG).show();
                    fullAds.startAdd(Code.this);
                    dialog.cancel();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
                else
                {
                    Toast.makeText(Code.this,"Please Enter the Valid File Name to Save!",Toast.LENGTH_LONG).show();
                }
            }});

        wtex.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v)
            {
                String fn=filename.getText().toString().replaceAll("[^a-zA-Z0-9_]","");
                if(!fn.equals("")) {
                    saveFile(fn, ".txt");
                    Toast.makeText(Code.this,"File Saved",Toast.LENGTH_LONG).show();
                    fullAds.startAdd(Code.this);
                    dialog.cancel();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
                else
                {
                    Toast.makeText(Code.this,"Please Enter the Valid File Name to Save!",Toast.LENGTH_LONG).show();
                }
            }}
                );
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.cancel();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
                return true;
            }
        });
        dialog.show();
    }

    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("size",z);
        outState.putInt("color",c);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        z=savedInstanceState.getInt("size");
        c=savedInstanceState.getInt("color");
        getState(z,c);
    }
    public void getState(int z,int c)
    {
        switch (z)
        {
            case 1:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xsmall));
                out.setBackgroundResource(R.drawable.trans);
                out.setClickable(false);
                break;
            case 2:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.small));
                break;
            case 3:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.normal));
                break;
            case 4:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.large));
                break;
            case 5:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xlarge));
                break;
            case 6:
                code.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.xxlarge));
                in.setBackgroundResource(R.drawable.trans);
                in.setClickable(false);
        }
        if(c==0)
        {
            back.setBackgroundColor(Color.WHITE);
            code.setBackgroundColor(Color.WHITE);
            code.setTextColor(Color.BLACK);
            fl.setBackgroundResource(R.drawable.shadow);
        }
        else
        {
            back.setBackgroundColor(Color.parseColor("#202020"));
            code.setBackgroundColor(Color.parseColor("#202020"));
            code.setTextColor(Color.parseColor("#F5F5DC"));
            fl.setBackgroundResource(R.drawable.shadowb);
        }
        
    }
    public void saveFile(String filename,String ext)
    {

        File root = android.os.Environment.getExternalStorageDirectory();
        if(root==null)
        {
            root=android.os.Environment.getDataDirectory();
        }
        File dir = new File (root.getAbsolutePath() + "/UN_CODING");
        dir.mkdirs();
        filename=filename.replaceAll("\\s","");
        File file = new File(dir, filename+ext);
        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.print(entireFile);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File sd=new File("/sdcard/UN_CODING/"+filename+ext);
        Intent i2 = new Intent();
        i2.setAction(android.content.Intent.ACTION_VIEW);
        i2.setDataAndType(Uri.fromFile(sd), "text/plain");
        startActivity(i2);
    }
}
