package pl.wsb.students.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class AuthenticateResourceTest {

    @Test
    void postAuthenticate_whenInvalidCredentials_then401IsReceived() throws IOException
    {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("email", "uzytkownik1@java-course-wsb.pl");
                put("password", "zle_haslo");
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
        CloseableHttpResponse response = client.execute(httpPost);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_UNAUTHORIZED);
        client.close();
    }

    @Test
    void postAuthenticate_whenValidCredentials_then200IsReceived() throws IOException {
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
        CloseableHttpResponse response = client.execute(httpPost);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);
        client.close();
    }
}