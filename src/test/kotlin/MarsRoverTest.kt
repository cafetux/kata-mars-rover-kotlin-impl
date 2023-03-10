import com.rover.RoverEvent
import com.rover.model.rover.Orientation.NORTH
import com.rover.model.rover.Orientation.WEST
import com.rover.RoverInstructionsCommand
import com.rover.model.map.PlanetMap
import com.rover.model.map.Position
import com.rover.model.rover.Orientation
import com.rover.model.rover.Rover
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MarsRoverTest {

    private lateinit var result: List<RoverEvent>
    private lateinit var rover: Rover
    private var obstacles = listOf<Position>()
    private var edgeLinks : Map<Position, Position> = mapOf()

    @Test
    fun `should rover moves from initial point to another`() {

        given_a_rover_at_initial_position(10, 10, NORTH)

        when_rover_receive_instructions("FFFLFFFLFFFFRB")

        then_rover_is_finally_at_position(8, 9, WEST)
        and_then_no_obstacles_are_reported()
    }

    @Test
    fun `should rover stop and reports when encounter an obstacle`() {

        given_a_rover_at_initial_position(10, 10, NORTH)
        and_given_obstacle_at_position(10, 12)

        when_rover_receive_instructions("FFFLFFFLFFFFRB")

        then_rover_is_finally_at_position(10, 11, NORTH)
        and_then_obstacle_is_reported_to_be_at(10, 12)

    }

    @Test
    fun `should be a spherical planet`() {

        given_a_rover_at_initial_position(10, 10, NORTH)
        and_given_map_link_between(Position(10,15),Position(10,5))

        when_rover_receive_instructions("FFFFFF")

        then_rover_is_finally_at_position(10, 6, NORTH)
    }
    @Test
    fun `should not process instructions when command contains invalid command`() {

        given_a_rover_at_initial_position(10, 10, NORTH)

        when_rover_receive_instructions("FF?FFF")

        then_rover_notify_we_encounter_invalid_command("?")
        then_rover_is_finally_at_position(10, 10, NORTH)
    }

    private fun then_rover_notify_we_encounter_invalid_command(expectedInvalidCommand: String) {
        val actualInvalidCommand = this.result.filterIsInstance<RoverEvent.InvalidCommand>().toList()[0].command
        assertThat(actualInvalidCommand).isEqualTo(expectedInvalidCommand)
    }

    private fun and_given_map_link_between(outOfMapPosition: Position, destination: Position) {
        edgeLinks = edgeLinks + Pair(outOfMapPosition, destination)
    }


    private fun and_then_no_obstacles_are_reported() {
        assertThat(getResultObstacleDetection()).`as`("should not have reported an obstacle").isEmpty()
    }

    private fun getResultObstacleDetection(): List<RoverEvent.ObstacleEncountered> {
        return this.result.filterIsInstance<RoverEvent.ObstacleEncountered>().toList()
    }

    private fun and_then_obstacle_is_reported_to_be_at(x: Int, y: Int) {
        val obstacles = getResultObstacleDetection()
        assertThat(obstacles).`as`("should have reported obstacle").hasSize(1)
        assertThat(obstacles[0].position).isEqualTo(Position(x, y))
    }

    private fun and_given_obstacle_at_position(x: Int, y: Int) {
        obstacles = obstacles + Position(x, y)
    }


    private fun then_rover_is_finally_at_position(expectedX: Int, expectedY: Int, expectedOrientation: Orientation) {
        assertThat(getNewRoverPosition()).hasSize(1)
        val newRover = getNewRoverPosition()[0].rover
        assertThat(newRover.position).`as`("Rover position should be").isEqualTo(Position(expectedX, expectedY))
        assertThat(newRover.orientation).`as`("Rover Orientation should be").isEqualTo(expectedOrientation)
    }

    private fun getNewRoverPosition() = this.result.filterIsInstance<RoverEvent.NewRoverPosition>().toList()

    private fun when_rover_receive_instructions(instructions: String) {
        this.result = RoverInstructionsCommand(this.rover, instructions, PlanetMap(obstacles, edgeLinks)).run()
    }

    private fun given_a_rover_at_initial_position(initialX: Int, initialY: Int, orientation: Orientation) {
        this.rover = Rover(Position(initialX, initialY), orientation)
    }

}