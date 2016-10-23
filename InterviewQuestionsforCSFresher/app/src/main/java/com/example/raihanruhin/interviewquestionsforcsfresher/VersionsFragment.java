package com.example.raihanruhin.interviewquestionsforcsfresher;

import android.app.Activity;
import android.app.ListFragment;
import android.app.Notification;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by raihanruhin on 15-Jul-16.
 */




 public class VersionsFragment extends ListFragment {
    private static final String TAG = VersionsFragment.class.getSimpleName();
    String[] answer = new String[0];
    String table=new String();
    public VersionsFragment() {
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //String[] versionName = getResources().getStringArray(R.array.version_names);
        /*Bundle bundle = getArguments();
        if(bundle!=null)
        table = bundle.getString("key");*/

        Questions q = (Questions) getActivity();
        table = q.getStr();
        //Log.d(TAG, "onActivityCreated: " + table);

        if(table.equals("C++"))
            table="Cpp";
        else if(table.equals("C#"))
            table="Cs";
        else if(table.equals("Database"))
            table="db";
        else if(table.equals("AlgoDS"))
            table="ad";
        table = table.toLowerCase();
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<String, Integer, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            String[] versionName = new String[0];
            ArrayList<String> scripts = new ArrayList<String>();
            ArrayList<String> scripts2 = new ArrayList<String>();
            try {
                //Log.d(VersionsFragment.class.getSimpleName(),"http://192.168.1.105/android/phpcall.php?table="+table );
                URL url = new URL("http://nihonsys.com/ruhin/android/phpcall.php?table="+table);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                String strJson = "";
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    strJson += line;
                }
                //Log.d(VersionsFragment.class.getSimpleName(), "JSON string =" + strJson);
                JSONArray jArray = new JSONArray(strJson);
                //JSONObject jsonObj = new JSONObject(strJson);
                // Getting JSON Array node
                //JSONArray jArray = jsonObj.getJSONArray(strJson);

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject jObject = jArray.getJSONObject(i);

                    String name = jObject.getInt("id") + ". " + jObject.getString("question");
                    scripts.add(name);

                    String name2 = jObject.getString("answer");
                    scripts2.add(name2);
                }
                versionName = scripts.toArray(new String[scripts.size()]);
                answer = scripts2.toArray(new String[scripts.size()]);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return versionName;
        }

        protected void onPostExecute(String[] result){
            //for(int i=0;i<result.length;i++)
                //Log.d(VersionsFragment.class.getSimpleName(), result[i]);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, result);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        OnVersionNameSelectionChangeListener listener = (OnVersionNameSelectionChangeListener) getActivity();
        listener.OnSelectionChanged(position, answer);
    }
}
