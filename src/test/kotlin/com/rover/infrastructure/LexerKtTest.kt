package com.rover.infrastructure

import com.rover.RoverEvent
import com.rover.model.command.Instruction
import com.rover.model.command.Instruction.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LexerKtTest {

    @Test
    fun `should parse known instructions`() {
        val actualInstructions = parse("FBLRF") as com.rover.model.Result<List<Instruction>>

        assertThat(actualInstructions.result)
            .containsExactly(FORWARD, BACKWARD, LEFT, RIGHT, FORWARD)
    }

    @Test
    fun `should be case insensitive`() {
        val actualInstructions = parse("FblRf")  as com.rover.model.Result<List<Instruction>>
        assertThat(actualInstructions.result)
            .containsExactly(FORWARD, BACKWARD, LEFT, RIGHT, FORWARD)
    }

    @Test
    fun `should be an error when encounter an unknown command`() {
        val actualInstructions = parse("?BTRX") as com.rover.model.Error<RoverEvent.InvalidCommand>
        assertThat(actualInstructions.error.command).isEqualTo("?")
    }
}