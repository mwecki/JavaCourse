package pl.wsb.students.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class UserResourceTest {
    @Test
    void putNewPassword_whenInvalidDataSend_then400IsReceived() throws IOException {

        Map<String, String> map = new HashMap<String, String>() {
            {
                put("email", "uzytkownik@java-course-wsb.pl");
                put("password", "uzytkownik1");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);

        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/v1/authenticate");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse responseLogin = client.execute(httpPost);

        String json = EntityUtils.toString(responseLogin.getEntity(), "UTF-8");
        Object obj = JSONValue.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        Map<String, String> mapData = new HashMap<String, String>() {
            {
                put("email", "uzytkownik@java-course-wsb.pl");
                put("oldpassword", "uzytkownik1");
            }
        };
        ObjectMapper objectDataMapper = new ObjectMapper();
        String requestDataBody = objectDataMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(mapData);

        HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/api/v1/user/changepassword");
        StringEntity requestDataEntity = new StringEntity(
            requestDataBody,
            ContentType.APPLICATION_JSON
        );
        httpPut.setEntity(requestDataEntity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        httpPut.setHeader("Authorization", "Bearer: " + jsonObject.get("access_token"));
        CloseableHttpResponse response = client.execute(httpPut);
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
        client.close();
    }

    @Test
    void putNewPassword_whenValidDataSend_then200IsReceived() throws IOException {

        Map<String, String> map = new HashMap<String, String>() {
            {
                put("email", "uzytkownik@java-course-wsb.pl");
                put("password", "uzytkownik1");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);

        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/v1/authenticate");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse responseLogin = client.execute(httpPost);

        String json = EntityUtils.toString(responseLogin.getEntity(), "UTF-8");
        Object obj = JSONValue.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        Map<String, String> mapData = new HashMap<String, String>() {
            {
                put("email", "uzytkownik@java-course-wsb.pl");
                put("oldpassword", "uzytkownik1");
                put("newpassword", "uzytkownik1");
            }
        };
        ObjectMapper objectDataMapper = new ObjectMapper();
        String requestDataBody = objectDataMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(mapData);

        HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/api/v1/user/changepassword");
        StringEntity requestDataEntity = new StringEntity(
                requestDataBody,
                ContentType.APPLICATION_JSON
        );
        httpPut.setEntity(requestDataEntity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        httpPut.setHeader("Authorization", "Bearer: " + jsonObject.get("access_token"));
        CloseableHttpResponse response = client.execute(httpPut);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        client.close();
    }

    @Test
    void logout_SuccessIsReceived() throws IOException {

        Map<String, String> map = new HashMap<String, String>() {
            {
                put("email", "uzytkownik@java-course-wsb.pl");
                put("password", "uzytkownik1");
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);

        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/api/v1/authenticate");
        StringEntity requestEntity = new StringEntity(
                requestBody,
                ContentType.APPLICATION_JSON
        );
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse responseLogin = client.execute(httpPost);

        String json = EntityUtils.toString(responseLogin.getEntity(), "UTF-8");
        Object obj = JSONValue.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        HttpDelete httpDelete = new HttpDelete("http://127.0.0.1:8080/api/v1/user/logout");
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-type", "application/json");
        httpDelete.setHeader("Authorization", "Bearer: " + jsonObject.get("access_token"));
        CloseableHttpResponse response = client.execute(httpDelete);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        client.close();
    }
}