package net.codejava.song.model;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "track")
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "isrc", unique = true, nullable = false, length = 20)
	private String isrc;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "duration_ms", nullable = false)
	private Long durationMs;

	@Column(name = "explicit", nullable = false)
	private Boolean explicit;

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

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

	public Boolean getExplicit() {
		return explicit;
	}

	public void setExplicit(Boolean explicit) {
		this.explicit = explicit;
	}

	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}

	// default constructor for JPA
	public Track() {

	}

	// constructor with arguments
	public Track(String isrc, String name, Long durationMs, Boolean explicit) {
		this.isrc = isrc;
		this.name = name;
		this.durationMs = durationMs;
		this.explicit = explicit;
		this.createdAt = LocalDateTime.now();
	}

}
