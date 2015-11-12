/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.netorium.watchfoldernew.watchfoldernew;

import java.io.File;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author riesa
 */
public class FileMonitor implements FileAlterationListener{

    private final Logger logger = LoggerFactory.getLogger(FileMonitor.class);
    
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
        logger.info("Filesystem FileCreate event: {}", file.getAbsolutePath());
    }

    @Override
    public void onFileChange(File file) {
        logger.info("Filesystem FileChange event: {}", file.getAbsolutePath());
    }

    @Override
    public void onFileDelete(File file) {
        logger.info("Filesystem FileDelete event: {}", file.getAbsolutePath());
    }

    @Override
    public void onStop(FileAlterationObserver fao) {
    }
}
