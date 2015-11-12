/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.netorium.watchfoldernew.watchfoldernew;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.LoggerFactory;

/**
 *
 * @author riesa
 */
public class Main {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        
        logger.info("Starting program ...");
        String dirToWatch = "d:\\JOBS-Folder\\WatchfolderNewTest";
        
        IOFileFilter recursive = null;
        //TODO What's about the IOFileFilter
        //recursive = FileFilterUtils.fileFileFilter();
        
        FileAlterationObserver fileAlterationObserver
                        = new FileAlterationObserver(dirToWatch, recursive);
        
        fileAlterationObserver.addListener(new FileMonitor());
        try {
            fileAlterationObserver.initialize();
        } catch (Exception ex) {
            logger.error("FileAlterationObserver.initialize() failed", ex);
            return;
        }
        
        while (true)
        {
            fileAlterationObserver.checkAndNotify();
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}
