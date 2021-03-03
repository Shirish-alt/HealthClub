package com.example.healthclub_app.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthclub_app.Adapters.ItemAdapter;
import com.example.healthclub_app.Models.DietModel;
import com.example.healthclub_app.R;
import com.example.healthclub_app.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CurrentDiet extends Fragment {

    String access_token="",name;
    WebView webView;
    String str;
    List<DietModel> dietModels,dietModels1,dietModels2,dietModels3,dietModels4,dietModels5,dietModels6,dietModels7;
    RecyclerView res_brkfast,res_mid_mrng,res_lunch,res_afternoon,res_evening,res_dinner,res_waterintake,res_earlymorning;
    public CurrentDiet(String token) {
        access_token=token;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_current_diet, container, false);
        res_brkfast=view.findViewById(R.id.res_breakfast);
        res_mid_mrng=view.findViewById(R.id.res_mid_mrng);
        res_lunch=view.findViewById(R.id.res_lunch);
        res_afternoon=view.findViewById(R.id.res_afternoon);
        res_evening=view.findViewById(R.id.res_evening);
        res_dinner=view.findViewById(R.id.res_dinner);
        res_waterintake=view.findViewById(R.id.res_waterintake);
        res_earlymorning=view.findViewById(R.id.res_earlymorning);

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

        res_waterintake.setHasFixedSize(true);
        res_waterintake.setLayoutManager(new LinearLayoutManager(getActivity()));
        res_earlymorning.setHasFixedSize(true);
        res_earlymorning.setLayoutManager(new LinearLayoutManager(getActivity()));









//     Log.e("TokenDiet:",access_token);

        if(access_token!=null){
            Log.e("Token",access_token);
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
        dietModels7=new ArrayList<>();





        return  view;
    }

    private void LoadCurrentDiet(String access_token) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                Urls.Diet_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Current Diet",response);
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<=0;i++){
                        JSONObject object= (JSONObject) array.get(i);
                        String id=object.getString("id");
//JsonObject "diet"

                        JSONObject objectdiet=object.getJSONObject("diet");




                        if(objectdiet.get("Early_Morning").equals(null)){
                            res_earlymorning.setVisibility(View.GONE);
                        }else {
                            JSONArray brkarr=objectdiet.getJSONArray("Early_Morning");
                            if(brkarr != null) {
                                for (int e = 0; e < brkarr.length(); e++) {
                                    String items = brkarr.getString(e);

                                    DietModel model = new DietModel("Early_Morning", "- " + items);
                                    dietModels7.add(model);
                                }
                            }
                        }



                        if(objectdiet.get("Breakfast").equals(null)){
                            res_brkfast.setVisibility(View.GONE);
                        }else{
                            JSONArray brkarr0=objectdiet.getJSONArray("Breakfast");
                            if(brkarr0 != null) {
                                for (int b = 0; b < brkarr0.length(); b++) {
                                    String items = brkarr0.getString(b);

                                    DietModel model = new DietModel("Breakfast", "- " + items);
                                    dietModels.add(model);
                                }
                            }
                        }

                        if(objectdiet.get("Mid_Morning_Snack").equals(null)){
                            res_mid_mrng.setVisibility(View.GONE);
                        }else {
                            JSONArray brkarr1=objectdiet.getJSONArray("Mid_Morning_Snack");
                            if(brkarr1 != null) {
                                for (int b = 0; b < brkarr1.length(); b++) {
                                    String items = brkarr1.getString(b);

                                    DietModel model1 = new DietModel("Mid_Morning_Snack", "- " + items);
                                    dietModels1.add(model1);
                                }
                            }
                        }


                        if(objectdiet.get("lunch").equals(null)){
                            res_lunch.setVisibility(View.GONE);
                        }else {
                            JSONArray brkarr2=objectdiet.getJSONArray("lunch");
                            if(brkarr2 != null) {
                                for (int b = 0; b < brkarr2.length(); b++) {
                                    String items = brkarr2.getString(b);

                                    DietModel model = new DietModel("lunch", "- " + items);
                                    dietModels2.add(model);
                                }
                            }
                        }


                        if(objectdiet.get("Afetnoon_Snack").equals(null)){
                         res_afternoon.setVisibility(View.GONE);
                        }

                       else {
                            JSONArray brkarr3=objectdiet.getJSONArray("Afetnoon_Snack");
                            if(brkarr3 != null) {
                                for (int b = 0; b < brkarr3.length(); b++) {
                                    String items = brkarr3.getString(b);

                                    DietModel model = new DietModel("Afetnoon_Snack", "- " + items);
                                    dietModels3.add(model);
                                }
                            }
                        }



                      if(objectdiet.get("Evening_Snack").equals(null)){
                          res_evening.setVisibility(View.GONE);

                      }else{
                          JSONArray brkarr4=objectdiet.getJSONArray("Evening_Snack");
                          if(brkarr4 != null) {
                              for (int b = 0; b < brkarr4.length(); b++) {
                                  String items = brkarr4.getString(b);

                                  DietModel model = new DietModel("Evening_Snack", "- " + items);
                                  dietModels4.add(model);
                              }
                          }
                      }

                      if(objectdiet.get("Dinner").equals(null)){
                          res_dinner.setVisibility(View.GONE);
                      }

                       else {
                          JSONArray brkarr5=objectdiet.getJSONArray("Dinner");
                          if(brkarr5 != null) {
                              for (int b = 0; b < brkarr5.length(); b++) {
                                  String items = brkarr5.getString(b);

                                  DietModel model = new DietModel("Dinner", "- " + items);
                                  dietModels5.add(model);
                              }
                          }
                      }





                        if(objectdiet.get("Water_Intake").equals(null)){
                            Toast.makeText(getActivity(), "No DAta", Toast.LENGTH_SHORT).show();
                            res_waterintake.setVisibility(View.GONE);
                        }else{
                            JSONArray waterArr=objectdiet.getJSONArray("Water_Intake");
                            for(int w=0;w<waterArr.length();w++){
                                String items=waterArr.getString(w);
                                DietModel model=new DietModel("Water_Intake",items);
                                dietModels6.add(model);
                            }
                        }





                    }
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
                    res_dinner.setAdapter(itemAdapter5);
                    res_waterintake.setAdapter(itemAdapter6);
                    res_earlymorning.setAdapter(itemAdapter7);

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
                Log.e("DietAuth", String.valueOf(map));

                return  map;
            }
        };
        RequestQueue queue=Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }


//    private void initRecyclerView() {
//        ItemAdapter itemAdapter=new ItemAdapter(getActivity(),dietModels);
//       // recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(itemAdapter);
//
//    }
//    private List<DietModel> initData() {
//
//        dietModels=new ArrayList<>();
//        dietModels.add(new DietModel("BreakFast","Low Fat Panner-100gm","Oatmeal","1 ounce (28 grams) of dried chia seeds"));
//        dietModels.add(new DietModel("Mid_Morning_Snack","Apple slices with peanut butter","Eggwhites - 8","Low Fat Panner-200gm"));
//        dietModels.add(new DietModel("Lunch","Low Fat Panner-150gm","Low Fat Panner-150gm","Low Fat Panner-200gm"));
//        dietModels.add(new DietModel("Afertnoon_Snack","Low Fat Panner-150gm","Low Fat Panner-150gm","Low Fat Panner-200gm"));
//        dietModels.add(new DietModel("Evening_Snack","Low Fat Panner-150gm","Low Fat Panner-150gm","Low Fat Panner-200gm"));
//        dietModels.add(new DietModel("Dinner","Sprout 100gm","Low Fat Panner-150gm","Low Fat Panner-200gm"));
//
//        return  dietModels;
//
//    }


}