package com.rover.infrastructure

import com.rover.model.command.Instruction.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LexerKtTest {

    @Test
    fun `should parse known instructions`() {
        val actualInstructions = parse("FBLRF")
        assertThat(actualInstructions)
            .containsExactly(FORWARD, BACKWARD, LEFT, RIGHT, FORWARD)
    }

    @Test
    fun `should be case insensitive`() {
        val actualInstructions = parse("FblRf")
        assertThat(actualInstructions)
            .containsExactly(FORWARD, BACKWARD, LEFT, RIGHT, FORWARD)
    }

    @Test
    fun `should know if instructions are invalid`() {
        val actualInstructions = parse("?BTRX")
        assertThat(actualInstructions)
            .containsExactly(UNKNOWN, BACKWARD, UNKNOWN, RIGHT, UNKNOWN)
    }
}