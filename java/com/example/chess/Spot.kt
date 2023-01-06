package com.example.chess

class Spot(x: Int, y: Int, piece: Piece?) {
    var piece: Piece? = null
    var x = 0
    var y = 0

    init {
        this.piece = piece
        this.x = x
        this.y = y
    }
}