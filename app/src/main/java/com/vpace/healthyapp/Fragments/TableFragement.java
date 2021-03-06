package com.vpace.healthyapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.vpace.healthyapp.Adapters.TableAdapter;
import com.vpace.healthyapp.Models.TableModel;
import com.vpace.healthyapp.R;
import com.vpace.healthyapp.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableFragement extends Fragment {

RecyclerView res_table;
TableAdapter tableAdapter;
List<TableModel> tableModelList;

String access_token;
    public TableFragement(String token) {
        access_token=token;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_table_fragement, container, false);

        res_table=view.findViewById(R.id.res_table);
        res_table.setHasFixedSize(true);
        res_table.setLayoutManager(new LinearLayoutManager(getActivity()));

      //ArrayList...

      tableModelList=new ArrayList<>();

        if(access_token==null){
            Toast.makeText(getActivity(), "Plaese Login", Toast.LENGTH_SHORT).show();
        }else {
            Log.e("Access_Token:",""+access_token);
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
                                String weight=object.getString("weight");
                                String vfat=object.getString("vfat");
                                String tsf=object.getString("tsf");
                                String totalfatpercent=object.getString("totalfatpercent");
                                String bmi=object.getString("bmi");
                                String bmr=object.getString("bmr");
                                String musclepercent=object.getString("musclepercent");
                                String type=object.getString("type");

                                TableModel tableModel=new TableModel("","",weight,vfat,tsf,totalfatpercent,bmi,bmr,musclepercent,type,"");
                                tableModelList.add(tableModel);

                            }
                            tableAdapter=new TableAdapter(getActivity(),tableModelList);
                            res_table.setAdapter(tableAdapter);

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