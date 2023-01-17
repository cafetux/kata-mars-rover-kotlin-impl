package com.rover

import com.rover.infrastructure.parse
import com.rover.model.command.Instruction
import com.rover.model.map.PlanetMap
import com.rover.model.rover.Rover
import com.rover.model.rover.moveBackward
import com.rover.model.rover.moveForward

class RoverInstructionsCommand(private val rover: Rover, private val instructionLine: String, private val map: PlanetMap) {
    fun run(): List<RoverEvent> {
        val instructions = parse(instructionLine)
        val roverFinal = instructions
            .map { toMovement(it) }
            .fold(rover) { acc, movement ->
                val nextRover = movement(acc)
                val nextPosition = map.replaceIfOutside(nextRover.position)
                if (map.hasObstacle(nextPosition)) {
                    return listOf(RoverEvent.NewRoverPosition(acc), RoverEvent.ObstacleEncountered(nextPosition))
                }
                Rover(nextPosition, nextRover.orientation)
            }
        return listOf(RoverEvent.NewRoverPosition(roverFinal))
    }

    private fun toMovement(it: Instruction) = when (it) {
        Instruction.LEFT -> { rov: Rover -> Rover(rov.position, rov.orientation.rotateLeft()) }
        Instruction.RIGHT -> { rov: Rover -> Rover(rov.position, rov.orientation.rotateRight()) }
        Instruction.FORWARD -> { rov: Rover -> moveForward(rov) }
        Instruction.BACKWARD -> { rov: Rover -> moveBackward(rov) }
        else -> { rov: Rover -> Rover(rov.position, rov.orientation) }
    }
}