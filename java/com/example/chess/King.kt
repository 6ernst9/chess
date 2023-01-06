package com.example.chess

class King( white : Boolean): Piece(white) {
    override fun canMove(board: Board?, start: Spot?, end: Spot?): Boolean {
        if( end?.piece?.isWhite == this.isWhite)
        {
            return false
        }
        else{
            var x = Math.abs( start!!.x - end!!.x)
            var y = Math.abs(start.y - end.y)
            return x + y == 1 && isNotCheck(board, start, end) || x == y && y== 1 && isNotCheck(board, start, end)
        }
    }
    override fun canMoveCheckVerifier(board: Board?, start: Spot?, end: Spot?): Boolean {
        if( end?.piece?.isWhite == this.isWhite)
        {
            return false
        }
        else{
            var x = Math.abs( start!!.x - end!!.x)
            var y = Math.abs(start.y - end.y)
            return x + y == 1 || x==y && y==1
        }
    }
    override fun pieceString() : String{
        return "King"
    }
    private fun isNotCheck( board: Board?, start: Spot?, end: Spot?) :Boolean{
        val newBoard : Board? = board
        val piece : Piece? = newBoard?.getBox(start!!.x, start.y)?.piece
        newBoard?.getBox(start!!.x, start.y)?.piece = null
        val intermediatePiece = newBoard?.getBox(end!!.x, end.y)?.piece
        newBoard?.getBox(end!!.x, end.y)?.piece = piece

        for( i in 0..7){
            for( j in 0..7){
                if(newBoard!!.getBox(i, j).piece!=null && newBoard.getBox(i, j).piece!!.isWhite != end?.piece!!.isWhite && newBoard.getBox(i, j).piece!!.canMoveCheckVerifier(newBoard, newBoard.getBox(i, j), end)){
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