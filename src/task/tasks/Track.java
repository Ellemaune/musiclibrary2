package task.tasks;

import java.time.Duration;

public class Track {

    private String name;
    private String singer;
    private String album;
    private Duration recordLength;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Duration getRecordLength() {
        return recordLength;
    }

    public void setRecordLength(Duration recordLength) {
        this.recordLength = recordLength;
    }
}
