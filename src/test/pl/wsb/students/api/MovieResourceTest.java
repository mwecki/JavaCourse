package pl.wsb.students.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
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

class MovieResourceTest {

    @Test
    void postMovie_whenValidDataSend_then200IsReceived() throws IOException {

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
                put("title", "test888");
                put("director", "Martyn Wecki");
                put("year", "2020");
                put("genre", "Action");
                put("category", "PG");
            }
        };
        ObjectMapper objectDataMapper = new ObjectMapper();
        String requestDataBody = objectDataMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(mapData);

        HttpPost httpPostData = new HttpPost("http://127.0.0.1:8080/api/v1/movie");
        StringEntity requestDataEntity = new StringEntity(
                requestDataBody,
                ContentType.APPLICATION_JSON
        );
        httpPostData.setEntity(requestDataEntity);
        httpPostData.setHeader("Accept", "application/json");
        httpPostData.setHeader("Content-type", "application/json");
        httpPostData.setHeader("Authorization", "Bearer: " + jsonObject.get("access_token"));
        CloseableHttpResponse response = client.execute(httpPostData);
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        client.close();
    }
}