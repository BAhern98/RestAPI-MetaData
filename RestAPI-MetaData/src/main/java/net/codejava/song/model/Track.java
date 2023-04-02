package net.codejava.song.model;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Track {
    
    @Id
    @NaturalId
    private String isrc;
    private String name;
    private Long durationMs;
    private boolean explicit;
    
    public Track() {
        // default constructor
    }

    public Track(String isrc, String name, Long durationMs, boolean explicit) {
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

	public Long getDurationMs() {
		return durationMs;
	}

	public void setDurationMs(Long durationMs) {
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