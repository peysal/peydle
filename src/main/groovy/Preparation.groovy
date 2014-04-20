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

Utils.print("+", "starting preparation")
def btPath = args[0]
def backupAbsoluteLocation = args[1]
def incomingAbsoluteLocation = args[2]
def secondsToSleep = args[3] as Long
Utils.info("+", "btPath=${btPath}, " +
        "backupAbsoluteLocation=${backupAbsoluteLocation}, \n" +
        "incomingAbsoluteLocation=${incomingAbsoluteLocation}")

def ant = new AntBuilder()

FileSystemManager manager = VFS.getManager();
FileObject backupFileMonitor = manager.resolveFile(backupAbsoluteLocation);
DefaultFileMonitor fm = new DefaultFileMonitor(new BackupListener());
fm.setRecursive(true);
fm.addFile(backupFileMonitor);
fm.start()  //running on daemon thread
new File(btPath).eachFile { file ->
    println "move ${file.name} to incoming folder"
    ant.move(file: file, toDir: incomingAbsoluteLocation)
    println "looking for ${file.name} inside backup"

    for (int i = 1; i < 3; i++) {
        println "sleeps for ${secondsToSleep} second, ${i}"
        sleep(secondsToSleep)
    }
}
fm.stop()
