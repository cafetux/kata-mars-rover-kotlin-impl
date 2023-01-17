package com.rover.model

sealed interface RoverEvent {
    data class NewRoverPosition(val rover: Rover) : RoverEvent
    data class ObstacleEncountered(val position: Position) : RoverEvent
}