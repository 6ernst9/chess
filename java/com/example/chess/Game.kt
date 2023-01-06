package com.example.chess

class Game( p1 : Player, p2: Player) {
    private var player1 =  Player(true)
    private var player2 = Player (false)
    private var board = Board()
    private var status : GameStatus = GameStatus.ACTIVE
    var currentTurn = Player( true )
    var moveList = ArrayList<String>()
   init{
        player1 = p1
        player2 = p2
        board.resetBoard()
        if (p1.isWhiteSide) {
            currentTurn = p1
        } else {
            currentTurn = p2
        }
        moveList.clear()
    }
    fun isEnd(): Boolean {
        return getStatus() != GameStatus.ACTIVE
    }
    fun getStatus(): GameStatus{
        return status
    }
    private fun addMoveToMoveList (move: Move){
        val position: String = when(move.end!!.y){
            0-> "A${move.end!!.x}"
            1-> "B${move.end!!.x}"
            2-> "C${move.end!!.x}"
            3-> "D${move.end!!.x}"
            4-> "E${move.end!!.x}"
            5-> "F${move.end!!.x}"
            6-> "G${move.end!!.x}"
            7-> "H${move.end!!.x}"
            else-> "00"
        }
        val piece: String = move.end!!.piece!!.pieceString()
        val color: String = if(move.end!!.piece!!.isWhite) "White" else "Black"
        moveList.add("$color $piece to $position")

    }
    fun getMoves() : ArrayList<String>{
        return this.moveList
    }
    fun setStatus(status: GameStatus) {
        this.status = status
    }
    fun getTurn(): Player{
        return this.currentTurn
    }
    fun getTurnString() : String{
        if(currentTurn == player1){
            return "Player1"
        }
        return "Player2"
    }
    fun getBoard () :Board{
        return this.board
    }
    fun playerMove(player: Player, startX: Int, startY: Int, endX: Int, endY: Int): Boolean {
        val startBox: Spot = board.getBox(startX, startY)
        val endBox: Spot = board.getBox(endX, endY)
        println("${board.getBox(startX, startY).piece} piece")
        val move = Move(player, startBox, endBox)
        return makeMove(move, player)
    }
    private fun makeMove(move: Move, player: Player): Boolean {
        val sourcePiece: Piece = move.start?.piece ?: return false

        if (player != currentTurn) {
            println("notTurn")
            return false

        }
        if (sourcePiece.isWhite != player.isWhiteSide) {
            println("notSide")
            return false
        }
        if (!sourcePiece.canMove(board, move.start, move.end)) {
            println("cantMove")
            return false
        }
        val destPiece: Piece? = move.end?.piece
        if (destPiece != null) {
            destPiece.isKilled = true
            move.pieceKilled= destPiece
        }
        // move piece from the stat box to end box
        move.end?.piece =move.start?.piece
        move.start?.piece = null

        if(move.end!!.x == 0 && move.end!!.piece!!.pieceString() == "Pawn"||move.end!!.x == 7 && move.end!!.piece!!.pieceString() == "Pawn"){
            move.end!!.piece = Queen(move.end!!.piece!!.isWhite)
        }

        addMoveToMoveList(move)

        if (isCheckMate(move.end, player)) {
            if (player.isWhiteSide) {
                setStatus(GameStatus.WHITE_WIN)
            } else {
                setStatus(GameStatus.BLACK_WIN)
            }
        }
        if (isStealMate(move.end, player)) {
            setStatus(GameStatus.STALEMATE)
        }

        // set the current turn to the other player
        if (currentTurn == player1) {
            this.currentTurn = player2
        } else {
            this.currentTurn = player1
        }
        return true
    }
    private fun checkVerifier( end: Spot?, player: Player) : Boolean{

        var kingSpot = Spot( 0, 0, null)
        for( i in 0..7){
            for (j in 0..7){
                if(board.getBox(i, j).piece != null && board.getBox(i, j).piece!!.pieceString() == "King" && board.getBox(i, j).piece!!.isWhite != player.isWhiteSide)
                {
                    kingSpot = board.getBox(i, j)
                }
            }
        }
        if(end?.piece!!.canMove(board, end, kingSpot)){
            return true
        }
        return false
    }
    private fun isCheckMate(end : Spot?, player: Player) : Boolean{
        if(!checkVerifier(end, player)){
            return false
        }
        for(i in 0..7){
            for(j in 0..7){
                if(board.getBox(i, j).piece != null && board.getBox(i, j).piece!!.isWhite != player.isWhiteSide )
                {
                    for(m in 0..7){
                        for( n in 0..7){
                            if(board.getBox(i, j).piece!!.canMove(board, board.getBox(i, j), board.getBox(m, n))){
                                println("$i $j $m $n")
                                return false
                            }
                        }
                    }
                }

            }
        }
        return true
    }
    private fun isStealMate(end : Spot?, player: Player) : Boolean{
        if(checkVerifier(end, player)){
            return false
        }
        for(i in 0..7){
            for(j in 0..7){
                if(board.getBox(i, j).piece != null && board.getBox(i, j).piece!!.isWhite != player.isWhiteSide )
                {
                    for(m in 0..7){
                        for( n in 0..7){
                            if(board.getBox(i, j).piece!!.canMove(board, board.getBox(i, j), board.getBox(m, n))){
                                println("$i $j $m $n")
                                return false
                            }
                        }
                    }
                }

            }
        }
        return true
    }


}