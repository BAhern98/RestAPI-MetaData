package net.codejava.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.song.model.Track;
import net.codejava.song.model.TrackMetadata;
import net.codejava.song.repo.TrackRepository;

@Service
public class TrackService {
    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track createTrack(String isrc, TrackMetadata metadata) {
        Track track = new Track();
        track.setIsrc(isrc);
        track.setName(metadata.getName());
        track.setDurationMs(metadata.getDurationMs());
        track.setExplicit(metadata.isExplicit());
        return trackRepository.save(track);
    }

    public Track getTrack(String isrc) {
    	return trackRepository.findByIsrc(isrc);
    }
}