package net.codejava.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.song.model.Track;
import net.codejava.song.model.TrackMetadata;
import net.codejava.song.repo.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Override
    public Track createTrack(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public Track getTrack(String isrc) {
        return trackRepository.findByIsrc(isrc);
    }
}