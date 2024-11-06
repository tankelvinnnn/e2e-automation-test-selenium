package com.selenium.common;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIClient {
    private static CloseableHttpClient httpClient = HttpClients.createDefault();
    
    public static HttpResponse sendRequest(String method, String url, String body, String bearerToken) throws Exception {
        HttpRequestBase request;
    
        switch (method.toUpperCase()) {
            case "POST" -> {
                HttpPost postRequest = new HttpPost(url);
                if (body != null) {
                    postRequest.setEntity(new StringEntity(body));
                }
                request = postRequest;
            }
            case "PUT" -> {
                HttpPut putRequest = new HttpPut(url);
                if (body != null) {
                    putRequest.setEntity(new StringEntity(body));
                }
                request = putRequest;
            }
            case "DELETE" -> request = new HttpDelete(url);
            case "GET" -> request = new HttpGet(url);
            default -> throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    
        // Set header Authorization jika ada token
        request.setHeader("Content-type", "application/json");
        if (bearerToken != null && !bearerToken.isEmpty()) {
            request.setHeader("Authorization", "Bearer " + bearerToken);
        }
        
        // Eksekusi permintaan
        return httpClient.execute(request);
    }

    public static Response sendRequestRest(String method, String url, String body, String bearerToken) throws Exception {
        RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");
        
        if (bearerToken != null && !bearerToken.isEmpty()) {
            request.header("Authorization", "Bearer " + bearerToken);
        }
        
        if (body != null) {
            request.body(body);
        }
        
        Response response;
        switch (method.toUpperCase()) {
            case "GET" -> response = request.get(url);
            case "POST" -> response = request.post(url);
            case "PUT" -> response = request.put(url);
            case "DELETE" -> response = request.delete(url);
            default -> throw new IllegalArgumentException("HTTP method tidak valid: " + method);
        }
        
        return response;
    }

    public static void displayResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(response.getEntity());

        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + responseBody);
    }

    public static void displayResponse(Response response) {
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Headers: " + response.getHeaders().toString());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
    }

    public void close() throws Exception {
        httpClient.close();
    }

}
