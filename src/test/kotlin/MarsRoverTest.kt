import com.rover.model.Orientation
import com.rover.model.Orientation.NORTH
import com.rover.model.Orientation.WEST
import com.rover.model.Rover
import com.rover.RoverInstructionsCommand
import com.rover.model.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MarsRoverTest {


    private lateinit var result: Rover
    private lateinit var rover: Rover
    private var obstacles = listOf<Position>()

    @Test
    fun `should rover moves from initial point to another`() {

        given_a_rover_at_initial_position(10,10, NORTH)

        when_rover_receive_instructions("FFFLFFFLFFFFRB")

        then_rover_is_finally_at_position(8 , 9, WEST)

    }

    @Test
    fun `should rover stop and reports when encounter an obstacle`() {

        given_a_rover_at_initial_position(10,10, NORTH)
        and_obstacle_at_position(10, 12)

        when_rover_receive_instructions("FFFLFFFLFFFFRB")

        then_rover_is_finally_at_position(10 , 11, NORTH)

    }

    private fun and_obstacle_at_position(x: Int, y: Int) {
        obstacles = obstacles + Position(x,y)
    }


    private fun then_rover_is_finally_at_position(expectedX: Int, expectedY: Int, expectedOrientation: Orientation) {
        assertThat(this.result.positionX).`as`("Rover X position").isEqualTo(expectedX)
        assertThat(this.result.positionY).`as`("Rover Y position").isEqualTo(expectedY)
        assertThat(this.result.orientation).`as`("Rover Orientation").isEqualTo(expectedOrientation)
    }

    private fun when_rover_receive_instructions(instructions: String) {
        this.result = RoverInstructionsCommand(this.rover, instructions, obstacles).run()
    }

    private fun given_a_rover_at_initial_position(initialX: Int, initialY: Int, orientation: Orientation) {
        this.rover = Rover(initialX, initialY, orientation)
    }

}