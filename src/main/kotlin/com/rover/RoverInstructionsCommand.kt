package com.rover

import com.rover.infrastructure.parse
import com.rover.model.*

class RoverInstructionsCommand(val rover: Rover, val instructionLine: String, val obstacles: List<Position>) {
    fun run(): Rover {
        val instructions = parse(instructionLine)
        return instructions
            .map { toMovement(it) }
            .fold(rover) { acc, movement ->
                val nextPosition = movement(acc)
                if(obstacles.contains(Position(nextPosition.positionX, nextPosition.positionY))){
                   return acc
                }
                nextPosition
            }
    }

    private fun toMovement(it: Instruction) = when (it) {
        Instruction.LEFT -> { rov: Rover -> Rover(rov.positionX, rov.positionY, rov.orientation.rotateLeft()) }
        Instruction.RIGHT -> { rov: Rover -> Rover(rov.positionX, rov.positionY, rov.orientation.rotateRight()) }
        Instruction.FORWARD -> { rov: Rover -> moveForward(rov) }
        Instruction.BACKWARD -> { rov: Rover -> moveBackward(rov) }
        else -> { rov: Rover -> Rover(rov.positionX, rov.positionY, rov.orientation) }
    }

}