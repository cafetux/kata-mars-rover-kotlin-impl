package com.rover.model


fun moveForward(rover: Rover): Rover = when (rover.orientation) {
    Orientation.NORTH -> Rover(rover.positionX, rover.positionY + 1, rover.orientation)
    Orientation.SOUTH -> Rover(rover.positionX, rover.positionY - 1, rover.orientation)
    Orientation.WEST -> Rover(rover.positionX - 1, rover.positionY, rover.orientation)
    Orientation.EAST -> Rover(rover.positionX + 1, rover.positionY, rover.orientation)
}

fun moveBackward(rover: Rover): Rover = when (rover.orientation) {
    Orientation.NORTH -> Rover(rover.positionX, rover.positionY - 1, rover.orientation)
    Orientation.SOUTH -> Rover(rover.positionX, rover.positionY + 1, rover.orientation)
    Orientation.WEST -> Rover(rover.positionX + 1, rover.positionY, rover.orientation)
    Orientation.EAST -> Rover(rover.positionX - 1, rover.positionY, rover.orientation)
}
