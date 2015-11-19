package de.netorium.watchfoldernew.watchfoldernew;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

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

    public static class FileEventData {

        public FileEventData(File file, OperatingSystemFileEvent fileEvent) {
            this.file = file;
            this.fileEvent = fileEvent;
        }
          
        private File file;

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }
      
        private OperatingSystemFileEvent fileEvent;

        public OperatingSystemFileEvent getFileEvent() {
            return fileEvent;
        }

        public void setFileEvent(OperatingSystemFileEvent fileEvent) {
            this.fileEvent = fileEvent;
        }
    }
    
    private final List<FileEventData> fileEvents = new ArrayList<>();
    private String absolutePath;
    
    public SpiedFile(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public void addEvent(File file, OperatingSystemFileEvent osFileEvent)
    {
        if (!file.getAbsolutePath().equals(absolutePath))
        {
            String message = String.format("FileEvent of file %s does not fit to SpiedFile %s", file.getAbsolutePath(),
                    absolutePath);
            throw new InvalidParameterException();
        }
        
        fileEvents.add(new FileEventData(file, osFileEvent));
    }
}
