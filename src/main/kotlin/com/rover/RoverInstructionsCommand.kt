package com.rover

import com.rover.infrastructure.parse
import com.rover.model.Result
import com.rover.model.command.Instruction
import com.rover.model.map.PlanetMap
import com.rover.model.rover.Rover
import com.rover.model.rover.moveBackward
import com.rover.model.rover.moveForward

class RoverInstructionsCommand(
    private val rover: Rover,
    private val instructionLine: String,
    private val map: PlanetMap
) {
    fun run(): List<RoverEvent> {
        return when (val instructions = parse(instructionLine)) {
            is com.rover.model.Error -> listOf(instructions.error, RoverEvent.NewRoverPosition(rover))
            is Result -> processInstructions(instructions.result)
        }
    }

    private fun processInstructions(instructions: List<Instruction>): List<RoverEvent> {
        val roverFinal = instructions
            .map { toMovement(it) }
            .fold(rover) { currentRover, movement ->
                val nextRover = movement(currentRover)
                val adjustedNextPosition = map.replaceIfOutside(nextRover.position)
                if (map.hasObstacle(adjustedNextPosition)) {
                    return listOf(
                        RoverEvent.NewRoverPosition(currentRover),
                        RoverEvent.ObstacleEncountered(adjustedNextPosition)
                    )
                }
                Rover(adjustedNextPosition, nextRover.orientation)
            }
        return listOf(RoverEvent.NewRoverPosition(roverFinal))
    }

    private fun toMovement(it: Instruction): ((Rover) -> Rover) {
        return when (it) {
            Instruction.LEFT -> { rov: Rover -> Rover(rov.position, rov.orientation.rotateLeft()) }
            Instruction.RIGHT -> { rov: Rover -> Rover(rov.position, rov.orientation.rotateRight()) }
            Instruction.FORWARD -> { rov: Rover -> moveForward(rov) }
            Instruction.BACKWARD -> { rov: Rover -> moveBackward(rov) }
        }
    }
}