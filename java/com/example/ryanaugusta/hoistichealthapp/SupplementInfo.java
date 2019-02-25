// Project:     Holistic Health App
// Author:      Augusta
// Date:        2018
// File:        SupplementInfo.java

package com.example.ryanaugusta.hoistichealthapp;

/**
 * Created by ryanaugusta on 1/24/18.
 */

class SupplementInfo {

    // declare java references
    // this class handles the getters and setters for population of listView items and Database Test info.
    private String name;
    private String info;

    public SupplementInfo(String name, String info) throws Exception {

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
