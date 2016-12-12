package com.example.supriya.virtualticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

import static java.lang.Math.abs;

public class transaction extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    HashMap<String, Long> placeToId;
    Spinner fromSpinner,toSpinner;
    Double pricePerDistance;
    String From;
    String To;
    Double Price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
//        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                 String s = fromSpinner.getSelectedItem().toString();
//                Log.d("STATE",s);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        toSpinner.setAdapter(placeAdapter);
//        Log.d("Transaction", fromSpinner.getSelectedItem().toString());
//        Log.d("Transaction", toSpinner.getSelectedItem().toString());


        myRef.child("Routes").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                placeToId = (HashMap<String, Long>) dataSnapshot.getValue();
                Log.i("DATA", "" + dataSnapshot.toString());
                Log.d("WHAAAAAAAAAAAT",placeToId.toString());
                final List<String> placeList = new ArrayList<String>();
                final List<Long> priceList = new ArrayList<Long>();

                for (String s : placeToId.keySet()) {
                    placeList.add(s);
                    priceList.add(placeToId.get(s));
                }

                Log.d("Transaction", placeList.toString());
                Log.d("Whatwhat", priceList.toString());
                fromSpinner = (Spinner) findViewById(R.id.from);
                toSpinner = (Spinner) findViewById(R.id.to);

                ArrayAdapter<String> placeAdapter = new ArrayAdapter<String>(transaction.this, android.R.layout.simple_spinner_item, placeList);
                placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fromSpinner.setAdapter(placeAdapter);
                toSpinner.setAdapter(placeAdapter);
//                String fromPlace = fromSpinner.getSelectedItem().toString();
//                String toPlace = toSpinner.getSelectedItem().toString();
//
//                double pricePerDistance = placeToId.get("Cost");
//                double from = placeToId.get(fromPlace);
//                double to = placeToId.get(toPlace);
//
//                double cost = pricePerDistance * (Math.abs(from - to));
//                TextView costTextView = (TextView) findViewById(R.id.cost_txt_view);
//                costTextView.setText("" + cost);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("WHAAAAAAAAAAAT","---------------------------");
            }
        });
//        From = fromSpinner.getSelectedItem().toString();
//        To = toSpinner.getSelectedItem().toString();
//        Log.d("Places", placeToId.values().toString());
        fromSpinner = (Spinner) findViewById(R.id.from);
        toSpinner = (Spinner) findViewById(R.id.to);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                From = parent.getItemAtPosition(position).toString();
                toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        To = parent.getItemAtPosition(position).toString();
                        Log.d("TO", From + To);
                        System.out.println(placeToId.get(From).getClass());
                        //Log.d("Price1", placeToId.get(From).toString());

                        TextView costTxt = (TextView) findViewById(R.id.cost_txt_view);

                        double price = abs(placeToId.get(From) - placeToId.get(To));
                        //Log.d("Price2", price);
                        costTxt.setText("" + price);

//                        pricePerDistance = Double.valueOf(placeToId.get("Cost"));
//                        Log.d("Price", pricePerDistance.toString());
//                        Double from = Double.valueOf(placeToId.get(From));
//                        Double to = placeToId.get(To);
//
//                        Double cost = pricePerDistance * (Math.abs(from - to));
//                        TextView costTextView = (TextView) findViewById(R.id.cost_txt_view);
//                        costTextView.setText("" + cost);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void OnConfirm(View v){
        Intent I = new Intent(this,UserProfile.class );
        startActivity(I);
    }
    public void onLogggout(){
        finish();

    }
}


