package com.rover.infrastructure

import com.rover.model.Instruction

fun parse(instructions: String): List<Instruction> {
    return instructions.toCharArray()
        .map {
            when (it.uppercase()) {
                "F" -> Instruction.FORWARD
                "B" -> Instruction.BACKWARD
                "R" -> Instruction.RIGHT
                "L" -> Instruction.LEFT
                else -> Instruction.UNKNOWN
            }
        }.toList()
}