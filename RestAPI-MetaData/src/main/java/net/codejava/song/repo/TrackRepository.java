package net.codejava.song.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.song.model.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
	
    Track findByIsrc(String isrc);

}