//package net.codejava.song.test;
//import static org.junit.Assert.assertEquals;
//
//import static org.mockserver.integration.ClientAndServer.startClientAndServer;
//import static org.mockserver.model.HttpRequest.request;
//import static org.mockserver.model.HttpResponse.response;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
////import org.mockserver.client.server.MockServerClient;
////import org.mockserver.client.MockServerClient;
//import org.mockserver.integration.ClientAndServer;
//import org.springframework.boot.test.context.SpringBootTest;


//@SpringBootTest
//public class TestMockServer {
//
//    private ClientAndServer mockServer;
//
//    @BeforeClass
//    public void startServer() {
//        mockServer = startClientAndServer(1080);
//    }
// 
//    @AfterClass 
//    public void stopServer() { 
//        mockServer.stop();
//    }
//    
//    
//    @Test
//    public void shouldReturnTrack() throws Exception{
//        String isrc = "TEST1234560000";
//        String responseBody = "{\"id\": \"1234\",\"name\": \"Some new track!\",\"duration_ms\": 12000000}";
//        
//        new MockServerClient("localhost", 1080)
//            .when(
//                request()
//                    .withMethod("GET")
//                    .withPath("/codechallenge/createTrack/" + isrc)
//            )
//            .respond(
//                response()
//                    .withStatusCode(200)
//                    .withBody(responseBody)
//                    .withHeader("Content-Type", "application/json")
//            );
//        
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("http://localhost:1080/codechallenge/createTrack/" + isrc);
//
//        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//
//        String responseString = EntityUtils.toString(httpResponse.getEntity());
//        int statusCode = httpResponse.getStatusLine().getStatusCode();
//
//        assertEquals(200, statusCode);
//        assertEquals(responseBody, responseString);
//
//        httpClient.close();
////    }
//    @Test
//    public void shouldReturnTrack() throws Exception {
//        String isrc = "TEST1234560000";
//        String responseBody = "{\"id\": \"1234\",\"name\": \"Some new track!\",\"duration_ms\": 12000000}";
//
//        new MockServerClient("localhost", 1080)
//            .when(
//                request()
//                    .withMethod("GET")
//                    .withPath("/codechallenge/createTrack/" + isrc)
//            )
//            .respond(
//                response()
//                    .withStatusCode(200)
//                    .withBody(responseBody)
//                    .withHeader("Content-Type", "application/json")
//            );
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest httpRequest = HttpRequest.newBuilder()
//            .GET()
//            .uri(URI.create("http://localhost:1080/codechallenge/createTrack/" + isrc))
//            .header("Content-Type", "application/json")
//            .build();
//        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//        String response = httpResponse.body();
//
//        assertEquals(responseBody, response);
//        assertEquals(200, httpResponse.statusCode());
//    }
//
// 
//    // ...
//}