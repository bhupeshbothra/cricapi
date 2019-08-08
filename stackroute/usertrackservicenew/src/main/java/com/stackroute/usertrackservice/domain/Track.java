package com.stackroute.usertrackservice.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

    @Id
    private String trackId;

    @JsonProperty("name")
    private String trackName;

    private String comment;

    @JsonProperty("listeners")
    private String trackListeners;

    @JsonProperty("url")
    private  String trackUrl;

    private Artist artist;

    public Track() {
    }

    public Track(String trackId, String trackName, String comment, String trackListeners, String trackUrl, Artist artist) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.comment = comment;
        this.trackListeners = trackListeners;
        this.trackUrl = trackUrl;
        this.artist = artist;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTrackListeners() {
        return trackListeners;
    }

    public void setTrackListeners(String trackListeners) {
        this.trackListeners = trackListeners;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", comment='" + comment + '\'' +
                ", trackListeners='" + trackListeners + '\'' +
                ", trackUrl='" + trackUrl + '\'' +
                ", artist=" + artist +
                '}';
    }
}
