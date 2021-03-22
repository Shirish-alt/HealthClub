package com.vpace.healthyapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vpace.healthyapp.Adapters.ItemAdapter;
import com.vpace.healthyapp.Models.DietModel;
import com.vpace.healthyapp.R;
import com.vpace.healthyapp.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PreviousDiet extends Fragment {

 String access_token="",name,arrname=" ";
    WebView webView;
    String str;
    TextView water_intake_time,hungry_time,early_time,breack_time,mid_morning_time,lunch_time,evening_time,dinner_time;
    List<DietModel> dietModels,dietModels1,dietModels2,dietModels3,dietModels4,dietModels5,dietModels6,dietModels7;
    RecyclerView res_brkfast,res_mid_mrng,res_lunch,res_afternoon,res_evening,res_dinner,res_prev_ifyouarehugery,res_prev_earluymorning;

    public PreviousDiet(String token) {
        access_token=token;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_previous_diet, container, false);
        res_brkfast=view.findViewById(R.id.res_breakfast);
        res_mid_mrng=view.findViewById(R.id.res_mid_mrng);
        res_lunch=view.findViewById(R.id.res_lunch);
        res_afternoon=view.findViewById(R.id.res_afternoon);
        res_evening=view.findViewById(R.id.res_evening);
        res_dinner=view.findViewById(R.id.res_dinner);
        res_prev_ifyouarehugery=view.findViewById(R.id.res_prev_ifyouarehugery);
        res_prev_earluymorning=view.findViewById(R.id.res_prev_earlymorning);

        early_time=view.findViewById(R.id.early_time);
        breack_time=view.findViewById(R.id.break_time);
        mid_morning_time=view.findViewById(R.id.mid_time);
        lunch_time=view.findViewById(R.id.lunch_time);
        evening_time=view.findViewById(R.id.evening_time);
        dinner_time=view.findViewById(R.id.dinner_time);





        res_brkfast.setHasFixedSize(true);
        res_brkfast.setLayoutManager(new LinearLayoutManager(getActivity()));

        res_mid_mrng.setHasFixedSize(true);
        res_mid_mrng.setLayoutManager(new LinearLayoutManager(getActivity()));

        res_lunch.setHasFixedSize(true);
        res_lunch.setLayoutManager(new LinearLayoutManager(getActivity()));

        res_afternoon.setHasFixedSize(true);
        res_afternoon.setLayoutManager(new LinearLayoutManager(getActivity()));

        res_evening.setHasFixedSize(true);
        res_evening.setLayoutManager(new LinearLayoutManager(getActivity()));

        res_dinner.setHasFixedSize(true);
        res_dinner.setLayoutManager(new LinearLayoutManager(getActivity()));

        res_prev_ifyouarehugery.setHasFixedSize(true);
        res_prev_ifyouarehugery.setLayoutManager(new LinearLayoutManager(getActivity()));
        res_prev_earluymorning.setHasFixedSize(true);
        res_prev_earluymorning.setLayoutManager(new LinearLayoutManager(getActivity()));






//     Log.e("TokenDiet:",access_token);

        if(access_token!=null){
            LoadCurrentDiet(access_token);
        }else{
            Toast.makeText(getActivity(), "Please Login", Toast.LENGTH_LONG).show();

        }
        dietModels=new ArrayList<>();
        dietModels1=new ArrayList<>();
        dietModels2=new ArrayList<>();
        dietModels3=new ArrayList<>();
        dietModels4=new ArrayList<>();
        dietModels5=new ArrayList<>();
        dietModels6=new ArrayList<>();
        dietModels6=new ArrayList<>();




        return  view;
    }

    private void LoadCurrentDiet(String access_token) {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                Urls.Diet_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Prev",response);
                try {
                    JSONArray array=new JSONArray(response);


                    if(array.get(1).equals(null)){
                        Toast.makeText(getActivity(), "No Data", Toast.LENGTH_SHORT).show();
                        res_brkfast.setVisibility(View.GONE);
                        res_dinner.setVisibility(View.GONE);
                        res_evening.setVisibility(View.GONE);
                        res_afternoon.setVisibility(View.GONE);
                        res_lunch.setVisibility(View.GONE);
                        res_mid_mrng.setVisibility(View.GONE);
                        res_prev_earluymorning.setVisibility(View.GONE);
                        res_prev_ifyouarehugery.setVisibility(View.GONE);

                    }else{

                        for(int i=1;i<=1;i++){
                            JSONObject object= (JSONObject) array.get(1);



                            String id=object.getString("id");
                            Log.e("Id",id);
//JsonObject "diet"

                            JSONObject objectdiet=object.getJSONObject("diet");
                            Log.e("Previous Diet",""+objectdiet);
//                        JSONArray names=objectdiet.names();




                            String early=objectdiet.getString("Early_Morning_time");
                            String breaktime=objectdiet.getString("Breakfast_time");
                            String mid=objectdiet.getString("Mid_Morning_Snack_time");
                            String lunch=objectdiet.getString("lunch_time");
                            String evening=objectdiet.getString("Evening_Snack_time");
                            String dinner=objectdiet.getString("Dinner_time");



                            early_time.setText(early);
                            breack_time.setText(breaktime);
                            mid_morning_time.setText(mid);
                            lunch_time.setText(lunch);
                            evening_time.setText(evening);
                            dinner_time.setText(dinner);




                            if(objectdiet.get("").equals(null)){
                                res_prev_earluymorning.setVisibility(View.GONE);
                            }else {
                                JSONArray arrayEarly=objectdiet.getJSONArray("Early_Morning");
                                for(int e=0;e<arrayEarly.length();e++){
                                    String items=arrayEarly.getString(e);
                                    DietModel model=new DietModel("Early_Morning",items);
                                    dietModels6.add(model);

                                }
                            }



                            if(objectdiet.get("Breakfast").equals(null)){
                                res_brkfast.setVisibility(View.GONE);
                            }else {
                                JSONArray brkarr=objectdiet.getJSONArray("Breakfast");

                                for(int b=0;b<brkarr.length();b++){
                                    String items=brkarr.getString(b);

                                    DietModel model=new DietModel("Brackfast","- "+items);
                                    dietModels.add(model);
                                }
                            }


                            if(objectdiet.get("Mid_Morning_Snack").equals(null)){
                                res_mid_mrng.setVisibility(View.GONE);
                            }else {
                                JSONArray brkarr1=objectdiet.getJSONArray("Mid_Morning_Snack");
                                for(int b=0;b<brkarr1.length();b++){
                                    String items=brkarr1.getString(b);

                                    DietModel model1=new DietModel("Mid_Morning_Snack","- "+items);
                                    dietModels1.add(model1);
                                }
                            }




                            if(objectdiet.get("lunch").equals(null)){
                                res_lunch.setVisibility(View.GONE);
                            }else{
                                JSONArray brkarr2=objectdiet.getJSONArray("lunch");
                                for(int b=0;b<brkarr2.length();b++){
                                    String items=brkarr2.getString(b);

                                    DietModel model=new DietModel("lunch","- "+items);
                                    dietModels2.add(model);
                                }
                            }



                            if(objectdiet.get("Afetnoon_Snack").equals(null)){
                                res_afternoon.setVisibility(View.GONE);
                            }else {
                                JSONArray brkarr3=objectdiet.getJSONArray("Afetnoon_Snack");
                                for(int b=0;b<brkarr3.length();b++){
                                    String items=brkarr3.getString(b);

                                    DietModel model=new DietModel("Afetnoon_Snack","- "+items);
                                    dietModels3.add(model);
                                }

                            }

                            if(objectdiet.get("Evening_Snack").equals(null)){
                                res_evening.setVisibility(View.GONE);
                            }else{
                                JSONArray brkarr4=objectdiet.getJSONArray("Evening_Snack");
                                for(int b=0;b<brkarr4.length();b++){
                                    String items=brkarr4.getString(b);

                                    DietModel model=new DietModel("Evening_Snack","- "+items);
                                    dietModels4.add(model);
                                }
                            }


                         if(objectdiet.get("Dinner").equals(null)){
                             res_dinner.setVisibility(View.GONE);
                         }else{
                             JSONArray brkarr5=objectdiet.getJSONArray("Dinner");
                             for(int b=0;b<brkarr5.length();b++){
                                 String items=brkarr5.getString(b);

                                 DietModel model=new DietModel("Dinner","- "+items);
                                 dietModels5.add(model);
                             }

                         }

                         if(objectdiet.get("").equals(null)){
                             res_prev_ifyouarehugery.setVisibility(View.GONE);
                         }else {
                             JSONArray hungrykarr5=objectdiet.getJSONArray("Water_Intake");
                             for(int b=0;b<hungrykarr5.length();b++){
                                 String items=hungrykarr5.getString(b);

                                 DietModel model=new DietModel("Water_Intake","- "+items);
                                 dietModels7.add(model);
                             }

                         }





                        }
//                    ItemAdapter itemAdapter=new ItemAdapter(getActivity(),dietModels);
//                    recyclerView.setAdapter(itemAdapter);
                        ItemAdapter itemAdapter=new ItemAdapter(getActivity(),dietModels);
                        ItemAdapter itemAdapter1=new ItemAdapter(getActivity(),dietModels1);
                        ItemAdapter itemAdapter2=new ItemAdapter(getActivity(),dietModels2);
                        ItemAdapter itemAdapter3=new ItemAdapter(getActivity(),dietModels3);
                        ItemAdapter itemAdapter4=new ItemAdapter(getActivity(),dietModels4);
                        ItemAdapter itemAdapter5=new ItemAdapter(getActivity(),dietModels5);
                        ItemAdapter itemAdapter6=new ItemAdapter(getActivity(),dietModels6);
                        ItemAdapter itemAdapter7=new ItemAdapter(getActivity(),dietModels7);

                        res_brkfast.setAdapter(itemAdapter);
                        res_mid_mrng.setAdapter(itemAdapter1);
                        res_lunch.setAdapter(itemAdapter2);
                        res_afternoon.setAdapter(itemAdapter3);
                        res_evening.setAdapter(itemAdapter4);
                        res_prev_earluymorning.setAdapter(itemAdapter6);
                        res_prev_ifyouarehugery.setAdapter(itemAdapter7);
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
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("Authorization", "Bearer " + access_token);
                return  map;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }
}