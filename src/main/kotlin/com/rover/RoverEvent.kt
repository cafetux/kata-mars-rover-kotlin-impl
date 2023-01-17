package com.rover

import com.rover.model.map.Position
import com.rover.model.rover.Rover

sealed interface RoverEvent {
    data class NewRoverPosition(val rover: Rover) : RoverEvent
    data class ObstacleEncountered(val position: Position) : RoverEvent
}