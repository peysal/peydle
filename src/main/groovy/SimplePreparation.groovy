package main.groovy

import org.apache.commons.vfs2.FileObject
import org.apache.commons.vfs2.FileSystemManager
import org.apache.commons.vfs2.VFS
import org.apache.commons.vfs2.impl.DefaultFileMonitor

/**
 * 1) folder source information available from command line
 * 2) copy that 1 file from source folder
 * 3) put that 1 file into destination
 * 4) monitor backup folder for new zip file
 * 5) suspend current main thread based on limit set in config file
 * 6) if new zip file found, main thread continue
 * 7) if no zip file is found and time limit is up, main thread continue processing
 *
 */

def sourceTestFolder = new File(args[0])
def incomingFolder = new File(args[1])
//def backupFolder = new File(args[2])
//def timeLimitPerTest = args[3]

def zipFile = ~/.*\.txt/
sourceTestFolder.eachFileMatch(zipFile) { file ->
    new AntBuilder().copy(file: file, toFile: backupFolder)
}

//FileSystemManager manager = VFS.getManager();
//FileObject file= manager.resolveFile(backupFolder.absolutePath);
//
//DefaultFileMonitor fm = new DefaultFileMonitor(new BackupListener());
//fm.setRecursive(true);
//fm.addFile(file);
//fm.start()