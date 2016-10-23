package com.example.raihanruhin.interviewquestionsforcsfresher;

import android.app.FragmentTransaction;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class Questions extends AppCompatActivity implements OnVersionNameSelectionChangeListener{
    private static final String TAG = Questions.class.getSimpleName();

    String str, arr[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questions);
        str = getIntent().getExtras().getString("string","defaultValue");
        //Toast.makeText(getApplicationContext(), str , Toast.LENGTH_SHORT).show();

        // Check whether the Activity is using the layout verison with the fragment_container
        // FrameLayout and if so we must add the first fragment

        if (findViewById(R.id.fragment_container) != null){

            // However if we are being restored from a previous state, then we don't
            // need to do anything and should return or we could end up with overlapping Fragments
            if (savedInstanceState != null){
                return;
            }

            // Create an Instance of Fragment
            VersionsFragment versionsFragment = new VersionsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", str);
            //versionsFragment.setArguments(getIntent().getExtras());
            versionsFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.fragment_container, versionsFragment).commit();
        }
    }

    @Override
    public void OnSelectionChanged(int versionNameIndex, String[] answer) {
        DescriptionFragment descriptionFragment = (DescriptionFragment) getFragmentManager()
                .findFragmentById(R.id.description_fragment);

        if (descriptionFragment != null){
            // If description is available, we are in two pane layout
            // so we call the method in DescriptionFragment to update its content
           // Bundle args = new Bundle();
           // args.putString("key", str);
           // args.putStringArray("answer", answer );
            //args.putInt(DescriptionFragment.KEY_POSITION,versionNameIndex);
            //descriptionFragment.setArguments(args);
            setArray(answer);
            //setStr(str);
            descriptionFragment.setDescription(versionNameIndex);
        } else {
            DescriptionFragment newDescriptionFragment = new DescriptionFragment();
            Bundle args = new Bundle();
            //args.putString("key", str);
            //args.putStringArray("answer", answer );
            setArray(answer);
            args.putInt(DescriptionFragment.KEY_POSITION,versionNameIndex);

            newDescriptionFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the backStack so the User can navigate back
            fragmentTransaction.replace(R.id.fragment_container,newDescriptionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    public String getStr(){
        return str;
    }

    public void setArray(String[] s)
    {
        arr = s;
        //Log.d(TAG, "setArray: " + arr[0]);
    }
    public String[] getArray(){
        return arr;
    }
}


