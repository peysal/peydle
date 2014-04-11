package main.groovy

/**
 * Prepare the environment for the testing to happen in Scenario phase.
 * 1) it assume the orchestra tool already running successfully
 * 2) it assume the business test case folder convention is setup successfully
 * 3) it assume the file exist
 */

Utils.print("+", "starting preparation")

def businessTestNumber = args[0]
Utils.info("+", "businessTestNumber=${businessTestNumber}")
def businessTestFolder = args[1]
Utils.info("+", "businessTestFolder=${businessTestFolder}")

def btFolder = new File(businessTestFolder)
if (btFolder.exists() && btFolder.isDirectory()) {

}

