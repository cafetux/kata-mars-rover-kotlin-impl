package com.rover.model.map

class PlanetMap(private val obstacles: List<Position>, val edgeLinks: Map<Position, Position>) {

    fun hasObstacle(position: Position) : Boolean {
        return obstacles.contains(position)
    }

    fun replaceIfOutside(position: Position):Position {
        return edgeLinks.getOrDefault(position, position)
    }
}