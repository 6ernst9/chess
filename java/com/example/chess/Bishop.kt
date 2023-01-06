package com.example.chess

import kotlin.math.abs

class Bishop( white : Boolean) : Piece(white) {
    override fun canMove(board: Board?, start: Spot?, end: Spot?): Boolean {

        if( end?.piece?.isWhite == this.isWhite)
        {
            return false
        }
        else{
            var x = Math.abs( start!!.x - end!!.x)
            var y = Math.abs(start.y - end.y)
            if(x==y)
                return isClearDiagonally(board, start, end) && isNotCheck(board, start, end)
        }
        return false

    }
    override fun canMoveCheckVerifier(board: Board?, start: Spot?, end: Spot?): Boolean {
        if( end?.piece?.isWhite == this.isWhite)
        {
            return false
        }
        else{
            var x = Math.abs( start!!.x - end!!.x)
            var y = Math.abs(start.y - end.y)
            if( isClearDiagonally(board, start, end) ){
                return x == y
            }
        }
        return false

    }

    override fun pieceString(): String {
        return "Bishop"
    }
    private fun isClearDiagonally(board : Board?, start : Spot?, end : Spot?) : Boolean{
        val gap = abs(end!!.y - start!!.y) -1
        if(gap == 0) return true
        for ( i in 1.. gap){
            val nextY = if(end.y>start.y) start.y+i else start.y-i
            val nextX = if(end.x>start.x) start.x+i else start.x-i
            if(board!!.getBox(nextX, nextY ).piece != null){
                return false
            }
        }
        return true

    }
    private fun isNotCheck( board: Board?, start: Spot?, end: Spot?) :Boolean{
        val newBoard : Board? = board
        val piece : Piece? = newBoard?.getBox(start!!.x, start.y)?.piece
        newBoard?.getBox(start!!.x, start.y)?.piece = null
        val intermediatePiece = newBoard?.getBox(end!!.x, end.y)?.piece
        newBoard?.getBox(end!!.x, end.y)?.piece = piece

        var kingSpot : Spot? = null
        for( i in 0..7){
            for( j in 0..7){
                if(newBoard?.getBox(i, j)?.piece?.pieceString() == "King" && newBoard.getBox(i, j).piece!!.isWhite == piece!!.isWhite){
                    kingSpot = newBoard.getBox(i, j)
                }
            }
        }
        for( i in 0..7){
            for( j in 0..7){
                if(newBoard!!.getBox(i, j)?.piece!=null && newBoard.getBox(i, j).piece!!.isWhite != kingSpot?.piece!!.isWhite
                    && newBoard.getBox(i, j).piece!!.canMoveCheckVerifier(newBoard, newBoard.getBox(i, j), kingSpot) ){
                    newBoard.getBox(start!!.x, start.y).piece = piece
                    newBoard.getBox(end!!.x, end.y).piece = intermediatePiece
                    return false
                }
            }
        }
        newBoard?.getBox(start!!.x, start.y)?.piece = piece
        newBoard?.getBox(end!!.x, end.y)?.piece = intermediatePiece
        return true
    }
}