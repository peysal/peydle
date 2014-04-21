package main.groovy

import groovy.text.GStringTemplateEngine

def f = new File('babun/tenants.template')
engine = new GStringTemplateEngine()

def usersClientInfo = [[user: "User 1", clients:["A", "B"]], [user: "User 2", clients:["C", "D"]]]

def templatesOutput = ""

usersClientInfo.each { userClientInfo ->
    userClientInfo.clients.each { client ->
        def binding = ["user": userClientInfo.user, "client": client, "clientUser": client.toLowerCase(),
                "clientPassword": client.toLowerCase()]
        template = engine.createTemplate(f).make(binding)
        templatesOutput += (template.toString() + "\n\n")
    }
}

println templatesOutput
