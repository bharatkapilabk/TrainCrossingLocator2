package com.example.traincrossinglocator;

public class Train {

    String name,departure;

    public Train(String name, String departure) {
        this.name = name;
        this.departure = departure;
    }
    public  Train(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
