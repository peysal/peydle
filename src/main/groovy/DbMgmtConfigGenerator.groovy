package main.groovy

import groovy.text.GStringTemplateEngine

def f = new File('babun/dbMgmtConfig.template')
def engine = new GStringTemplateEngine()

def usersClientInfo = ["A", "B", "C", "D"]
def explorersClientInfo = ["X", "Y", "Z"]

def templatesOutput = ""

usersClientInfo.each { userClientInfo ->
    def binding = ["clientBusinessName": "Klien " + userClientInfo, "client": userClientInfo,
            "clientUser": userClientInfo.toLowerCase(), "clientPassword": userClientInfo.toLowerCase()]
    template = engine.createTemplate(f).make(binding)
    templatesOutput += (template.toString() + "\n")
}

println templatesOutput
