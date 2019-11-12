package com.example.traincrossinglocator;

public class TrainData {
    String status,time;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TrainData(String status, String time) {
        this.status = status;
        this.time = time;
    }
}
