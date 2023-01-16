package com.rover

class RoverInstructionsCommand(val rover: Rover, val instructions: String) {
    fun run(): Rover {
        return rover
    }

}