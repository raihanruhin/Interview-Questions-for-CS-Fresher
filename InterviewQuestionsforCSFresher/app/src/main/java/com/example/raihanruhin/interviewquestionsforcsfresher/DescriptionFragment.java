package com.example.raihanruhin.interviewquestionsforcsfresher;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;

/**
 * Created by raihanruhin on 15-Jul-16.
 */
public class DescriptionFragment extends Fragment {
    private static final String TAG = DescriptionFragment.class.getSimpleName();
    final static String KEY_POSITION = "position";
    int mCurrentPosition = -1;

    String[] mVersionDescriptions;
    TextView mVersionDescriptionTextView;

    public DescriptionFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //if (savedInstanceState != null) {
            // Restore last state for checked position.
            //mCurrentPosition = savedInstanceState.getInt(KEY_POSITION, 1);
            //Log.d(TAG, "Show last click position: "+ KEY_POSITION);
        //
        //setRetainInstance(true);
        /*Bundle bundle = this.getArguments();
        if(bundle!=null)
        table = bundle.getString("key");
        answer = bundle.getStringArray("answer");

        if (table.equals("C++"))
            table = "Cpp";
        if (table.equals("C#"))
            table = "Cs";*/

        //mVersionDescriptions = getResources().getStringArray(R.array.version_descriptions);
        /*mVersionDescriptions = answer;*/
        Questions q = (Questions) getActivity();
        mVersionDescriptions = q.getArray();
        //Log.d(TAG , "onActivityCreated: " + mVersionDescriptions.length);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // If the Activity is recreated, the savedInstanceStare Bundle isn't empty
        // we restore the previous version name selection set by the Bundle.
        // This is necessary when in two pane layout

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(KEY_POSITION);
            //Log.d(TAG, "Show last click position: "+ KEY_POSITION);
        }
        /*else
        {
            Bundle bundle = getArguments();
            mCurrentPosition = bundle.getInt(KEY_POSITION);
            //Log.d(TAG, "onCreateView: "+mCurrentPosition);
        }*/
//        mCurrentPosition = savedInstanceState.getInt(KEY_POSITION);
        //Log.d(TAG, "Show last click position: "+ mCurrentPosition);
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        //View view = inflater.inflate(R.layout.fragment_description, null, false);

        mVersionDescriptionTextView = (TextView) view.findViewById(R.id.version_description);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        // During the startup, we check if there are any arguments passed to the fragment.
        // onStart() is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method below
        // that sets the description text


        Bundle args = getArguments();

        if (args != null) {
            // Set description based on argument passed in

            setDescription(args.getInt(KEY_POSITION));

        } else if (mCurrentPosition != -1) {
            // Set description based on savedInstanceState defined during onCreateView()
            setDescription(mCurrentPosition);
        }
    }

    public void setDescription(int descriptionIndex) {
        Questions q = (Questions) getActivity();
        mVersionDescriptions = q.getArray();

        mVersionDescriptionTextView.setText(mVersionDescriptions[descriptionIndex]);
        mCurrentPosition = descriptionIndex;
        Log.d(TAG, "onSaveInstanceState: " + mCurrentPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current description selection in case we need to recreate the fragment
        outState.putInt(KEY_POSITION, mCurrentPosition);
        //Log.d(TAG, "onSaveInstanceState: " + mCurrentPosition);
    }
}

