package main.groovy

import org.apache.commons.vfs2.FileObject
import org.apache.commons.vfs2.FileSystemManager
import org.apache.commons.vfs2.VFS
import org.apache.commons.vfs2.impl.DefaultFileMonitor

/**
 * Prepare the environment for the testing to happen in Scenario phase.
 * 1) it assume the orchestra tool already running successfully
 * 2) it assume the business test case folder convention is setup successfully
 * 3) it assume the file exist
 */
//
//Utils.print("+", "starting preparation")
//
//def businessTestNumber = args[0]
//Utils.info("+", "businessTestNumber=${businessTestNumber}")

FileSystemManager manager = VFS.getManager();
def absoluteLocation = new File('D:/learningWorkspace/peydle/executionClient/backup')
println absoluteLocation.absolutePath
FileObject file= manager.resolveFile(absoluteLocation.absolutePath);

DefaultFileMonitor fm = new DefaultFileMonitor(new BackupListener());
fm.setRecursive(true);
fm.addFile(file);
fm.start()
for (int i = 1; i < 6; i++) {
    println "sleeps for 5 second at step ${i}"
    sleep(5000)
}

