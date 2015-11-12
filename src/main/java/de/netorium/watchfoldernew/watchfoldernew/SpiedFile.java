package de.netorium.watchfoldernew.watchfoldernew;

import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author riesa
 */
public class SpiedFile {

    public void addEvent(File file, OperatingSystemFileEvent osFileEvent)
    {
        //TODO save information of file:
        //EventType
    }
    
        private String AbsolutePath;

    /**
     * Get the value of AbsolutePath
     *
     * @return the value of AbsolutePath
     */
    public String getAbsolutePath() {
        return AbsolutePath;
    }

    /**
     * Set the value of AbsolutePath
     *
     * @param AbsolutePath new value of AbsolutePath
     */
    public void setAbsolutePath(String AbsolutePath) {
        this.AbsolutePath = AbsolutePath;
    }

    
}
