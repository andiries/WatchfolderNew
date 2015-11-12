/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.netorium.watchfoldernew.watchfoldernew;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author riesa
 */
public class FileMonitor implements FileAlterationListener{

    private final Logger logger = LoggerFactory.getLogger(FileMonitor.class);

    private FileEventInterpreter fileEventInterpreter;
    
    private final ConcurrentMap<String, SpiedFile> spiedFiles = new ConcurrentHashMap<>();
    
    public FileMonitor() {
        createFileEventInterpreter();
    }

    private void createFileEventInterpreter() {
        if (SystemUtils.IS_OS_WINDOWS)
            fileEventInterpreter = new NtfsFileEventInterpreter();
        else
            fileEventInterpreter = new GenericFileEventInterpreter();
    }
    
    @Override
    public void onStart(FileAlterationObserver fao) {
    }

    @Override
    public void onDirectoryCreate(File file) {
    }

    @Override
    public void onDirectoryChange(File file) {
    }

    @Override
    public void onDirectoryDelete(File file) {
    }

    @Override
    public void onFileCreate(File file) {
        logger.info("Filesystem FileCreate event:\n{}", fileToString(file));
        
        SpiedFile spiedFile;
        if (spiedFiles.containsKey(file.getAbsolutePath()))
        {
            spiedFile = spiedFiles.get(file.getAbsolutePath());
            spiedFile.addEvent(file, OperatingSystemFileEvent.CREATED);
        }
        else
        {
            spiedFile = new SpiedFile();
            spiedFile.addEvent(file, OperatingSystemFileEvent.CREATED);
            spiedFiles.put(file.getAbsolutePath(), spiedFile);
        }
        
        SpiedFileEvaluationResult evaluationResult = 
            fileEventInterpreter.evaluateSpiedFile(spiedFile);
        
        //TODO act according to evaluationResult
    }

    @Override
    public void onFileChange(File file) {
        logger.info("Filesystem FileChange event:\n{}", fileToString(file));
    }

    @Override
    public void onFileDelete(File file) {
        logger.info("Filesystem FileDelete event:\n{}", fileToString(file));
    }

    @Override
    public void onStop(FileAlterationObserver fao) {
    }
    
    private static String fileToString(File file)
    {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(" absolutePath: ").append(file.getAbsolutePath());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(" canonicalPath: ").append(file.getCanonicalPath());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(" parent: ").append(file.getParent());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(" path: ").append(file.getPath());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(" length: ").append(file.length());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(" lastModified: ").append(file.lastModified());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(" name: ").append(file.getName());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FileMonitor.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return stringBuilder.toString();
    }
}
