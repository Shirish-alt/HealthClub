package com.example.healthclub_app.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

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
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GraphFragement extends Fragment {

 String  access_token,type,bmi,totalfatpercent,musclepercent;
 ArrayList<PieEntry> FatInfo,MusleInfo;
 ArrayList<BarEntry> BmiInfo;
 PieChart musle_piechart,fat_piechart;
 BarChart bmi_barChart;
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
        musle_piechart=(PieChart) view.findViewById(R.id.musle_piechart);
        fat_piechart=(PieChart) view.findViewById(R.id.fat_piechart);
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
        FatInfo =new ArrayList<PieEntry>();
        MusleInfo =new ArrayList<PieEntry>();




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
//                                BmiInfo.add(new PieEntry(Float.parseFloat(bmi),type));
//                                PieDataSet dataSet=new PieDataSet(BmiInfo,"BMI Pie Chart");
//                                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//                                dataSet.setValueTextColor(Color.BLACK);
//                                dataSet.setValueTextSize(15f);
//
//                                PieData pieData=new PieData(dataSet);
//                                bmi_pieChart.invalidate();
//                                bmi_pieChart.setData(pieData);
//
//                                bmi_pieChart.setCenterText("Weekly Report");
//                                bmi_pieChart.setCenterTextSize(17f);
//                                bmi_pieChart.setCenterTextColor(Color.BLACK);
//                                bmi_pieChart.animate();




//Musle Info
                                MusleInfo.add(new PieEntry(Float.parseFloat(musclepercent),type));
                                PieDataSet dataSet1=new PieDataSet(MusleInfo,"Musle Pie Chart");
                                dataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
                                dataSet1.setValueTextColor(Color.BLACK);
                                dataSet1.setValueTextSize(15f);

                                PieData pieData1=new PieData(dataSet1);
                                musle_piechart.invalidate();
                                musle_piechart.setData(pieData1);

                                musle_piechart.setCenterText("Weekly Report");
                                musle_piechart.setCenterTextSize(17f);
                                musle_piechart.setCenterTextColor(Color.BLACK);
                                musle_piechart.animate();

 //Fat Into

                                FatInfo.add(new PieEntry(Float.parseFloat(totalfatpercent),type));
                                PieDataSet dataSet2=new PieDataSet(FatInfo,"Fat Pie Chart");
                                dataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
                                dataSet2.setValueTextColor(Color.BLACK);
                                dataSet2.setValueTextSize(15f);

                                PieData pieData2=new PieData(dataSet2);
                                fat_piechart.invalidate();
                                fat_piechart.setData(pieData2);

                                fat_piechart.setCenterText("Weekly Report");
                                fat_piechart.setCenterTextSize(17f);
                                fat_piechart.setCenterTextColor(Color.BLACK);
                                fat_piechart.animate();
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