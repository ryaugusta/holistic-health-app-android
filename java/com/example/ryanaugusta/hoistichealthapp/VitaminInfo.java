// Project:     Holistic Health App
// Author:      Augusta
// Date:        1/25/18
// File:        VitaminInfo.java

package com.example.ryanaugusta.hoistichealthapp;

/**
 * Created by ryanaugusta on 1/24/18.
 */

class VitaminInfo {
    // declare java references
    // this class handles all the vitamin getters and setters for population of ListView.
    private String name;
    private String info;


    public VitaminInfo(String name, String info) throws Exception {

        this.name = name;
        this.info = info;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;

    }

    public String getInfo() {

        return info;

    }

    public void setInfo(String info) {

        this.info = info;

    }

    @Override
    public String toString()
    {
        return name + ": " + info;
    }
}
