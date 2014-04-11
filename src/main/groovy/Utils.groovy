package main.groovy

/**
 *
 */
class Utils {

    static print(def character, def message) {
        println "${character.multiply(4)} ${message} ${character.multiply(4)}"
    }

    static info(def character, def message) {
        println "${character.multiply(3)} ${message} ${character.multiply(3)}"
    }
}
