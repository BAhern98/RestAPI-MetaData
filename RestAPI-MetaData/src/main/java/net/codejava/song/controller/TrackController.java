package net.codejava.song.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import net.codejava.song.model.Track;
import net.codejava.song.model.TrackMetadata;
import net.codejava.song.service.TrackService;

@RestController
@RequestMapping("/codechallenge")
public class TrackController {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	@Autowired
	private TrackService trackService;

//    @PostMapping("/createTrack")
//    public ResponseEntity<Track> createTrack(@RequestParam String isrc, @RequestBody Track track) {
//        try {
//        	   // Check if the user is authorized to access the track
//            if (!checkUserAuthorization(isrc)) {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//            // Validate input parameters
//            if (isrc == null || isrc.isEmpty()) {
//                throw new IllegalArgumentException("isrc parameter is required");
//            }
//            if (track == null) {
//                throw new IllegalArgumentException("Track object is required");
//            }
//            
//            // Set the isrc value in the track object
//            track.setIsrc(isrc);
//
//            // Create the track
//            Track createdTrack = trackService.createTrack(track);
//            return new ResponseEntity<>(createdTrack, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            // Return a 400 Bad Request response if the input parameters are invalid
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            // Log the exception and return a 500 Internal Server Error response
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

	@PostMapping("/createTrack")
	public ResponseEntity<Track> createTrack(@RequestParam String isrc, @RequestBody Track track, @RequestHeader("Authorization") String authToken) {
	    try {
	        // Check if the user is authorized to access the track
	        if (!checkAuthToken(authToken)) {
	            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	        }
	        // Validate input parameters
	        if (isrc == null || isrc.isEmpty()) {
	            throw new IllegalArgumentException("isrc parameter is required");
	        }
	        if (track == null) {
	            throw new IllegalArgumentException("Track object is required");
	        }
	        
	        // Set the isrc value in the track object
	        track.setIsrc(isrc);

	        // Create the track
	        Track createdTrack = trackService.createTrack(track);
	        return new ResponseEntity<>(createdTrack, HttpStatus.CREATED);
	    } catch (IllegalArgumentException e) {
	        // Return a 400 Bad Request response if the input parameters are invalid
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        // Log the exception and return a 500 Internal Server Error response
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@GetMapping("/getTrack")
	public ResponseEntity<Track> getTrack(@RequestParam String isrc, @RequestHeader("Authorization") String authToken) {
		try {
			// Check if the user is authorized to access the track
			if (!checkAuthToken(authToken)) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			Track track = trackService.getTrack(isrc);
			if (track == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(track, HttpStatus.OK);
		} catch (Exception e) {
			// Log the exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean checkAuthToken(String authToken) {
	    try {
	        Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(authToken);
	        return true;
	    } catch (JwtException e) {
	        return false;
	    }
	}
//    @GetMapping("/getTrack")
//    public ResponseEntity<Track> getTrack(@RequestParam String isrc) {
//        Track track = trackService.getTrack(isrc);
//        if (track == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(track, HttpStatus.OK);
//    }

}