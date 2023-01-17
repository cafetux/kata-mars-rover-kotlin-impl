package com.rover

import com.rover.infrastructure.parse
import com.rover.model.Instruction
import com.rover.model.Rover
import com.rover.model.moveBackward
import com.rover.model.moveForward

class RoverInstructionsCommand(val rover: Rover, val instructionLine: String) {
    fun run(): Rover {
        val instructions = parse(instructionLine)
        return instructions
            .map { toMovement(it) }
            .fold(rover) { acc, movement -> movement(acc) }
    }

    private fun toMovement(it: Instruction) = when (it) {
        Instruction.LEFT -> { rov: Rover -> Rover(rov.positionX, rov.positionY, rov.orientation.rotateLeft()) }
        Instruction.RIGHT -> { rov: Rover -> Rover(rov.positionX, rov.positionY, rov.orientation.rotateRight()) }
        Instruction.FORWARD -> { rov: Rover -> moveForward(rov) }
        Instruction.BACKWARD -> { rov: Rover -> moveBackward(rov) }
        else -> { rov: Rover -> Rover(rov.positionX, rov.positionY, rov.orientation) }
    }

}