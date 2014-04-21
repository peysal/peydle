package main.groovy

import groovy.text.GStringTemplateEngine

def f = new File('d:/learningWorkspace/peydle/src/main/resources/dbMgmtConfig.template')
def engine = new GStringTemplateEngine()

def usersClientInfo = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"]
def explorersClientInfo = ["X", "Y", "Z"]

def templatesOutput = ""

usersClientInfo.each { userClientInfo ->
    def binding = ["clientBusinessName": "Client " + userClientInfo, "client": userClientInfo,
            "clientUser": userClientInfo.toLowerCase(), "clientPassword": userClientInfo.toLowerCase()]
    template = engine.createTemplate(f).make(binding)
    templatesOutput += (template.toString() + "\n\n")
}

explorersClientInfo.each { explorerClientInfo ->
    def binding = ["clientBusinessName": "Client " + explorerClientInfo, "client": explorerClientInfo,
            "clientUser": explorerClientInfo.toLowerCase(), "clientPassword": explorerClientInfo.toLowerCase()]
    template = engine.createTemplate(f).make(binding)
    templatesOutput += (template.toString() + "\n\n")
}

println templatesOutput
