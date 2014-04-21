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
Utils.info("+", "btPath=${btPath}, " +
        "backupAbsoluteLocation=${backupAbsoluteLocation}, \n" +
        "incomingAbsoluteLocation=${incomingAbsoluteLocation}")

def ant = new AntBuilder()

new File(btPath).eachFile { file ->
    println "move ${file.name} to incoming folder"
    ant.move(file: file, toDir: incomingAbsoluteLocation)
    println "looking for ${file.name} inside backup"
    def counter = 1
    while ( counter < 3) {
        Thread.sleep(secondsToSleep)
        println "cheking the file at ${secondsToSleep/1000 * counter} second"
        counter++
    }
    //need to think out what will happen if file not created within time limit
}
