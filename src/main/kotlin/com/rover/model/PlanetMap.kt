package com.rover.model

class PlanetMap(private val obstacles: List<Position>) {

    fun hasObstacle(position: Position) : Boolean {
        return obstacles.contains(position)
    }
}