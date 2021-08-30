package com.example.admin_main;

import androidx.annotation.ColorLong;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.content.DialogInterface;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.slider.LabelFormatter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity {
    private int col=8;

    private int[] Data1={0,0,0};
    private int[] Data2={0,0,0};
    private int[] Data3={0,0,0};
    private int[] Data4={0,0,0,0,0};



    private BarChart bar;
    List<BarEntry>list;
    List<BarEntry> list2;
    List<BarEntry> list3;
    List<BarEntry> list4;
    List<BarEntry> list5;



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:9090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);





    MaterialButton button11 ,button22,button33;
    PieChart pieChart,pieChart1,pieChart2,pieChart3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);


        list=new ArrayList<>();
        list2=new ArrayList<>();
        list3=new ArrayList<>();
        list4=new ArrayList<>();
        list5=new ArrayList<>();

        //I added this if statement to keep the selected fragment when rotating the device



      button11 = findViewById(R.id.button1) ;
        button22 = findViewById(R.id.button2);
        button33 = findViewById(R.id.button3);


        button11.setChecked(true);


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                col=8;


                getData(1);

                  /*  addDataSetHours();
                    addDataNewP();
                    addDataNewChildren();
                    addDataTotal();
                    addBarChart();*/

            }

        });



        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* for(int i = 0 ; i<2;i++) {
                    Data1[i] = (35 + 40 * i);
                    Data2[i] = (50 + 40 * i);
                    Data3[i] = (3 + 40 * i);
                }*/
                 /*   for(int i=0; i<4;i++) {
                        list.add(new BarEntry(i, i+5));
                        list2.add(new BarEntry(i, i+6));
                        list3.add(new BarEntry(i, i+7));
                        list4.add(new BarEntry(i, i+8));
                        list5.add(new BarEntry(i, i+9));


                    }*/
                col=5;
                getData(2);


                  /*  addDataSetHours();
                    addDataNewP();
                    addDataNewChildren();
                    addDataTotal();
                    addBarChart();*/
                }


        });

        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     for(int i = 0 ; i<2;i++) {
                    Data1[i] = (35 + 10 * i);
                    Data2[i] = (50 + 10 * i);
                    Data3[i] = (3 + 10 * i);
                }*/
                /*   for(int i=0; i<12;i++) {
                        list.add(new BarEntry(i, i+1));
                        list2.add(new BarEntry(i, i+2));
                        list3.add(new BarEntry(i, i+3));
                        list4.add(new BarEntry(i, i+4));
                        list5.add(new BarEntry(i, i+5));


                    }*/
                col=13;
                getData(3);


                    addDataSetHours();
                    addDataNewP();
                    addDataNewChildren();
                    addDataTotal();
                    addBarChart();
                }

        });

        pieChart = findViewById(R.id.pieChart_view);
        pieChart.setRotationEnabled(false);
        pieChart.setUsePercentValues(false);
        pieChart.setHoleRadius(80f);
        pieChart.setTransparentCircleAlpha(180);

        pieChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        pieChart.setCenterTextSize(20);
        pieChart.getDescription().setEnabled(false);


        pieChart1 = findViewById(R.id.pieChart_view1);
        pieChart1.setRotationEnabled(false);
        pieChart1.setUsePercentValues(false);
        pieChart1.setHoleRadius(80f);
        pieChart1.setTransparentCircleAlpha(180);

        pieChart1.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        pieChart1.setCenterTextSize(20);
        pieChart1.getDescription().setEnabled(false);


        pieChart2 = findViewById(R.id.pieChart_view2);
        pieChart2.setRotationEnabled(false);
        pieChart2.setUsePercentValues(false);
        pieChart2.setHoleRadius(80f);
        pieChart2.setTransparentCircleAlpha(180);

        pieChart2.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        pieChart2.setCenterTextSize(20);
        pieChart2.getDescription().setEnabled(false);


        pieChart3 = findViewById(R.id.pieChart_view3);
        pieChart3.setRotationEnabled(false);
        pieChart3.setUsePercentValues(true);
        pieChart3.setHoleRadius(0);

        pieChart3.setTransparentCircleAlpha(0);
       // pieChart3.setCenterText("");
        pieChart3.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        pieChart3.setCenterTextSize(20);
        pieChart3.getDescription().setEnabled(false);


        pieChart3.setTouchEnabled(false);
        pieChart2.setTouchEnabled(false);
        pieChart1.setTouchEnabled(false);
        pieChart.setTouchEnabled(false);
       // bar.setTouchEnabled();



        addDataSetHours();
        addDataNewP();
        addDataNewChildren();
        addDataTotal();
        addBarChart();



        final FragmentManager fragmentManager = getSupportFragmentManager();

        // define your fragments here
        final Fragment fragment1 = new users();
        final Fragment fragment2 = new leaders();
        final Fragment fragment3 = new courses();
        final Fragment fragment4 = new home();
        final Fragment fragment5 = new more();

        bottomNavigation.setOnNavigationItemSelectedListener(
          new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        switch (item.getItemId()) {
                            case R.id.home_Id:
//
                                /*Fragment myFragment = (Fragment)getSupportFragmentManager().findFragmentByTag("MY_FRAGMENT");
                                if(myFragment != null&& myFragment.isVisible()) {
                                    fragmentManager.beginTransaction().remove(myFragment).commitAllowingStateLoss();
                                }*/
                                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment4, "MY_FRAGMENT")
                                        .commitAllowingStateLoss();

                                break;

                            case R.id.users_Id:
                                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment1, "MY_FRAGMENT")
                                        .commitAllowingStateLoss();
                                break;
                            case R.id.leaders_Id:
                                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment2, "MY_FRAGMENT")
                                        .commitAllowingStateLoss();


                                break;
                            case R.id.coures_Id:
                                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment3, "MY_FRAGMENT")
                                        .commitAllowingStateLoss();
                                break;
                            case R.id.more_Id:
                                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment5, "MY_FRAGMENT")
                                        .commitAllowingStateLoss();

                                break;
                      /*      case R.id.more_Id:
//                                fragmentManager.beginTransaction().replace(R.id.adminlayout, fragment5, "MY_FRAGMENT")
//                                        .commitAllowingStateLoss();
                                PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.more_Id));
                                MenuInflater inflater = popup.getMenuInflater();
                                inflater.inflate(R.menu.mymenu, popup.getMenu());
                                popup.show();

                                break;
*/
                        }


                        return true;
                    }
                });


    }
    private void addDataSetHours() {
        ArrayList<PieEntry> Monthly_data = new ArrayList<>();

        for(int i = 0; i < Data1.length; i++){
            Monthly_data.add(new PieEntry((int)Data1[i] , i));
        }

        pieChart.setCenterText(String.valueOf(Data1[2]));
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(Monthly_data, "Activities In Hour");
        pieDataSet.setSliceSpace(Data1.length);
        pieDataSet.setValueTextSize(12);
        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.blue1));
        colors.add(getResources().getColor(R.color.gray1));
        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        pieDataSet.setSliceSpace(0f);
        pieChart.setDrawSliceText(false);
       //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
       // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
        //pieChart.getLegend().setEnabled(false);
        pieChart.animateY(2250, Easing.EasingOption.EaseInCubic);
    }
    private void addDataNewP() {
        ArrayList<PieEntry> parents_data = new ArrayList<>();


        for(int i = 0; i < Data2.length; i++){
            parents_data.add(new PieEntry((int)Data2[i] , i));
        }

        pieChart1.setCenterText(String.valueOf(Data2[2]));
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(parents_data, " New Parents");
        pieDataSet.setSliceSpace(Data2.length);
        pieDataSet.setValueTextSize(12);
        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.yellow));
        colors.add(getResources().getColor(R.color.gray1));
        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        pieDataSet.setSliceSpace(0f);
        pieChart1.setDrawSliceText(false);
        //add legend to chart
        Legend legend = pieChart1.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart1.setData(pieData);
        pieChart1.invalidate();
        //pieChart.getLegend().setEnabled(false);
        pieChart1.animateY(2500, Easing.EasingOption.EaseInCubic);
    }
    private void addDataNewChildren() {
        ArrayList<PieEntry> children_data = new ArrayList<>();
        //  ArrayList<String> xEntrys = new ArrayList<>();
        for(int i = 0; i < Data3.length; i++){
            children_data.add(new PieEntry(Data3[i] , i));
        }

        pieChart2.setCenterText(String.valueOf(Data3[2]));
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(children_data, "New Children");
        pieDataSet.setSliceSpace(Data3.length);
        pieDataSet.setValueTextSize(12);
        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.blue2));
        colors.add(getResources().getColor(R.color.gray1));
        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        pieDataSet.setSliceSpace(0f);
        pieChart2.setDrawSliceText(false);
        //add legend to chart
        Legend legend = pieChart2.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart2.setData(pieData);
        pieChart2.invalidate();
        //pieChart.getLegend().setEnabled(false);
        pieChart2.animateY(2750, Easing.EasingOption.EaseInCubic);
    }

    private void addDataTotal() {
        ArrayList<PieEntry> total_data = new ArrayList<>();

        for(int i = 0; i < Data4.length; i++){
            total_data.add(new PieEntry(Data4[i] , i));
        }
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(total_data, "Total Per Category");
        pieDataSet.setSliceSpace(Data4.length);
        pieDataSet.setValueTextSize(12);
        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();



        colors.add(getResources().getColor(R.color.teal_200));
        colors.add(getResources().getColor(R.color.blue2));
        colors.add(getResources().getColor(R.color.yellow));
        colors.add(getResources().getColor(R.color.blue2));
        colors.add(getResources().getColor(R.color.gray1));
        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        pieDataSet.setSliceSpace(0f);
        pieDataSet.setDrawValues(true);

        pieDataSet.setValueTextSize(10);


        pieChart3.setUsePercentValues(false);

        //add legend to chart
        Legend legend = pieChart3.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart3.setData(pieData);
        pieChart3.invalidate();
        //pieChart.getLegend().setEnabled(false);


        pieChart3.animateY(3000, Easing.EasingOption.EaseInCubic);
    }




    private void addBarChart (){
        bar = (BarChart) findViewById(R.id.bar);





        //Add data for the first group
        for(int i=0; i<7;i++) {
            list.add(new BarEntry(i, i));

        }

        //Add data for the second group

        for(int i=0; i<7;i++) {
            list2.add(new BarEntry(i, i));

        }

        for(int i=0; i<7;i++) {
            list3.add(new BarEntry(i, i));

        }
        for(int i=0; i<7;i++) {
            list4.add(new BarEntry(i, i));

        }
        for(int i=0; i<7;i++) {
            list5.add(new BarEntry(i, i));

        }



        /*
        list2.add(new BarEntry(1,6));
        list2.add(new BarEntry(2,8));
        list2.add(new BarEntry(3,5));
        list2.add(new BarEntry(4,3));
        list2.add(new BarEntry(5,4));*/



        BarDataSet barDataSet=new BarDataSet(list,"art");
        barDataSet.setColor(getResources().getColor(R.color.gray1));        //Set the color for the first group of columns

        BarDataSet barDataSet2=new BarDataSet(list2,"draw");
        barDataSet2.setColor(getResources().getColor(R.color.blue2));      //Set the color for the second group of columns

        BarDataSet barDataSet3=new BarDataSet(list3,"math");
        barDataSet3.setColor(getResources().getColor(R.color.yellow));

        BarDataSet barDataSet4=new BarDataSet(list4,"sport");
        barDataSet4.setColor(getResources().getColor(R.color.blue2));

        BarDataSet barDataSet5=new BarDataSet(list5,"space");
        barDataSet5.setColor(getResources().getColor(R.color.teal_200));

        BarData barData=new BarData(barDataSet);      //Add the first group
        barData.addDataSet(barDataSet2);

        barData.addDataSet(barDataSet3);

        barData.addDataSet(barDataSet4);

        barData.addDataSet(barDataSet5); //Add the second group (multiple groups can also use the same method)

        bar.setData(barData);
        barDataSet.setHighlightEnabled(false);
        barDataSet.setDrawValues(false);
        barDataSet2.setHighlightEnabled(false);
        barDataSet2.setDrawValues(false);
        barDataSet3.setHighlightEnabled(false);
        barDataSet3.setDrawValues(false);
        barDataSet4.setHighlightEnabled(false);
        barDataSet4.setDrawValues(false);
        barDataSet5.setHighlightEnabled(false);
        barDataSet5.setDrawValues(false);
        barData.setBarWidth(0.08f);//The width of the column
        //Key! The three parameters respectively represent the interval between the starting point of the X-axis group and the interval between the columns in the group
        barData.groupBars(1f,0.35f,0.05f);

        bar.getXAxis().setCenterAxisLabels(true);      //Set the column (column group) to center and align the point on the X axis


        bar.getXAxis().setAxisMaximum(col);      //Maximum value of X axis
        bar.getXAxis().setAxisMinimum(1);      //Minimum value of X axis
        //Number of X-axis coordinates The second parameter is generally false true, which means that the number of labels is mandatory, which may cause problems such as incomplete display of X-axis coordinates.
        bar.getXAxis().setLabelCount(13,false);
        bar.getDescription().setEnabled(false);        //A string of English letters in the lower right corner is not displayed
        bar.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);      //The position of the X axis is set to down, the default is up
        bar.getAxisRight().setEnabled(false);          //The right Y axis is not displayed, the default is to display

        bar.setDrawBarShadow(false);

        //bar.setDrawValueAboveBar(false);

        bar.setPinchZoom(false);

       // bar.getLegend().setEnabled(false);
      //  bar..isEnabled = false;

        bar.setScaleMinima(2.2f, 1.0f);

        bar.setPinchZoom(false);
        bar.setDrawGridBackground(false);

        // empty labels so that the names are spread evenly
        String[] labels = {"", "Name1", "Name2", "Name3", "Name4", "Name5", ""};

        XAxis xAxis = bar.getXAxis();
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        xAxis.setAxisLineColor(Color.BLUE);
      //  xAxis.setAxisMinimum(1f);

     //   bar.getAxisRight().setEnabled(false);

      //  bar.setTouchEnabled(true);

       // bar.notifyDataSetChanged();
       // bar.invalidate();



        bar.animateY(3000);


    }



    private void getData(int period) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES);
        // create retrofit builder and pass our base url
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9090/")
                .addConverterFactory(GsonConverterFactory.create());
                retrofit.client(okHttpClient.build());
        RetrofitAPI retrofitAPI = retrofit.build().create(RetrofitAPI.class);


        Call<double[]> activity = retrofitAPI.meetingsChart(period);
        activity.enqueue(new Callback<double[]>() {
            @Override
            public void onResponse(Call<double[]> call, Response<double[]> response) {
                Data1[0] = (int) response.body()[1];
                Data1[1] = (int) (response.body()[0] - response.body()[1]);
                Data1[2] = (int) response.body()[1];
                TextView circle1=findViewById(R.id.textView6);
                circle1.setText(String.valueOf((int)response.body()[2])+"%");
                addDataSetHours();

            }

            @Override
            public void onFailure(Call<double[]> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });



        Call<double[]> parents = retrofitAPI.parentsChart(period);
        parents.enqueue(new Callback<double[]>() {
            @Override
            public void onResponse(Call<double[]> call, Response<double[]> response) {
                Data2[0] = (int) response.body()[1];
                Data2[1] = (int) (response.body()[0] - response.body()[1]);
                Data2[2] = (int) response.body()[1];
                TextView circle2=findViewById(R.id.textView3);
                circle2.setText(String.valueOf((int)response.body()[2])+"%");
                addDataNewP();
            }

            @Override
            public void onFailure(Call<double[]> call, Throwable t) {
                System.out.println(t.getMessage());
            }

        });

        Call<double[]> kids = retrofitAPI.kidsChart(period);
        kids.enqueue(new Callback<double[]>() {
            @Override
            public void onResponse(Call<double[]> call, Response<double[]> response) {
                Data3[0] = (int) response.body()[1];
                Data3[1] = (int) (response.body()[0] - response.body()[1]);
                Data3[2] = (int) response.body()[1];
                TextView circle3=findViewById(R.id.textView4);
                circle3.setText(String.valueOf((int)response.body()[2])+"%");
                addDataNewChildren();
            }

            @Override
            public void onFailure(Call<double[]> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        Call<HashMap<String, Integer>> cat = retrofitAPI.getkidsNumInCategoryPerPeriod(period);
        cat.enqueue(new Callback<HashMap<String, Integer>>() {
            @Override
            public void onResponse(Call<HashMap<String, Integer>> call, Response<HashMap<String, Integer>> response) {
                Data4[0]=(int)response.body().get("art");
                Data4[1]=(int)response.body().get("animal");
                Data4[2]=(int)response.body().get("math");
                Data4[3]=(int)response.body().get("draw");
                Data4[4]=(int)response.body().get("space");

                addDataTotal();
            }

            @Override
            public void onFailure(Call<HashMap<String, Integer>> call, Throwable t) {

            }
        });

        Call<HashMap<Locale.Category, HashMap<Integer, Integer>>> barC = retrofitAPI.barChartResults(period);
        barC.enqueue(new Callback<HashMap<Locale.Category, HashMap<Integer, Integer>>>() {
            @Override
            public void onResponse(Call<HashMap<Locale.Category, HashMap<Integer, Integer>>> call, Response<HashMap<Locale.Category, HashMap<Integer, Integer>>> response) {
            //    list.set(0,new BarEntry(response.body().entrySet().toArray()[0]));
               int j=0;
                for(HashMap<Integer, Integer> h: response.body().values()){
                    if(period==1){
                        for(int i=0;i<7;i++){
                            switch(j){
                                case 0:
                                    list.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 1:
                                    list2.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 2:
                                    list3.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 3:
                                    list4.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 4:
                                    list5.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                            }

                        }
                    }
                    if(period==2){
                        for(int i=0;i<4;i++){
                            switch(j){
                                case 0:
                                    list.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 1:
                                    list2.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 2:
                                    list3.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 3:
                                    list4.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 4:
                                    list5.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                            }

                        }
                    }
                    if(period==3){
                        for(int i=0;i<12;i++){
                            switch(j){
                                case 0:
                                    list.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 1:
                                    list2.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 2:
                                    list3.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 3:
                                    list4.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                                case 4:
                                    list5.set(i,new BarEntry(i,h.get(h.keySet().toArray()[i])));
                                    break;
                            }

                        }
                    }
                    j++;

                }
                addBarChart();
            }


            @Override
            public void onFailure(Call<HashMap<Locale.Category, HashMap<Integer, Integer>>> call, Throwable t) {

            }
        });
    }




}