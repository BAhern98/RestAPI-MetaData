package net.codejava.song.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Track {
    
    @Id
    private String isrc;
    private String name;
    private int durationMs;
    private boolean explicit;
    
    public Track() {
        // default constructor
    }

    public Track(String isrc, String name, int durationMs, boolean explicit) {
        this.isrc = isrc;
        this.name = name;
        this.durationMs = durationMs;
        this.explicit = explicit;
    }

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDurationMs() {
		return durationMs;
	}

	public void setDurationMs(int durationMs) {
		this.durationMs = durationMs;
	}

	public boolean isExplicit() {
		return explicit;
	}

	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}
    
    
    
    
    // constructors, getters, and setters
    
}