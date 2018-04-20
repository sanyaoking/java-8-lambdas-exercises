package com.insightfullogic.java8.examples.chapter4;

import java.util.ArrayList;

// BEGIN body
public class MusicalCarriage
        implements Carriage, Jukebox {

    @Override
    public String rock() {
        return Carriage.super.rock();
    }

}
// END body
