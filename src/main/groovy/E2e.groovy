package main.groovy

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
def timeSplitter = 2
Utils.info("+", "btPath=${btPath}, " +
        "backupAbsoluteLocation=${backupAbsoluteLocation}, \n" +
        "incomingAbsoluteLocation=${incomingAbsoluteLocation}")

def ant = new AntBuilder()

new File(btPath).eachFile { file ->
    println "move ${file.name} to incoming folder"
    ant.move(file: file, toDir: incomingAbsoluteLocation)
    println "now looking for ${file.name} inside backup for ${secondsToSleep/1000} seconds"
    long secondsPerProcess = secondsToSleep / timeSplitter
    def secondsLeft = secondsToSleep

    while ( secondsPerProcess <= secondsLeft) {
        Thread.sleep(secondsPerProcess)
        secondsLeft -= secondsPerProcess
        println "Seconds left to check: ${secondsLeft /1000} for file:${file.name}"

    }
}
