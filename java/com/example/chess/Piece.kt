package com.example.chess


abstract class  Piece(white: Boolean) {
    var isKilled = false
    var isWhite = false
    val piece : String = ""

    abstract fun canMove(
        board: Board?,
        start: Spot?, end: Spot?
    ): Boolean

    init {
        isWhite = white
    }

    abstract fun pieceString() : String
    abstract fun canMoveCheckVerifier(
        board: Board?,
        start: Spot?, end: Spot?
    ): Boolean

}