package com.guresberatcan.satteliteapp.data.model

data class PositionsBO(
    val list: List<PositionList>
) {
    data class PositionList(
        val id: String,
        val positions: List<Position>
    ) {
        data class Position(
            val posX: Double,
            val posY: Double
        )
    }
}