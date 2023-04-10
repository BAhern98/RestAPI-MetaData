//package net.codejava.song;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockserver.integration.ClientAndServer.startClientAndServer;
//import static org.mockserver.model.HttpRequest.request;
//import static org.mockserver.model.HttpResponse.response;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Test;
//import org.mockserver.client.MockServerClient;
////import org.mockserver.client.server.MockServerClient;
//import org.mockserver.integration.ClientAndServer;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class RestApiMetaDataApplicationTests {
//    private ClientAndServer mockServer;
//
////    @BeforeClass
////    public void startServer() throws Exception {
////        mockServer = startClientAndServer(1080);
////    }
//// 
//    @AfterClass 
//    public void stopServer() { 
//        mockServer.stop();
//        
//    }
//    
//  
//    @Test
//    public void shouldReturnTrack() throws Exception {
//        mockServer = startClientAndServer(1080);
//
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
////    @Test
////    void testCreateTrack() throws Exception {
////        // Start MockServer and configure response
////        MockServerClient mockServer = startClientAndServer(1080);
////        String isrc = "TEST1234560000";
////        String responseBody = "{\"id\": \"1\",\"isrc\": \"" + isrc + "\",\"name\": \"Some new track!\",\"duration_ms\": 12000000,\"explicit\": true}";
////
////        mockServer.when(
////                request()
////                        .withMethod("POST")
////                        .withPath("/createTrack")
////                        .withHeader("Authorization", "Bearer " + JWT_TOKEN)
////                        .withQueryStringParameter("isrc", isrc)
////                        .withBody(json("{\"name\":\"Some new track!\",\"duration_ms\":12000000,\"explicit\":true}"))
////        ).respond(
////                response()
////                        .withStatusCode(201)
////                        .withBody(responseBody)
////                        .withHeader("Content-Type", "application/json")
////        );
////
////        // Send HTTP request to endpoint
////        HttpClient httpClient = HttpClient.newHttpClient();
////        HttpRequest httpRequest = HttpRequest.newBuilder()
////                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Some new track!\",\"duration_ms\":12000000,\"explicit\":true}"))
////                .uri(URI.create("http://localhost:8080/createTrack?isrc=" + isrc))
////                .header("Content-Type", "application/json")
////                .header("Authorization", "Bearer " + JWT_TOKEN)
////                .build();
////        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
////
////        // Verify response
////        assertEquals(201, httpResponse.statusCode());
////        assertEquals(responseBody, httpResponse.body());
////    }
//
//
// 
//
//}
