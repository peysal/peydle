package main.groovy

println "argument supplied ${args} from gradle"
def preparationInfo = [:]
boolean isOrchestraStarted = false

//A string can be executed in the standard java way:
def winCommand = "cmd /c dir"   //if running inside win
def command = "ls" // command in linux

Process proc = command.execute()            // Call *execute* on the string
proc.waitFor()                              // Wait for the command to finish

// Obtain status and output
println "return code: ${ proc.exitValue()}"
println "stderr: ${proc.err.text}"
println "stdout: ${proc.in.text}" // *out* from the external program is *in* for groovy

/**
 * how do we monitor current running process execution
 * can we assume exit value to determine whether a process complete its execution?
 * or do we need to see what is the value inside error stream? err
 */

if(isOrchestraStarted) {
    /**
     *  Start scanning top business test folder for which id to be executed
     *  example: businessTest/3.2/preparation/1
     *           businessTest/3.2/preparation/2
     */
     preparationInfo['businessTestNumber'] = 'which business test number'

    /**
     *  execute copy each zip file inside number folder to incoming
     *  will do this until all the zip file inside each number folder is completed
     *
     */

}

