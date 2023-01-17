package com.rover.model

enum class Orientation {
    NORTH {
        override fun rotateLeft(): Orientation {
            return WEST
        }

        override fun rotateRight(): Orientation {
            return EAST
        }
    }, WEST {
        override fun rotateLeft(): Orientation {
            return SOUTH
        }

        override fun rotateRight(): Orientation {
            return NORTH
        }
    }, EAST {
        override fun rotateLeft(): Orientation {
            return NORTH
        }

        override fun rotateRight(): Orientation {
            return SOUTH
        }
    }, SOUTH {
        override fun rotateLeft(): Orientation {
            return EAST
        }

        override fun rotateRight(): Orientation {
            return WEST
        }
    };

    abstract fun rotateLeft(): Orientation
    abstract fun rotateRight(): Orientation
}