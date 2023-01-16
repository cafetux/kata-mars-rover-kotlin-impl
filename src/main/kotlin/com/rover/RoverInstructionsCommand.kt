package com.rover

import com.rover.infrastructure.parse
import com.rover.model.Instruction
import com.rover.model.Rover

class RoverInstructionsCommand(val rover: Rover, val instructionLine: String) {
    fun run(): Rover {
        val instructions = parse(instructionLine)
         return rover
    }

}