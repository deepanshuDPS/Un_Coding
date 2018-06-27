package com.Rowdy.Apps.un_coding;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.*;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class Home_Page extends AppCompatActivity
{
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    NavigationView nv;
    ArrayList<Button> b=new ArrayList<Button>();
    ArrayList<TextView> t=new ArrayList<TextView>();
    Toolbar toolbar;
    ImageView logo;
    TextView text;
    RelativeLayout headerback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        try {
            setTimes();
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            dl = (DrawerLayout) findViewById(R.id.dl);
            abdt = new ActionBarDrawerToggle(Home_Page.this, dl, R.string.app_name, R.string.app_name);
            dl.setDrawerListener(abdt);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            nv = (NavigationView) findViewById(R.id.nv);
            View header = nv.getHeaderView(0);
            logo = (ImageView) header.findViewById(R.id.plogo);
            text = (TextView) header.findViewById(R.id.ptext);
            headerback = (RelativeLayout) header.findViewById(R.id.headid);
            logo.setBackgroundResource(R.drawable.trans);
            headerback.setBackgroundResource(R.drawable.headback);
            text.setText(null);
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

                        case R.id.dfprts:
                            startActivity(new Intent(Home_Page.this, DFPRT.class));
                            return true;
                        case R.id.PracQues:
                            startActivity(new Intent(Home_Page.this, Extras.class).putExtra("openExtra", 3));
                            return true;
                        case R.id.symbols:
                            startActivity(new Intent(Home_Page.this, Extras.class).putExtra("openExtra", 2));
                            return true;
                        case R.id.intQues:
                            startActivity(new Intent(Home_Page.this, Extras.class).putExtra("openExtra", 0));
                            return true;
                        case R.id.saved:
                            startActivity(new Intent(Home_Page.this, Extras.class).putExtra("openExtra", 1));
                            return true;
                        case R.id.help:
                            startActivity(new Intent(Home_Page.this, Extras.class).putExtra("openExtra", 4));
                            return true;
                        case R.id.rateus:
                            goToPlayStore();
                            return true;
                        default:
                            return true;
                    }
                }
            });
            getSupportActionBar().setTitle("UN_CODING");
            Ids id = new Ids();
            Typeface face = Typeface.createFromAsset(getAssets(), "pm.ttf");
            for (int i = 0; i < 39; i++) {
                b.add((Button) findViewById(id.getBoxes(i)));
                b.get(i).setTypeface(face);
            }
            for (int i = 0; i < 4; i++) {
                t.add((TextView) findViewById(id.getTopics(i)));
                t.get(i).setTypeface(face);
            }
            if(getTimes()>2 && getLater() && getTimes()%3==0)
                alertForRate();
        }
        catch (Exception e){}
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (abdt.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void alertForRate()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Home_Page.this);
        builder.setTitle("RATE THIS APP");
        builder.setMessage("Hi, take a minute to rate this app and help support to improve more features");
        builder.setCancelable(true);

        builder.setPositiveButton("RATE NOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToPlayStore();
                SharedPreferences.Editor editor = getSharedPreferences("rewards", MODE_PRIVATE).edit();
                editor.putBoolean("later", false);
                editor.commit();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO, THANKS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = getSharedPreferences("rewards", MODE_PRIVATE).edit();
                editor.putBoolean("later", false);
                editor.commit();
                dialog.cancel();
            }
        });

        builder.setNeutralButton("REMIND ME LATER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog diag = builder.create();
        diag.show();
    }
    public void goToPlayStore()
    {
        //path to playstore
        Uri uri = Uri.parse("market://details?id=com.Rowdy.Apps.un_coding");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=com.Rowdy.Apps.un_coding")));
        }
    }
    public boolean getLater()
    {
        SharedPreferences sharedPrefs = getSharedPreferences("rewards", MODE_PRIVATE);
        return sharedPrefs.getBoolean("later", true);
    }
    public int getTimes()
    {
        SharedPreferences sharedPrefs = getSharedPreferences("rewards", MODE_PRIVATE);
        return sharedPrefs.getInt("nooftimes", 0);
        //    returning the saved data of no of rewards
    }
    public void setTimes()
    {
        SharedPreferences.Editor editor = getSharedPreferences("rewards", MODE_PRIVATE).edit();
        int a=getTimes()+1;
        editor.putInt("nooftimes",a);
        editor.commit();
    }
    public void cOperators(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",101));
    }
    public void cIfelseswitch(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",102));
    }
    public void cLoops(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",103));
    }
    public void cFunctions(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",104));
    }
    public void cArrayStr(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",105));
    }
    public void cPointers(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",106));
    }
    public void cStruct(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",107));
    }
    public void cRecursion(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",108));
    }
    public void cFilehand(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",109));
    }
    public void cppOperators(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",201));
    }
    public void cppIfelseswitch(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",202));
    }
    public void cppLoops(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",203));
    }
    public void cppFunctions(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",204));
    }
    public void cppArrayStr(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",205));
    }
    public void cppPointers(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",206));
    }
    public void cppStruct(View v)
    {
       startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",207));
    }
    public void cppOops(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",208));
    }
    public void cppRecursion(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",209));
    }
    public void cppFilehand(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",210));
    }
    public void javaOperators(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",301));
    }
    public void javaIfelseswitch(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",302));
    }
    public void javaLoops(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",303));
    }
    public void javaMethods(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",304));
    }
    public void javaArrayStr(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",305));
    }
    public void javaOops(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",306));
    }
    public void javaRecursion(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",307));
    }
    public void javaExcepHand(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",308));
    }
    public void javaMultiThread(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",309));
    }
    public void javaFilehand(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",310));
    }

    public void pythonOperators(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",401));
    }
    public void pythonIfelse(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",402));
    }
    public void pythonLoops(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",403));
    }
    public void pythonFunctions(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",404));
    }
    public void pythonListString(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",405));
    }
    public void pythonTupDiction(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",406));
    }
    public void pythonOops(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",407));
    }
    public void pythonRecursion(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",408));
    }
    public void pythonExcepHand(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",409));
    }
    public void pythonFileHand(View v)
    {
        startActivity(new Intent(Home_Page.this,ListofProg.class).putExtra("topics",410));
    }
}
