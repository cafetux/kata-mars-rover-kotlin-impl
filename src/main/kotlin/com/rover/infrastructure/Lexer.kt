package com.rover.infrastructure

import com.rover.RoverEvent
import com.rover.model.Either
import com.rover.model.command.Instruction

fun parse(instructions: String): Either<List<Instruction>, RoverEvent> {
    val result =  instructions.toCharArray()
        .map {
            when (it.uppercase()) {
                "F" -> Instruction.FORWARD
                "B" -> Instruction.BACKWARD
                "R" -> Instruction.RIGHT
                "L" -> Instruction.LEFT
                else -> return com.rover.model.Error(RoverEvent.InvalidCommand(it.toString()))
            }
        }.toList()
    return com.rover.model.Result(result)
}