package net.codejava.song;

import static org.junit.Assert.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.springframework.boot.test.context.SpringBootTest;

import net.codejava.song.service.Token;

@SpringBootTest
class RestApiMetaDataApplicationTests {
    private ClientAndServer mockServer;
	String accessToken = Token.generateAccessToken();
//not working :(
//    @BeforeClass
//    public void startServer() throws Exception {
//        mockServer = startClientAndServer(1080);
//    }
// 
//    @AfterClass 
//    public void stopServer() { 
//        mockServer.stop();
//        
//    }
    
    @Test
    public void getTrackTest_200() throws Exception {
    	
        mockServer = startClientAndServer(1080);

        String isrc = "TEST1234560000";

        String expectedResponseBody = "{\"id\":\"1234\",\"name\":\"Some new track!\",\"duration_ms\":12000000,\"explicit\":true,\"artistsList\":[\"Artist 1\", \"Artist 2\"]}";

        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/getTrack")
                    .withQueryStringParameter("isrc", isrc)
                    .withHeader("Authorization", accessToken)
            )
            .respond(
                response()
                    .withStatusCode(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(expectedResponseBody)
            );

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:1080/getTrack?isrc=" + isrc))
            .header("Authorization", accessToken)
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, httpResponse.statusCode());
        assertEquals(expectedResponseBody, httpResponse.body());
        mockServer.stop();

    }
    
    @Test
    public void getTrackTest_401() throws Exception {

        // Start the MockServer
        mockServer = startClientAndServer(1080);

        // Set up the test data
        String isrc = "TEST1234560000"; 

        // Set up the expected response body
        String expectedResponseBody = "{\"id\":\"1234\",\"name\":\"Some new track!\",\"duration_ms\":12000000,\"explicit\":true,\"artistsList\":[\"Artist 1\", \"Artist 2\"]}";

        // Set up the MockServer to respond to a GET request to /getTrack with query parameter 'isrc' and header 'Authorization'
        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/getTrack")
                    .withQueryStringParameter("isrc", isrc)
                    .withHeader("Authorization", accessToken)
            )
            // Respond with a 401 Unauthorized status code
            .respond(
                response()
                    .withStatusCode(401)
            );

        // Send a GET request to the MockServer
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:1080/getTrack?isrc=" + isrc))
            .header("Authorization", accessToken)
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Verify that the response status code is 401 Unauthorized
        assertEquals(401, httpResponse.statusCode());

        // Stop the MockServer
        mockServer.stop();

    }
    
    @Test
    public void getTrackTest_404() throws Exception {
        
        // Start the mock server
        mockServer = startClientAndServer(1080);

        /// Set an invalid ISRC code
        String isrc = "INVALID_ISRC_CODE";

        // Set up the expected response body
        String expectedResponseBody = "{\"status\":\"404\",\"error\":\"Not Found\",\"message\":\"Track not found\",\"path\":\"/getTrack\"}";

        // Set up the mock server to respond with a 404 status code and the expected response body
        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/getTrack")
                    .withQueryStringParameter("isrc", isrc)
                    .withHeader("Authorization", accessToken)
            )
            .respond(
                response()
                    .withStatusCode(404)
                    .withHeader("Content-Type", "application/json")
                    .withBody(expectedResponseBody)
            );

        // Send a GET request to the mock server
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:1080/getTrack?isrc=" + isrc))
            .header("Authorization", accessToken)
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Check that the response has a 404 status code and the expected response body
        assertEquals(404, httpResponse.statusCode());
        assertEquals(expectedResponseBody, httpResponse.body());

        // Stop the mock server
        mockServer.stop();
    }

    @Test
    public void getTrackTest_500() throws Exception {
        
        // start the mock server
        mockServer = startClientAndServer(1080);

        String isrc = "TEST1234560000";
        
        // create a mock response with status code 500
        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("GET")
                    .withPath("/getTrack")
                    .withQueryStringParameter("isrc", isrc)
                    .withHeader("Authorization", accessToken)
            )
            .respond(
                response()
                    .withStatusCode(500)
            );

        // send a request to the endpoint
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:1080/getTrack?isrc=" + isrc))
            .header("Authorization", accessToken)
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // check that the response status code is 500
        assertEquals(500, httpResponse.statusCode());
        
        // stop the mock server
        mockServer.stop();
    }


    
    @Test
    public void createTrackTest_201() throws Exception {
        mockServer = startClientAndServer(1080);

        String isrc = "TEST1234560000";
        String requestBody = "{\"name\": \"Some new track!\",\"duration_ms\": 12000000, \"explicit\": true, \"artistsList\": [\"Artist 1\", \"Artist 2\"]}";
        String responseBody = "{\"id\": \"1234\",\"name\": \"Some new track!\",\"duration_ms\": 12000000, \"explicit\": true, \"artistsList\": [\"Artist 1\", \"Artist 2\"]}";

        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("POST")
                    .withPath("/codechallenge/createTrack/" + isrc)
                    .withHeader("MockServerClient", accessToken)
                    .withBody(requestBody)
            )
            .respond(
                response()
                    .withStatusCode(201)
                    .withBody(responseBody)
                    .withHeader("Content-Type", "application/json")
            );

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .uri(URI.create("http://localhost:1080/codechallenge/createTrack/" + isrc))
            .header("Content-Type", "application/json")
            .header("MockServerClient", accessToken)
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String response = httpResponse.body();

        assertEquals(responseBody, response);
        assertEquals(201, httpResponse.statusCode());
        mockServer.stop();
    }

    @Test
    public void createTrackTest_400() throws Exception {
        mockServer = startClientAndServer(1080);


        // Set up test data
        String isrc = "TEST1234560000";
        String requestBody = "{\"name\": \"Some new track!\",\"duration_ms\": 12000000, \"explicit\": true, \"artistsList\": [\"Artist 1\", \"Artist 2\"]}";
        String responseBody = "{\"error\": \"Bad Request\"}";

        // Set up MockServer expectations
        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("POST")
                    .withPath("/codechallenge/createTrack/" + isrc)
                    .withHeader("Authorization", accessToken)
                    .withBody(requestBody)
            )
            .respond(
                response()
                    .withStatusCode(400)
                    .withBody(responseBody)
                    .withHeader("Content-Type", "application/json")
            );

        // Send the HTTP request to the MockServer
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .uri(URI.create("http://localhost:1080/codechallenge/createTrack/" + isrc))
            .header("Content-Type", "application/json")
            .header("Authorization", accessToken)
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String response = httpResponse.body();

        // Verify the response from the MockServer
        assertEquals(responseBody, response);
        assertEquals(400, httpResponse.statusCode());
        mockServer.stop();

    }



    @Test
    public void createTrackTest_401() throws Exception {
    	
        mockServer = startClientAndServer(1080);


        String isrc = "TEST1234560000";

        // set up a mock server response to return a 401 Unauthorized
        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("POST")
                    .withPath("/codechallenge/createTrack/" + isrc)
                    .withHeader("Authorization", accessToken)
            )
            .respond(
                response()
                    .withStatusCode(401)
                    .withHeader("Content-Type", "application/json")
            );

        // send an HTTP request to the mock server
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.noBody())
            .uri(URI.create("http://localhost:1080/codechallenge/createTrack/" + isrc))
            .header("Content-Type", "application/json")
            .header("Authorization", accessToken)
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String response = httpResponse.body();

        // verify that the response is empty and the status code is 401 Unauthorized
        assertEquals("", response);
        assertEquals(401, httpResponse.statusCode());
        mockServer.stop();

    }




    
    @Test
    public void createTrackTest_500() throws Exception {
    	
        mockServer = startClientAndServer(1080);


        String isrc = "TEST1234560000";

        new MockServerClient("localhost", 1080)
            .when(
                request()
                    .withMethod("POST")
                    .withPath("/codechallenge/createTrack/" + isrc)
                    .withHeader("Authorization", accessToken)
            )
            .respond(
                response()
                    .withStatusCode(500)
                    .withHeader("Content-Type", "application/json")
            );

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.noBody())
            .uri(URI.create("http://localhost:1080/codechallenge/createTrack/" + isrc))
            .header("Content-Type", "application/json")
            .header("Authorization", accessToken)
            .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseBody = httpResponse.body();

        assertEquals(500, httpResponse.statusCode());
        mockServer.stop();

    }
    




    



 

}
