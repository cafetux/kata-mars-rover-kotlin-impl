package com.rover.model.map

class PlanetMap(private val obstacles: List<Position>) {

    fun hasObstacle(position: Position) : Boolean {
        return obstacles.contains(position)
    }
}