package com.example.chess

class Pawn( white : Boolean) : Piece(white) {
    override fun canMove(board: Board?, start: Spot?, end: Spot?): Boolean {
        if( end?.piece?.isWhite == this.isWhite)
        {
            return false
        }
        else{
            var x = Math.abs( start!!.x - end!!.x)
            var y = Math.abs(start.y - end.y)
            val direction : Boolean = end.x - start.x >0
            if( x ==1 && y == 0 && end?.piece==null && direction == this.isWhite&& isNotCheck(board, start, end))
                return true
            if(x==2 && y==0 ){
                if(start.x == 1 && end.x == 3 && board!!.getBox(2, end.y).piece==null && isNotCheck(board, start, end) && direction == this.isWhite
                    || start.x == 6 && end.x == 4 && board!!.getBox(5, end.y).piece==null && isNotCheck(board, start, end) && direction == this.isWhite )
                    return true
            }
            return end.piece!= null && end.piece?.isWhite == !this.isWhite && x == 1 && y == 1 && isNotCheck(board, start, end) && direction == this.isWhite
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
            val direction : Boolean = end.x - start.x >0
            if( x ==1 && y == 0 && end?.piece==null && direction == this.isWhite)
                return true
            if(x==2 && y==0 ){
                if(start.x == 1 && end.x == 3 && board!!.getBox(2, end.y).piece==null && direction == this.isWhite || start.x == 6 && end.x == 4
                    && board!!.getBox(5, end.y).piece==null && direction == this.isWhite )
                    return true
            }
            return end.piece!= null && end.piece?.isWhite == !this.isWhite && x == 1 && y == 1 && direction == this.isWhite
        }
    }
    override fun pieceString(): String {
        return "Pawn"
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
                if(newBoard!!.getBox(i, j).piece!=null && newBoard.getBox(i, j).piece!!.canMoveCheckVerifier(newBoard, newBoard.getBox(i, j), kingSpot) && newBoard.getBox(i, j).piece!!.isWhite != kingSpot?.piece!!.isWhite){
                    newBoard.getBox(start!!.x, start.y).piece = piece
                    newBoard?.getBox(end!!.x, end.y)?.piece = intermediatePiece
                    return false
                }
            }
        }
        newBoard?.getBox(start!!.x, start.y)?.piece = piece
        newBoard?.getBox(end!!.x, end.y)?.piece = intermediatePiece
        return true
    }
}