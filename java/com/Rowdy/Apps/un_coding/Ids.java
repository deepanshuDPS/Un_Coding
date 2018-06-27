package com.Rowdy.Apps.un_coding;


public class Ids
{

    public int[] topics={R.id.ctop,R.id.cpptop,R.id.javatop,R.id.pythontop};
    public int[] boxs={R.id.c1,R.id.c2,R.id.c3,R.id.c4,R.id.c5,R.id.c6,R.id.c7,R.id.c8,R.id.c9,
                        R.id.cpp1,R.id.cpp2,R.id.cpp3,R.id.cpp4,R.id.cpp5,R.id.cpp6,R.id.cpp7,R.id.cpp8,R.id.cpp9,R.id.cpp10,
                        R.id.java1,R.id.java2,R.id.java3,R.id.java4,R.id.java5,R.id.java6,R.id.java7,R.id.java8,R.id.java9,R.id.java10,
                        R.id.python1,R.id.python2,R.id.python3,R.id.python4,R.id.python5,R.id.python6,R.id.python7,R.id.python8,R.id.python9,R.id.python10};
    public int[] dfptr={R.raw.def,R.raw.fact,R.raw.points,R.raw.refs,R.raw.tips};
    public int getBoxes(int i)
    {
        return boxs[i];
    }
    public int getTopics(int i)
    {
        return topics[i];
    }
    public int getDfprt(int i)
    {
        return dfptr[i];
    }
}
