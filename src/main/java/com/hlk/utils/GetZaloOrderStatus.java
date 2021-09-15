/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import vn.zalopay.crypto.HMACUtil;

/**
 *
 * @author MSIGE66
 */
public class GetZaloOrderStatus {
    private static final Map<String, String> config = new HashMap<String, String>(){{
        put("app_id", "2553");
        put("key1", "PcY4iZIKFCIdgZvA6ueMcMHHUbRLYjPL");
        put("key2", "kLtgPl8HHhfvMuDHPwKfgfsY4Ydm9eIz");
        put("endpoint", "https://sb-openapi.zalopay.vn/v2/query");
    }};
    
    
    public static int getStatus(String app_trans_id) throws URISyntaxException, UnsupportedEncodingException, IOException{
    String data = config.get("app_id") +"|"+ app_trans_id  +"|"+ config.get("key1"); // appid|app_trans_id|key1
      String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data);
    
    List<NameValuePair> params = new ArrayList<>();
      params.add(new BasicNameValuePair("app_id", config.get("app_id")));
      params.add(new BasicNameValuePair("app_trans_id", app_trans_id));
      params.add(new BasicNameValuePair("mac", mac));

      URIBuilder uri = new URIBuilder(config.get("endpoint"));
      uri.addParameters(params);

      CloseableHttpClient client = HttpClients.createDefault();
      HttpPost post = new HttpPost(uri.build());
      post.setEntity(new UrlEncodedFormEntity(params));

      CloseableHttpResponse res = client.execute(post);
      BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
      StringBuilder resultJsonStr = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
        resultJsonStr.append(line);
      }

      JSONObject result = new JSONObject(resultJsonStr.toString());
      
      return result.getInt("return_code");
      
    }
}

