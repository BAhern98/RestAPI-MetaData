package net.codejava.song.test;

//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;
//
//import net.codejava.song.model.Track;
//import net.codejava.song.model.TrackMetadata;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.junit.Assert.*;
//
//
//public class TrackControllerWireMockServerTest {
//
//    private WireMockServer wireMockServer;
//    private RestTemplate restTemplate;
//
//    @Before
//    public void setUp() {
//        wireMockServer = new WireMockServer();
//        wireMockServer.start();
//
//        configureFor("localhost", wireMockServer.port());
//
//        stubFor(post(urlPathEqualTo("/codechallenge/createTrack"))
//                .withQueryParam("isrc", equalTo("TEST_ISRC"))
//                .withRequestBody(equalToJson("{\"name\":\"test_name\",\"durationMs\":1000,\"explicit\":true}"))
//                .willReturn(aResponse()
//                        .withStatus(HttpStatus.OK.value())
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody("{\"isrc\":\"TEST_ISRC\",\"name\":\"test_name\",\"durationMs\":1000,\"explicit\":true}")));
//
//        restTemplate = new RestTemplate();
//    }
//
//    @After
//    public void tearDown() {
//        wireMockServer.stop();
//    }
//
//    @Test
//    public void testCreateTrack() {
//        TrackMetadata metadata = new TrackMetadata(1000L, "test_name", true);
//        ResponseEntity<Track> responseEntity = restTemplate.postForEntity(
//                "http://localhost:" + wireMockServer.port() + "/codechallenge/createTrack?isrc=TEST_ISRC",
//                metadata, Track.class);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("TEST_ISRC", responseEntity.getBody().getIsrc());
//        assertEquals("test_name", responseEntity.getBody().getName());
//        assertEquals(1000L, (long) responseEntity.getBody().getDurationMs());
//        assertEquals(true, responseEntity.getBody().isExplicit());
//    }
//}

