package com.sprsec.service;

import com.sprsec.model.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;




@Service
public class Auth0Service {

    @Value("${AUTH0.DOMAIN}")
    private String DOMAIN;

    @Value("${AUTH0.CLIENT_ID}")
    private String CLIENT_ID;
    @Value("${AUTH0.CLIENT_SECRET}")
    private String CLIENT_SECRET;

    @Value("${AUTH0.AUTHORIZE_URL}")
    private String OAUTH_AUTHORIZE_URL;
    @Value("${AUTH0.TOKEN_URL}")
    private String OAUTH_TOKEN_URL;
    @Value("${AUTH0.USER_INFO_URL}")
    private String OAUTH_USER_INFO_URL;

    @Value("${AUTH0.REQUEST_AUTH_GRANT_TYPE}")
    private String GRANT_TYPE_AUTO_CODE;
    @Value("${AUTH0.REQUEST_AUTH_GRANT_TYPE_CLIENT_CREDENTIALS}")
    private String GRANT_TYPE_CLIENT_CREDENTIALS;
    @Value("${AUTH0.RESPONSE_JSON_ACCESS_TOKEN}")
    private String RESPONSE_JSON_ACCESS_TOKEN;

    @Value("${AUTH0.APP_LOGIN_REDIRECT_URL}")
    private String REDIRECT_URL;


    private JSONObject authorizationToken = null;
    private long authorizationTokenSetDate = 0l;
    private long authorizationTokenLifeTime = 86400000l; //24hours

    private String providerNotSocialAccount = "auth0";

    private String session_key_user_email = "auth0_user_email";
    private String session_key_user_fullname = "auth0_user_fullname";
    private String session_key_user_state = "auth0_user_state";
    private String session_key_user_provider = "auth0_user_provider";
    private String session_key_user_provider_acc_id = "auth0_user_provider_acc_id";
    private String session_key_user_provider_and_acc_id = "auth0_user_provider_and_acc_id";
    private String session_key_user_info_json = "auth0_user_info_json";
    private String session_key_user_login_caused_signup = "auth0_user_login_caused_signup";
    private String session_key_user_signup_caused_login = "auth0_user_signup_caused_login";
    private String session_key_user_ld_id = "auth0_user_ld_id";



    public String getAccessTokenFromAuthorizationCode(String confirmCode,String state,HttpServletRequest request){
        JSONObject tokenJson = getAccessTokenJsonFromAuthorizationCode(confirmCode,state,request);
        if (tokenJson != null && tokenJson.has(RESPONSE_JSON_ACCESS_TOKEN)){
            try{
                return  (String)tokenJson.get(RESPONSE_JSON_ACCESS_TOKEN);
            } catch (JSONException e){
                return null;
            }
        }else{
            return null;
        }
    }

    public JSONObject getUserInfoFromAccessToken(String accessToken){
        JSONObject userJson = null;

        URL url;
        HttpURLConnection connection = null;
        if (accessToken != null && accessToken.length()>0){
            try {
                //Create connection
                url = new URL(OAUTH_USER_INFO_URL + "?access_token="+accessToken);
                connection = (HttpURLConnection)url.openConnection();

                InputStream inputStream = connection.getInputStream();
                String encoding = connection.getContentEncoding();
                String body = IOUtils.toString(inputStream, encoding);
                userJson = new JSONObject(body);
//                logger.debug("user body :"+body);
                return userJson;

            } catch (Exception e){
                //TODO
                return userJson;
            }
        }
        return  userJson;
    }


    public JSONObject getAccessTokenJsonFromAuthorizationCode(String confirmCode,String state,HttpServletRequest request){
        JSONObject result = null;
        URL url;
        HttpURLConnection connection = null;

        JSONObject jsonToSend = new JSONObject();
        try{
            jsonToSend.put("client_id",CLIENT_ID);
            jsonToSend.put("client_secret",CLIENT_SECRET);
            jsonToSend.put("code",confirmCode);
            jsonToSend.put("grant_type",GRANT_TYPE_AUTO_CODE);
        }  catch (JSONException e){
            e.printStackTrace();
        }
        return apiCall(jsonToSend,OAUTH_TOKEN_URL,"POST","getAccessTokenJsonFromAuthorizationCode");
    }

    public boolean updateUserInfoInSession_pingAuth0(HttpSession session){
        String identifier = getUserFullIdentifierFromSession(session);
        return updateUserInfoInSession_pingAuth0(identifier,session);
    }
    public boolean updateUserInfoInSession_pingAuth0(String identifier,HttpSession session){
        if (identifier!= null){
            JSONObject userJson = getUserInfoByIndentifier(identifier);
            if (userJson != null){
                setUserInfoInSession(userJson,session);
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }


    public boolean checkUserEmailVerified_pingAuth0(String identifier){
        JSONObject userJson = getUserInfoByIndentifier(identifier);
        try{
            return userJson!=null && userJson.has("email_verified")
                    && (""+userJson.get("email_verified")).equals("true");
        } catch (JSONException e){
            return false;
        }
    }

    public JSONObject getUserInfoByIndentifier(String identifier){
        JSONObject result = null;
        if (identifier != null){
            result = apiCall(
                    null,
                    getGeneralAuthorizationBearerHeaders("getUserInfoByIndentifier"),
                    "https://"+DOMAIN+"/api/users/"+identifier,
                    "GET",
                    "getUserInfoByIndentifier");
        }
        return  result;
    }

    public String changeUserPasswordByUserIdentifier(String userIdentifier, String newPassword, boolean sendVerifivationEmail){
        String result = null;
        if (userIdentifier != null){
            if (newPassword !=null && newPassword.length()>0){

                JSONObject bodyJsonToSend = new JSONObject();
                try{
                    bodyJsonToSend.put("password",newPassword);
                    bodyJsonToSend.put("verify",sendVerifivationEmail);
                } catch (JSONException e){
                    //No check
                    bodyJsonToSend = null;
                }
                result = apiCallString(
                        bodyJsonToSend,
                        getGeneralAuthorizationBearerHeaders("changeUserPasswordByUserIdentifier"),
                        "https://"+DOMAIN+"/api/users/"+userIdentifier+"/password",
                        "PUT",
                        "changeUserPasswordByUserIdentifier");
            }
        }
        return result;
    }

    public String sendVerificationEmail(String userIdentifier,String sessionId){
        String result = null;
        if (userIdentifier != null){
            result = apiCallString(
                    null,
                    getGeneralAuthorizationBearerHeaders("changeUserPasswordByUserIdentifier"),
                    "https://"+DOMAIN+"/api/users/"+userIdentifier+"/send_verification_email",
                    "POST",
                    "sendVerificationEmail");
        }
        return result;
    }

    public boolean deleteUserByIdentifier(String identifier){
        boolean result = false;
        if (identifier != null){
            String response =
                    apiCallString(
                            null,
                            getGeneralAuthorizationBearerHeaders("deleteUserByIdentifier"),
                            "https://"+DOMAIN+"/api/users/"+identifier,
                            "DELETE",
                            "deleteUserByIdentifier");

            if (response!= null && response.toLowerCase().equals("ok")){
                result = true;
                //TODO KISSMETRICS here
            }
        }
        return result;
    }




    public void setUserInfoInSession(JSONObject userJson, HttpSession session){
        if (userJson != null){
            try{
                session.setAttribute(session_key_user_info_json,userJson);
                if (userJson.has("email")){
                    session.setAttribute(session_key_user_email,(String)userJson.get("email"));
                } else if (userJson.has("screen_name")){ //Twitter response has no email, screen_name only
                    session.setAttribute(session_key_user_email,(String)userJson.get("screen_name"));
                }else if (userJson.has("url")){
                    session.setAttribute(session_key_user_email,(String)userJson.get("url"));//Yahoo  response has url,
                }else if (userJson.has("link")){
                    session.setAttribute(session_key_user_email, (String) userJson.get("link"));//Facebook response has link,
                }

                if (userJson.has("name")){
                    session.setAttribute(session_key_user_fullname,(String)userJson.get("name"));//Required for intercom
                }

                session.setAttribute(session_key_user_provider_and_acc_id,userJson.has("user_id")? (String)userJson.get("user_id") : null);
                if (userJson.has("identities")){
                    try{
                        JSONObject identitiesJson = userJson.getJSONArray("identities").getJSONObject(0);
                        if (identitiesJson != null){
                            if (identitiesJson.has("provider")){
                                session.setAttribute(session_key_user_provider,""+identitiesJson.get("provider"));
                            }
                            if (identitiesJson.has("user_id")){
                                session.setAttribute(session_key_user_provider_acc_id,""+identitiesJson.get("user_id"));
                            }
                        }
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                } else if (userJson.has("user_id")){
                    String userIdWithSeparatorAndProvider = userJson.getString("user_id");
                    String[] providerAndUserIdStrings =  userIdWithSeparatorAndProvider.split("|");
                    if (providerAndUserIdStrings.length == 2){
                        session.setAttribute(session_key_user_provider,providerAndUserIdStrings[0]);
                        session.setAttribute(session_key_user_provider_acc_id,providerAndUserIdStrings[1]);

                    }
                }
            } catch (JSONException e){
              e.printStackTrace();
            }

        } else{
            //userJson is null
            cleanUpSessionFromIncompleteUserInfo(session);
        }
    }

    public User fillUserInfo(User user, HttpSession session){
        if (user != null && session.getAttribute(session_key_user_info_json) != null){
            JSONObject userInfo = (JSONObject) session.getAttribute(session_key_user_info_json);
            if (userInfo != null){
                try{
                    //critical info
                    if(userInfo.has("email")){
                        user.setName(userInfo.getString("email"));
                    } else if (userInfo.has("screen_name")){ //Twitter response has no email, screen_name only
                        user.setName(""+userInfo.get("screen_name"));
                    } else if (userInfo.has("url")){
                        user.setName(""+userInfo.get("url")); //Yahoo  response has url,
                    } else if (userInfo.has("link")){
                        user.setName(""+userInfo.get("link")); //Facebook response has link,
                    }

                    if(userInfo.has("user_id")){
                        user.setIdUserStr(userInfo.getString("user_id"));
                    }

                    if(userInfo.has("identities")){
                        JSONObject userIdentities = userInfo.getJSONArray("identities").getJSONObject(0);
                        if (userIdentities != null && userIdentities.has("provider")){
                            user.setProviderName(userIdentities.getString("provider"));
                        }
                    }

                    if(user.getProviderName() == null || user.getProviderName().length()==0){
                        //Has no identities inner json - > parse via user_Id and Separator
                        if (userInfo.has("user_id")){
                            user.setProviderName(userInfo.getString("user_id").split("|")[0]);
                        }
                    }

                    boolean criticalInfoPresent = user.getName() != null &&
                            user.getIdUserStr() != null &&
                            user.getProviderName()!= null;
                    criticalInfoPresent = criticalInfoPresent &&
                            user.getName().length()>0 &&
                            user.getIdUserStr().length()>0 &&
                            user.getProviderName().length()>0;
                    if (criticalInfoPresent){
                        if(userInfo.has("name")){
                            user.setName(userInfo.getString("name"));
                        }
                        if(userInfo.has("given_name")){
                            user.setName(userInfo.getString("given_name"));
                        }
                        if(userInfo.has("family_name")){
                            user.setSurname(userInfo.getString("family_name"));
                        }

                        if(userInfo.has("gender")){
                            user.setSex(""+userInfo.getString("gender").charAt(0));
                        }
                        return  user;
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    return null;
                }

            } else{

                return null;
            }
        } else {

            return null;
        }
        return null;
    }

    //Session methods

    private List<String> getSessionKeyNamesList(){
        List<String> sessionKeysList = new ArrayList<String>();
        sessionKeysList.add(session_key_user_email);
        sessionKeysList.add(session_key_user_provider);
        sessionKeysList.add(session_key_user_provider_acc_id);
        sessionKeysList.add(session_key_user_provider_and_acc_id);
        sessionKeysList.add(session_key_user_info_json);
        sessionKeysList.add(session_key_user_state);
        sessionKeysList.add(session_key_user_login_caused_signup);
        sessionKeysList.add(session_key_user_signup_caused_login);
        sessionKeysList.add(session_key_user_fullname);
        sessionKeysList.add(session_key_user_ld_id);
        return sessionKeysList;
    }

    //
    public void setUserLoginCausedSignUp(HttpSession session){
        session.setAttribute(session_key_user_login_caused_signup, "aga");
    }
    public void removeUserLoginCausedSignUpAttr(HttpSession session){
        session.removeAttribute(session_key_user_login_caused_signup);
    }

    public boolean userLoginCausedSignUp(HttpSession session){
        String present = (String)session.getAttribute(session_key_user_login_caused_signup);
        return  present != null;

    }

    public JSONObject getUserJsonInfoFromSession(HttpSession session){
        if (session.getAttribute(session_key_user_info_json)!= null){
            return (JSONObject) session.getAttribute(session_key_user_info_json);
        } else{
            return null;
        }
    }
    //

    public void setUserSignUpCausedLogin(HttpSession session){
        session.setAttribute(session_key_user_signup_caused_login,"aga");
    }
    public void removeUserSignUpCausedLoginAttr(HttpSession session){
        session.removeAttribute(session_key_user_signup_caused_login);
    }
    public boolean userSignUpCausedLogin(HttpSession session){
        String present = (String)session.getAttribute(session_key_user_signup_caused_login);
        return  present != null;

    }

    public void cleanUpSessionFromUserInfo(HttpSession session){
        List<String> sessionKeysList = getSessionKeyNamesList();
        for(String key: sessionKeysList){
            session.removeAttribute(key);
        }
    }

    public void cleanUpSessionFromIncompleteUserInfo(HttpSession session){
        List<String> sessionKeysList = getSessionKeyNamesList();
        boolean dataIsOk = true;
        for(String key: sessionKeysList){
            dataIsOk = dataIsOk && session.getAttribute(key)!=null;
        }
        if (!dataIsOk){
            for(String key: sessionKeysList){
                session.setAttribute(key,null);
            }
        }
    }

    public void setUserState(String state, HttpSession session){
        session.setAttribute(session_key_user_state,state);
    }

    public String getUserState(HttpSession session){
        return (String) session.getAttribute(session_key_user_state);
    }

    public String getUserProviderAccountIdFromSession(HttpSession session){
        return (String) session.getAttribute(session_key_user_provider_acc_id);
    }


    public String getUserProviderFromSession(HttpSession session){
        return (String) session.getAttribute(session_key_user_provider);
    }

    public boolean userProviderInSessionIsNotSocialAccount(HttpSession session){
        String provider = getUserProviderFromSession(session);
        if (provider == null){
            return false;
        } else{
            return provider.equals(providerNotSocialAccount);
        }
    }

    public String getUserEmailFromSession(HttpSession session){
        return (String) session.getAttribute(session_key_user_email);
    }
    public String getUserFullIdentifierFromSession(HttpSession session){
        return (String) session.getAttribute(session_key_user_provider_and_acc_id);
    }

    public String getUserFullNameFromService(HttpSession session){
        return (String) session.getAttribute(session_key_user_fullname);
    }

    public String getUserIdFromService(HttpSession session){
        return (String) session.getAttribute(session_key_user_ld_id);
    }

    public void setUserIdToService(HttpSession session, Integer userId){
        session.setAttribute(session_key_user_ld_id,""+userId);
    }


    //


    public boolean userProviderIsNotSocialAccount(User user){
        return user != null &&
                user.getProviderName() != null &&
                user.getProviderName().equals(providerNotSocialAccount);
    }


    //auth0 apiCaller methods

    private Map<String,String> getGeneralAuthorizationBearerHeaders(String invokerMethodName){
        Map<String,String> headers = null;
        JSONObject authenticationJson = getAuthorizationAccessJson();
        if (authenticationJson != null && authenticationJson.has(RESPONSE_JSON_ACCESS_TOKEN)){
            try{
                headers = new HashMap<String, String>();
                headers.put("Authorization","Bearer "+authenticationJson.get(RESPONSE_JSON_ACCESS_TOKEN));
            } catch (JSONException e){
                headers = null;
            }
        }
        return headers;
    }

    private JSONObject apiCall(JSONObject jsonRequestBodyToSend, String urlAndQuery, String requestMethod,String methodName){
        return apiCall(jsonRequestBodyToSend,null,urlAndQuery,requestMethod,methodName);
    }

    private JSONObject apiCall(JSONObject jsonRequestBodyToSend,
                               Map<String,String> headers,
                               String urlAndQuery,
                               String requestMethod,
                               String methodName){
        String response = apiCallString(jsonRequestBodyToSend, headers, urlAndQuery, requestMethod, methodName);
        JSONObject result =null;
        try{
            result = new JSONObject(response);
        } catch (JSONException e){
            long t  = new Date().getTime();
        }  catch (NullPointerException e){
            long t  = new Date().getTime();
        }
        return result;
    }

    private String apiCallString(JSONObject jsonRequestBodyToSend,Map<String,String> headers, String urlAndQuery, String requestMethod,String methodName){
        String result = null;
        URL url;
        HttpURLConnection connection = null;

        try {
            //Create connection
            url = new URL(urlAndQuery);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type","application/json");
            if (headers!= null){
                for (String headerKey: headers.keySet()){
                    connection.setRequestProperty(headerKey,headers.get(headerKey));
                }
            }
            connection.setUseCaches(false);
            connection.setDoInput(true);
            if (!requestMethod.equals("GET")){
                connection.setDoOutput(true);
                //Send request
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                if (jsonRequestBodyToSend !=null){
                    out.write(jsonRequestBodyToSend.toString());
                }
                out.flush();
                out.close();
            }

            boolean responseIsOk = connection.getResponseCode() == HttpURLConnection.HTTP_OK;

            InputStream inputStream = responseIsOk ? connection.getInputStream() : connection.getErrorStream();
            String encoding = connection.getContentEncoding();
            String body = IOUtils.toString(inputStream, encoding);
            inputStream.close();
            if (!responseIsOk){
                long t = new Date().getTime();

                if (jsonRequestBodyToSend != null){

                }
            }
            if (body != null && body.length()>0){
                result = body;
                if (result != null){
                    return result;
                }
            }
            return result;
        } catch (MalformedURLException e) {
            long t = new Date().getTime();
        }  catch (IOException e) {
            long t = new Date().getTime();

        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    private JSONObject getAuthorizationAccessJson(){
        if (authorizationToken == null ||
                Math.abs(new Date().getTime()-authorizationTokenSetDate) > authorizationTokenLifeTime
                ){
            JSONObject result = null;
            URL url;
            HttpURLConnection connection = null;

            JSONObject jsonToSend = new JSONObject();

            try{
                jsonToSend.put("client_id",CLIENT_ID);
                jsonToSend.put("client_secret",CLIENT_SECRET);
                jsonToSend.put("grant_type",GRANT_TYPE_CLIENT_CREDENTIALS);
            }  catch (JSONException e){
                e.printStackTrace();
            }
            result = apiCall(jsonToSend,OAUTH_TOKEN_URL,"POST","getAuthorizationAccessJson");

            if (result!=null){
                authorizationToken = result;
                authorizationTokenSetDate = new Date().getTime();
            }
            return  result;
        } else{
            return authorizationToken;
        }
    }
}

