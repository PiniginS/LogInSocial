package com.example.kit.logininsoical;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VKLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VKSdk.login(this);
    }

    @Override//для вк
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(final VKAccessToken res) {
                VKParameters params = new VKParameters();
                params.put(VKApiConst.FIELDS, "photo_max_orig");

                VKRequest request = new VKRequest("users.get",params);
                request.executeWithListener(new VKRequest.VKRequestListener() {

                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        JSONObject resp = response.json;
                        try {
                            Intent intent = new Intent(VKLoginActivity.this, MainActivity.class);
                            String id =((JSONObject)((JSONArray)resp.get("response")).get(0)).get("id").toString();
                            String first =((JSONObject)((JSONArray)resp.get("response")).get(0)).get("first_name").toString();
                            String last =((JSONObject)((JSONArray)resp.get("response")).get(0)).get("last_name").toString();
                            String photo =((JSONObject)((JSONArray)resp.get("response")).get(0)).get("photo_max_orig").toString();
                            intent.putExtra("user_id",id);
                            intent.putExtra("first_name",first);
                            intent.putExtra("last_name",last);
                            intent.putExtra("photo",photo);
                            intent.putExtra("from","Vkontakte");

                            startActivity(intent);
                            //Toast.makeText(getApplicationContext(), first+" "+last+" ваш id"+id, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VKError error) {
                        super.onError(error);
                    }
                });
            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(VKLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
