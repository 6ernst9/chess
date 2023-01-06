package com.example.chess

class Move(player: Player?, start: Spot?, end: Spot?) {
    private var player: Player? = null
    public var start: Spot? = null
    public var end: Spot? = null
    private var pieceMoved: Piece? = null
    public var pieceKilled: Piece? = null

  init{
        this.player = player
        this.start = start
        this.end = end
        pieceMoved = start!!.piece
    }

}