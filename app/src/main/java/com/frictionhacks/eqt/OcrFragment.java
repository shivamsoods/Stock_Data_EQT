package com.frictionhacks.eqt;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class OcrFragment extends Fragment {

    private Button btnUpload, btnSelect;
    private ImageView ivOcr;
    private RequestQueue queue;
    private TextView tvOcr;
    private ProgressBar pbOcr;

    private String filePath,url_image,url_return;
    private ProgressDialog progressDialog;

    public OcrFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ocr, container, false);

         tvOcr=view.findViewById(R.id.tv_ocr);
        btnUpload = view.findViewById(R.id.btn_ocr_upload);
        btnSelect = view.findViewById(R.id.btn_ocr_select);
        pbOcr=view.findViewById(R.id.pb_ocr);
        queue = Volley.newRequestQueue(getContext());

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(Objects.requireNonNull(getActivity()))
                        .crop(1f,1f)       //Crop Square image(Optional)
                        .compress(1024)
                        .galleryOnly()
                        .start();
    btnSelect.setVisibility(View.GONE);
            }
        });

btnUpload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //uploadImage();
        uploadPreImage();
    }
});
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK ) {
            filePath = ImagePicker.Companion.getFilePath(data);
            Toast.makeText(getActivity(), "ff  "+ filePath, Toast.LENGTH_SHORT).show();

            Log.d(TAG, "Filepath "+filePath);


        } else if (resultCode == ImagePicker.RESULT_ERROR) {

        } else {
        }

    }


    private void uploadImage(){
        Map config = new HashMap();
        config.put("cloud_name", "frictionhacks");
        MediaManager.init(getActivity(), config);

        String requestId = MediaManager.get().upload(filePath)
                .unsigned("preset1")
                .option("resource_type", "image")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        // your code here
                    }
                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        // example code starts here
                        Double progress = (double) bytes/totalBytes;
                        // post progress to app UI (e.g. progress bar, notification)
                        // example code ends here
                    }
                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        Log.d(TAG, "onSuccess: "+resultData);
                    }
                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        // your code here
                    }
                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        // your code here
                    }})
                .option("notification_url", url_return)
                .dispatch();
    }
    private void uploadPreImage(){
      //  url_search=getString(R.string.url_base)+"budget?budget="+budget+"&ls="+tenure;
        url_image=getString(R.string.url_base)+"ocr?q=https://images.sampletemplates.com/wp-content/uploads/2015/04/stock-purchase-agreement-sample.jpg";
        Log.d(VolleyLog.TAG, "budget url "+url_image);
pbOcr.setVisibility(View.VISIBLE);
btnUpload.setVisibility(View.GONE);
        //https://f19773f6.ngrok.io/budget?budget=30000&ls=0

        final JsonArrayRequest req = new JsonArrayRequest(url_image,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(VolleyLog.TAG, "budget response "+response);

                        //jsonSearchParseSet(response);
                        try {
                            pbOcr.setVisibility(View.GONE);
                            tvOcr.setVisibility(View.VISIBLE);
                            tvOcr.setText("");


                            for(int i=0;i<response.length();i++){
                                tvOcr.append((response.getString(i)));

                            }
                            btnUpload.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(VolleyLog.TAG, "error " + error);



            }
        });

        queue.add(req);
    }


}
