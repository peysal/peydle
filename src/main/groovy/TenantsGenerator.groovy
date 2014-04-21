package main.groovy

import groovy.text.GStringTemplateEngine

def f = new File('d:/learningWorkspace/peydle/src/main/resources/tenants.template')
engine = new GStringTemplateEngine()

def usersClientInfo = [[user: "User 1", clients:["A", "B"]], [user: "User 2", clients:["C", "D"]],
        [user: "User 3", clients:["E", "F"]], [user: "User 4", clients:["G", "H"]], [user: "User 5", clients:["I", "J"]],
        [user: "Explorer 1", clients:["X"]], [user: "Explorer 2", clients:["Y"]], [user: "Explorer 3", clients:["Z"]]]

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
