package net.codejava.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.song.model.Track;
import net.codejava.song.model.TrackMetadata;
import net.codejava.song.service.TrackService;

@RestController
@RequestMapping("/codechallenge")
public class TrackController {
    
    @Autowired
    private TrackService trackService;
    
//    @PostMapping("/createTrack")
//    public void createTrack(@RequestParam String isrc) {
//        trackService.createTrack(isrc);
//    }
    @PostMapping("/createTrack")
    public ResponseEntity<Track> createTrack(@RequestParam String isrc, @RequestBody TrackMetadata metadata) {
        Track track = trackService.createTrack(isrc, metadata);
        return ResponseEntity.ok(track);
    }
    
    @GetMapping("/getTrack")
    public ResponseEntity<Track> getTrack(@RequestParam String isrc) {
        Track track = trackService.getTrack(isrc);
        return ResponseEntity.ok(track);
    }
}