package com.Rowdy.Apps.un_coding;


public class Extra
{
    public String getTopic(int i)
    {
        if(i==101 || i==201 || i==301 || i==401)
            return "OPERATORS";
        else if(i==102 || i==202 || i==302)
            return "IF-ELSE\nAND SWITCH";
        else if(i==103|| i==203 || i==303 || i==403)
            return "LOOPS";
        else if(i==104|| i==204 || i==404)
            return "FUNCTIONS";
        else if(i==105|| i==205 || i==305)
            return "ARRAYS\nAND STRINGS";
        else if(i==106|| i==206)
            return "POINTERS";
        else if(i==107|| i==207)
            return "STRUCTURES";
        else if(i==109|| i==210 || i==310 || i==410)
            return "FILE\nHANDLING";
        else if(i==108|| i==209 || i==307 ||i==408)
            return "RECURSION";
        else if(i==208 || i==306 || i==407)
            return "OOPS\nCONCEPTS";
        else if(i==304)
            return "METHODS";
        else if(i==308 || i==409)
            return "EXCEPTION\nHANDLING";
        else if(i==309)
            return "MULTITHREADING";
        else if(i==402)
            return "IF-ELSE";
        else if(i==405)
            return "LISTS\nAND STRINGS";
        else if(i==406)
            return "TUPLES AND\nDICTIONARIES";
        else
            return "";
    }
    public int getNum(int lg)
    {
        if(lg==107 ||  lg==108 || lg==207 || lg==209 || lg==307 || lg==308||lg==309 || lg==408 || lg==404)
            return 3;
        else if(lg==104 || lg==106 || lg==201|| lg==204|| lg==206 || lg==301 || lg==304 || lg==401)
            return 4;
        else if(lg==101 || lg==105|| lg==109|| lg==205  || lg==305 || lg==402 || lg==407)
            return 5;
        else if(lg==102 || lg==202 || lg==302 || lg==306)
            return 7;
        else if(lg==103 || lg==303)
            return 8;
        else if(lg==210 ||lg==208 || lg==405|| lg==406|| lg==410)
            return 6;
        else if(lg==203)
            return 9;
        else if(lg==310|| lg==409)
            return 2;
        else if(lg==403)
            return 10;
        else
            return 1;
    }
    public int getLogo(int i)
    {
        switch (i)
        {
            case 1: return R.drawable.c;
            case 2: return R.drawable.cpp;
            case 3: return R.drawable.java;
            case 4: return R.drawable.python;
            default: return 0;
        }
    }
    public String getLogoText(int i)
    {
        switch (i)
        {
            case 1: return "C Codes";
            case 2: return "C++\nCodes";
            case 3: return "Java\nCodes";
            case 4: return "Python\nCodes";
            default: return "";
        }
    }
}
