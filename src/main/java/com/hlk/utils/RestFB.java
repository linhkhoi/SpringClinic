/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author MSIGE66
 */
@Component
public class RestFB {
  
  public static String FACEBOOK_APP_ID = "274262237510505";
  public static String FACEBOOK_APP_SECRET = "cbbae8b52197c0f768fb0bb2ba9c0f24";
  public static String FACEBOOK_REDIRECT_URL = "https://localhost:8443/SpringClinic/login-facebook";
//  public static String FACEBOOK_REDIRECT_URL = "http://springmvc-env.eba-2mrwhpvg.us-east-2.elasticbeanstalk.com/login-facebook";
  public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
  public String getToken(final String code) throws ClientProtocolException, IOException {
    String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URL, code);
    String response = Request.Get(link).execute().returnContent().asString();
     ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(response).get("access_token");
    return node.textValue();
  }
  
  public com.restfb.types.User getUserInfo(final String accessToken) {
    FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
    return facebookClient.fetchObject("me", com.restfb.types.User.class);
  }
  public UserDetails buildUser(com.restfb.types.User user) {
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority("ROLE_PATIENT"));
    UserDetails userDetail = new User(user.getId(), "", enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, authorities);
    return userDetail;
  }
}