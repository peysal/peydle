import java.nio.file.CopyOption
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.BasicFileAttributes

/**
 * step in refreshTesting
 * delete data files
 * restore data files
 * delete execution client
 * restore execution client
 */

def projectRootPath = "/Users/peysal/development/workspace/peydle"
def dataRuntime = "dataRuntime"
def orchestraPath = "orchestra"
def executionClientPath = "executionClient"

class FileCopyVisitor extends SimpleFileVisitor<Path> {
    private Path target
    private Path source

    FileCopyVisitor(Path target, Path source) {
        this.target = target
        this.source = source
    }

    FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path subFolderPath = this.source.relativize(dir)
        Path newlyFolderPath = Paths.get("$target/$subFolderPath")
        if (Files.notExists(newlyFolderPath) && dir.fileName.toString() != "dataFilesBackup") {
            println "++ Creating sub folder: ${subFolderPath} ++"
            Files.createDirectory(newlyFolderPath)
        }
        FileVisitResult.CONTINUE
    }

    FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
        Path subFilePath = this.source.relativize(file)
        Path newlyFilePath = Paths.get("$target/$subFilePath")
        println "copying File:$file.fileName to $newlyFilePath"
        Files.copy(file, newlyFilePath, StandardCopyOption.REPLACE_EXISTING)
        FileVisitResult.CONTINUE
    }
}

class FileDeleteVisitor extends SimpleFileVisitor<Path> {
    FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        Files.deleteIfExists(file)
        FileVisitResult.CONTINUE
    }

    FileVisitResult postVisitDirectory(Path dir,
                                       IOException exc) {
        println "- Deleting:$dir -"
        Files.deleteIfExists(dir)
        FileVisitResult.CONTINUE
    }
}

def walkTheTalk = {String path, SimpleFileVisitor<Path> visitor ->
    Path folderPath = Paths.get(path)
    Files.walkFileTree(folderPath, visitor)
}

def deleteDataFiles = { Path folder ->
    println "-- Deleting data files at: $folder --"
    def fileDeleteVisitor = new FileDeleteVisitor()
    walkTheTalk("$projectRootPath/$dataRuntime/DataFiles", fileDeleteVisitor)
}

def restoreDataFiles = { def folder ->
    println "++ Restoring data files at: $folder ++"
    Path dataFilesPath = Paths.get("$projectRootPath/$dataRuntime/$folder")
    if(!Files.isDirectory(dataFilesPath)) {
        Files.createDirectory(dataFilesPath)
        println "++ Created DataFiles folder ++"
    }
    def backupFolder = "$projectRootPath/$dataRuntime/dataFilesBackup"
    def fileRestoreVisitor = new FileCopyVisitor(dataFilesPath, Paths.get(backupFolder))
    walkTheTalk("$projectRootPath/$dataRuntime/dataFilesBackup", fileRestoreVisitor)
}

restoreDataFiles("DataFiles")
