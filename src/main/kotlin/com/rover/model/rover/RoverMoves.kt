package com.rover.model.rover

import com.rover.model.map.Position


fun moveForward(rover: Rover): Rover = when (rover.orientation) {
    Orientation.NORTH -> Rover(Position(rover.position.x, rover.position.y+1), rover.orientation)
    Orientation.SOUTH -> Rover(Position(rover.position.x, rover.position.y - 1), rover.orientation)
    Orientation.WEST -> Rover(Position(rover.position.x - 1, rover.position.y), rover.orientation)
    Orientation.EAST -> Rover(Position(rover.position.x + 1, rover.position.y), rover.orientation)
}

fun moveBackward(rover: Rover): Rover = when (rover.orientation) {
    Orientation.NORTH -> Rover(Position(rover.position.x, rover.position.y - 1), rover.orientation)
    Orientation.SOUTH -> Rover(Position(rover.position.x, rover.position.y + 1), rover.orientation)
    Orientation.WEST -> Rover(Position(rover.position.x + 1, rover.position.y), rover.orientation)
    Orientation.EAST -> Rover(Position(rover.position.x - 1, rover.position.y), rover.orientation)
}
