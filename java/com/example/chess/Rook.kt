package com.example.chess

import kotlin.math.abs

class Rook(white : Boolean) : Piece(white) {
    override fun canMove(board: Board?, start: Spot?, end: Spot?): Boolean {

        if( end?.piece?.isWhite == this.isWhite)
        {
            return false
        }
        else{
            val x = Math.abs( start!!.x - end!!.x)
            val y = Math.abs(start.y - end.y)
            if(isClearHorizontally(board, start, end) && isClearVertically(board, start, end ) && isNotCheck(board, start, end)) {
                return x * y == 0
            }
            return false
    }
   }
    override fun canMoveCheckVerifier(board: Board?, start: Spot?, end: Spot?): Boolean {

        if( end?.piece?.isWhite == this.isWhite)
        {
            return false
        }
        else{
            val x = Math.abs( start!!.x - end!!.x)
            val y = Math.abs(start.y - end.y)
            if(isClearHorizontally(board, start, end) && isClearVertically(board, start, end )) {
                return x * y == 0
            }
            return false
        }
    }
    private fun isClearHorizontally(board: Board? , start: Spot?, end: Spot?) :  Boolean{
        val gap = abs(end!!.y - start!!.y) -1
        if(gap == 0) return true
        for ( i in 1.. gap){
            val nextBox = if(end.y>start.y) start.y+i else start.y-i
            if(board!!.getBox(start.x, nextBox ).piece != null){
                return false
            }
        }
        return true
    }
    override fun pieceString(): String {
        return "Rook"
    }
    private fun isClearVertically(board: Board? , start: Spot?, end: Spot?) :  Boolean{
        val gap = abs(end!!.x - start!!.x) -1
        if(gap == 0) return true
        for ( i in 1.. gap){
            val nextBox = if(end.x>start.x) start.x+i else start.x-i
            if(board!!.getBox(nextBox, start.y ).piece != null){
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