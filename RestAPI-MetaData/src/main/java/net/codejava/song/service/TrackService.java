package net.codejava.song.service;

import net.codejava.song.model.Track;

public interface TrackService {

    Track createTrack(Track track);

    Track getTrack(String isrc);
}