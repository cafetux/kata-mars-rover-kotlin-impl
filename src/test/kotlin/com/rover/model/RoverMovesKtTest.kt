package com.rover.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RoverMovesKtTest {

    lateinit var actualRover:Rover

    @Test
    fun `should move positive on Y when move forward oriented NORTH`(){
        val rover = Rover(0, 0, Orientation.NORTH)

        `when`(moveForward(rover))

        then_rover_is_on(0, 1)
    }

    @Test
    fun `should move negative on Y when move forward oriented SOUTH`(){
        val rover = Rover(0, 0, Orientation.SOUTH)

        `when`(moveForward(rover))

        then_rover_is_on(0, -1)
    }

    @Test
    fun `should move negative on X when move forward oriented WEST`(){
        val rover = Rover(0, 0, Orientation.WEST)

        `when`(moveForward(rover))

        then_rover_is_on(-1, 0)
    }

    @Test
    fun `should move positive on X when move forward oriented EAST`(){
        val rover = Rover(0, 0, Orientation.EAST)

        `when`(moveForward(rover))

        then_rover_is_on(1, 0)
    }

    @Test
    fun `should move negative on X when move backward oriented EAST`(){
        val rover = Rover(0, 0, Orientation.EAST)

        `when`(moveBackward(rover))

        then_rover_is_on(-1, 0)
    }

    @Test
    fun `should move positive on X when move backward oriented WEST`(){
        val rover = Rover(0, 0, Orientation.WEST)

        `when`(moveBackward(rover))

        then_rover_is_on(1, 0)
    }

    @Test
    fun `should move negative on Y when move backward oriented NORTH`(){
        val rover = Rover(0, 0, Orientation.NORTH)

        `when`(moveBackward(rover))

        then_rover_is_on(0, -1)
    }

    @Test
    fun `should move positive on Y when move backward oriented SOUTH`(){
        val rover = Rover(0, 0, Orientation.SOUTH)

        `when`(moveBackward(rover))

        then_rover_is_on(0, 1)
    }


    private fun then_rover_is_on(expectedX: Int, expectedY: Int) {
        assertThat(this.actualRover.positionX).isEqualTo(expectedX)
        assertThat(this.actualRover.positionY).isEqualTo(expectedY)
    }

    private fun `when`(rover: Rover) {
        this.actualRover = rover
    }
}