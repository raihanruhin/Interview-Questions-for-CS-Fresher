package com.example.raihanruhin.interviewquestionsforcsfresher;

import java.util.ArrayList;

/**
 * Created by raihanruhin on 14-Jul-16.
 */
public class Data {
    public static ArrayList<Information> getData(){

        ArrayList<Information> data = new ArrayList<>();

        int[] images={
                R.drawable.c,
                R.drawable.cpp,
                R.drawable.cs,
                R.drawable.java,
                R.drawable.javascript,
                R.drawable.php,
                R.drawable.python,
                R.drawable.sql,
                R.drawable.algods,
                R.drawable.os,
                R.drawable.networking,
                R.drawable.misc
        };

        String[] Categories = {"C", "C++", "C#", "Java", "JavaScript", "PHP", "Python", "Database", "AlgoDS", "OS", "Networking", "Misc"};

        for(int i=0;i<images.length;i++)
        {
            Information current = new Information();
            current.imageId=images[i];
            current.title=Categories[i];
            data.add(current);
        }
        return data;
    }
}
