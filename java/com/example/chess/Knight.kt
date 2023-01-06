package com.example.chess

class Knight(white: Boolean) : Piece(white) {
    override fun canMove(
        board: Board?, start: Spot?,
        end: Spot?
    ): Boolean {
        if (end!!.piece?.isWhite == isWhite) {
            return false
        }
        val x = Math.abs(start!!.x - end.x)
        val y = Math.abs(start.y - end.y)
        return x * y == 2 && isNotCheck(board, start, end)
    }
    override fun canMoveCheckVerifier(
        board: Board?, start: Spot?,
        end: Spot?
    ): Boolean {
        if (end!!.piece?.isWhite == isWhite) {
            return false
        }
        val x = Math.abs(start!!.x - end.x)
        val y = Math.abs(start.y - end.y)
        return x * y == 2
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
    override fun pieceString(): String {
        return "Knight"
    }
}