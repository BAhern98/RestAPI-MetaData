package net.codejava.song.model;

public class TrackMetadata {
    private Long durationMs;
    private String name;
    private boolean explicit;

    public TrackMetadata(Long durationMs, String name, boolean explicit) {
        this.durationMs = durationMs;
        this.name = name;
        this.explicit = explicit;
    }

    public Long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }
}