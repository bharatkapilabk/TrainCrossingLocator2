package com.example.traincrossinglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class NewHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ListView listView;
    NavigationView navigation_view;
    TextView usr_email,usr_name;
    ImageView nav;
    FirebaseAuth mAuth;
String time;
    Calendar calendar;
    long diff;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    MyCountDownTimer myCountDownTimer;
    CustomAdapter customAdapter;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference1=firebaseDatabase.getReference("Data").child("Time");

    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Time = new ArrayList<String>();
//    private ArrayList<String> Amount = new ArrayList<String>();

    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        if(firebaseUser==null){
            sendToLogin();
        }
    }
    DrawerLayout drawer_layout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);

        mAuth=FirebaseAuth.getInstance();
       customAdapter=new CustomAdapter(NewHome.this,Name, Time);
        calendar=Calendar.getInstance();
        listView=findViewById(R.id.lv);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                time=dataSnapshot.getValue(String.class);



                Log.d("FireDebug","FireTime:"+time);


                try {
                    Date d1=simpleDateFormat.parse(time);
                    Log.d("FireDebug",""+d1.getTime());
                    Date d2=simpleDateFormat.parse(simpleDateFormat.format(calendar.getTime()));
                    Log.d("FireDebug",""+d2.getTime());
                    diff=d1.getTime()-d2.getTime();
                    Log.d("FireDebug",""+diff);
                    Toast.makeText(NewHome.this, ""+diff/1000, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                myCountDownTimer=new MyCountDownTimer(diff,1000);
                myCountDownTimer.start();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Context context=view.getContext();
                        Intent intent=new Intent(context,Map.class);
                        LatLng latlng1=new LatLng(31.342205,75.576007);
                        intent.putExtra("latlong",latlng1);
                        context.startActivity(intent);
                        break;
                    case 1:
                        Context context1=view.getContext();
                        Intent intent1=new Intent(context1,Map.class);
                        LatLng latlng2=new LatLng(31.3380797,75.5816489);
                        intent1.putExtra("latlong",latlng2);
                        context1.startActivity(intent1);
                        break;
                    case 2:
                        Context context2=view.getContext();
                        Intent intent2=new Intent(context2,Map.class);
                        LatLng latlng3=new LatLng(31.3393936,75.5797917);
                        intent2.putExtra("latlong",latlng3);
                        context2.startActivity(intent2);
                        break;
                    case 3:
                        Context context3=view.getContext();
                        Intent intent3=new Intent(context3,Map.class);
                        LatLng latlng4=new LatLng(31.3048962,75.6373343);
                        intent3.putExtra("latlong", latlng4);
                        context3.startActivity(intent3);
                        break;
                    case 4:
                        Context context4=view.getContext();
                        Intent intent4=new Intent(context4,Map.class);
                        LatLng latlng5=new LatLng(31.3179416,75.5988418);
                        intent4.putExtra("latlong", latlng5);
                        context4.startActivity(intent4);
                        break;
                }
            }
        });
        nav=findViewById(R.id.nav);
        navigation_view=findViewById(R.id.navigation_view);
        drawer_layout=findViewById(R.id.drawer_layout);
        calendar=Calendar.getInstance();
        Name.add("Sodal");
        Name.add("Hoshiarpur");
        Time.add("11");
        Time.add("11");

        Name.add("Tanda");
        Name.add("Dakoha");
        Time.add("11");
        Time.add("11");

        Name.add("BSF");
        Time.add("11");
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            View headerView = navigation_view.getHeaderView(0);
            usr_email = headerView.findViewById(R.id.usr_email);
            usr_email.setText(user.getEmail());
        }
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(navigation_view);
                drawer_layout.openDrawer(navigation_view);
                mDrawerToggle = new ActionBarDrawerToggle(NewHome.this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
                    @SuppressLint("NewApi")
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        super.onDrawerSlide(drawerView, slideOffset);

                        float min = 0.9f;
                        float max = 1.0f;
                        float scaleFactor = (max - ((max - min) * slideOffset));
                    }
                };
                drawer_layout.addDrawerListener(mDrawerToggle);
            }
        });

        navigation_view.setNavigationItemSelectedListener(this);


    }

    public class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
            simpleDateFormat1.setTimeZone(TimeZone.getTimeZone("UTC+5:30"));
            Date date = new Date(millisUntilFinished);

            Time.set(0,simpleDateFormat1.format(date));
            customAdapter.notifyDataSetChanged();
            Toast.makeText(NewHome.this, ""+simpleDateFormat1.format(date), Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onFinish() {
            Toast.makeText(NewHome.this, "Finished", Toast.LENGTH_SHORT).show();
        }}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.viewtrains){
            Intent intent=new Intent(NewHome.this,ViewTrains.class);
            startActivity(intent);
        }
        else if(id==R.id.viewcrossings){
            Toast.makeText(NewHome.this,"View Crossings", Toast.LENGTH_LONG).show();
        }
        else if(id==R.id.feedback){
            Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.surveymonkey.com/r/3DCJKN9"));
            startActivity(browserIntent);
        }
        else if(id==R.id.shareapp){
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        }
        else if(id==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            sendToLogin();
        }
        return true;
    }


    private void sendToLogin() {
        Intent loginIntent = new Intent(NewHome.this, LoginOrCreate.class);
        startActivity(loginIntent);
        finish();
    }
}
