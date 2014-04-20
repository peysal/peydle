package main.groovy

import org.apache.commons.vfs2.FileChangeEvent
import org.apache.commons.vfs2.FileListener

/**
 * Backup listener.
 */
class BackupListener implements FileListener {
    @Override
    void fileCreated(FileChangeEvent event) throws Exception {
        println("file created: ${event.file.name}")
    }

    @Override
    void fileDeleted(FileChangeEvent event) throws Exception {
        println "file deleted"
    }

    @Override
    void fileChanged(FileChangeEvent event) throws Exception {
        println "file changed"
    }
}
