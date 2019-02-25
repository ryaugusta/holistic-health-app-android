package com.example.ryanaugusta.hoistichealthapp;

/**
 * Created by ryanaugusta on 2/18/18.
 */

public class MyStackInfo {

    // declare java references
    // this class handles the getters and setters for population of listView items and Database Test info.
    private String name;

    public MyStackInfo(String name) throws Exception {

        this.name = name;

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;

    }

    @Override
    public String toString()
    {
        return name;
    }
}
