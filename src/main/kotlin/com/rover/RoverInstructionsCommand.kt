package com.rover

import com.rover.infrastructure.parse
import com.rover.model.*

class RoverInstructionsCommand(private val rover: Rover, private val instructionLine: String, private val map: PlanetMap) {
    fun run(): Rover {
        val instructions = parse(instructionLine)
        return instructions
            .map { toMovement(it) }
            .fold(rover) { acc, movement ->
                val nextPosition = movement(acc)
                if(map.hasObstacle(nextPosition.position)){
                   return acc
                }
                nextPosition
            }
    }

    private fun toMovement(it: Instruction) = when (it) {
        Instruction.LEFT -> { rov: Rover -> Rover(rov.position, rov.orientation.rotateLeft()) }
        Instruction.RIGHT -> { rov: Rover -> Rover(rov.position, rov.orientation.rotateRight()) }
        Instruction.FORWARD -> { rov: Rover -> moveForward(rov) }
        Instruction.BACKWARD -> { rov: Rover -> moveBackward(rov) }
        else -> { rov: Rover -> Rover(rov.position, rov.orientation) }
    }
}