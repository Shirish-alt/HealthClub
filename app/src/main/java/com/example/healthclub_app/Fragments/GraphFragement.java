package com.example.healthclub_app.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthclub_app.Activities.LoginScreen;
import com.example.healthclub_app.Adapters.TableAdapter;
import com.example.healthclub_app.Models.TableModel;
import com.example.healthclub_app.R;
import com.example.healthclub_app.Urls;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GraphFragement extends Fragment {

 String  access_token,type,bmi,totalfatpercent,musclepercent;
 ArrayList<BarEntry> FatInfo,MusleInfo;
 ArrayList<BarEntry> BmiInfo;
 ArrayList<String> bmi_lables;
 BarChart  bmi_barChart, musle_piechart,fat_piechart;

 RelativeLayout login,chart;
 TextView login_link;


    public GraphFragement(String token) {
        access_token=token;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_graph_fragement, container, false);

        bmi_barChart=(BarChart) view.findViewById(R.id.Bmi_piechart);
        musle_piechart=(BarChart) view.findViewById(R.id.musle_piechart);
        fat_piechart=(BarChart) view.findViewById(R.id.fat_piechart);
        login=view.findViewById(R.id.rel_login);
        login_link=view.findViewById(R.id.login_link);

        chart=view.findViewById(R.id.rel_chart);

        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginScreen.class));
            }
        });


        //ArrayList.....................................................
     BmiInfo =new ArrayList<BarEntry>();
        FatInfo =new ArrayList<BarEntry>();
        MusleInfo =new ArrayList<BarEntry>();

        bmi_lables=new ArrayList<>();




        if(access_token==null){
            Toast.makeText(getActivity(), "Plaese Login", Toast.LENGTH_SHORT).show();
            login.setVisibility(View.VISIBLE);
            chart.setVisibility(View.GONE);
        }else {
            Log.e("Access_Token:",""+access_token);
            login.setVisibility(View.GONE);
            chart.setVisibility(View.VISIBLE);
            GetTableData();
        }





        return view;
    }

    private void GetTableData() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, Urls.Progrsstable_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TableData:",response);

                        try {
                            JSONArray array=new JSONArray(response);
                            for(int i=0;i<array.length();i++){
                                JSONObject object=array.getJSONObject(i);

                                totalfatpercent=object.getString("totalfatpercent");
                                 bmi=object.getString("bmi");
                            musclepercent=object.getString("musclepercent");
                                 type=object.getString("type");





//Bmi Info...
                                BmiInfo.add(new BarEntry(i,Float.parseFloat(bmi)));
                                BarDataSet dataSet=new BarDataSet(BmiInfo,"BMI");
                                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                                dataSet.setValueTextColor(Color.BLACK);
                                dataSet.setValueTextSize(15f);

                                BarData barData=new BarData(dataSet);
                                bmi_barChart.setFitBars(true);
                                bmi_barChart.setData(barData);

                                bmi_barChart.getDescription().setText("Bar Report");
                                bmi_barChart.animateY(1000);

//Musle Info


                                MusleInfo.add(new BarEntry(i,Float.parseFloat(musclepercent)));
                                BarDataSet dataSet1=new BarDataSet(MusleInfo,"Muscle");
                                dataSet.setColors(Color.BLACK);
                                dataSet.setValueTextColor(Color.BLACK);
                                dataSet.setValueTextSize(15f);

                                BarData barData1=new BarData(dataSet1);
                                musle_piechart.setFitBars(true);
                                musle_piechart.setData(barData1);
                                musle_piechart.getDescription().setText("Bar Report");
                                musle_piechart.animateY(3000);


 //Fat Into

                                FatInfo.add(new BarEntry(i,Float.parseFloat(totalfatpercent)));
                                BarDataSet dataSet2=new BarDataSet(FatInfo,"Fat");
                                dataSet.setColors(Color.BLUE);
                                dataSet.setValueTextColor(Color.BLACK);
                                dataSet.setValueTextSize(15f);

                                BarData barData2=new BarData(dataSet2);
                                fat_piechart.setFitBars(true);
                                fat_piechart.setData(barData2);
                                fat_piechart.getDescription().setText("Bar Report");
                                fat_piechart.animateY(4000);




                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Err:"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + access_token);

                Log.e("Table-Auth:", String.valueOf(params));
                return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }

}