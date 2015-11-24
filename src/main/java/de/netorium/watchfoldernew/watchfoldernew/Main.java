package de.netorium.watchfoldernew.watchfoldernew;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.LoggerFactory;

/**
 *
 * @author riesa
 */
public class Main {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        
        logger.info("Starting program ...");

        String dirToWatch = "";
        if (SystemUtils.IS_OS_WINDOWS)
            dirToWatch = "d:\\JOBS-Folder\\WatchfolderNewTest";
        else if (SystemUtils.IS_OS_MAC_OSX)
            dirToWatch = "/Users/andreasries/tmp/WatchfolderNewTest";
                
        IOFileFilter filter  = FileFilterUtils.and(FileFilterUtils.fileFileFilter(), HiddenFileFilter.VISIBLE);
        
        FileAlterationObserver fileAlterationObserver
                        = new FileAlterationObserver(dirToWatch, filter);
        
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
