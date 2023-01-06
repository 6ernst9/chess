package com.example.chess

public class Board {
    var boxes = Array(8){Array(8){Spot( 0, 0, null)} }

    init {
        this.resetBoard()
    }
    fun getBox (x : Int, y : Int) : Spot{
        return boxes[x][y]
    }

    fun resetBoard(){
        boxes[0][0] = Spot(0, 0, Rook(true))
        boxes[0][1] = Spot(0, 1, Knight(true))
        boxes[0][2] = Spot(0, 2, Bishop(true))
        boxes[0][3] = Spot(0, 3, Queen(true))
        boxes[0][4] = Spot(0, 4, King(true))
        boxes[0][5] = Spot(0, 5, Bishop(true))
        boxes[0][6] = Spot(0, 6, Knight(true))
        boxes[0][7] = Spot(0, 7, Rook(true))
        boxes[1][0] = Spot(1, 0, Pawn(true))
        boxes[1][1] = Spot(1, 1, Pawn(true))
        boxes[1][2] = Spot(1, 2, Pawn(true))
        boxes[1][3] = Spot(1, 3, Pawn(true))
        boxes[1][4] = Spot(1, 4, Pawn(true))
        boxes[1][5] = Spot(1, 5, Pawn(true))
        boxes[1][6] = Spot(1, 6, Pawn(true))
        boxes[1][7] = Spot(1, 7, Pawn(true))
        boxes[6][0] = Spot(6, 0, Pawn(false))
        boxes[6][1] = Spot(6, 1, Pawn(false))
        boxes[6][2] = Spot(6, 2, Pawn(false))
        boxes[6][3] = Spot(6, 3, Pawn(false))
        boxes[6][4] = Spot(6, 4, Pawn(false))
        boxes[6][5] = Spot(6, 5, Pawn(false))
        boxes[6][6] = Spot(6, 6, Pawn(false))
        boxes[6][7] = Spot(6, 7, Pawn(false))
        boxes[7][0] = Spot(7, 0, Rook(false))
        boxes[7][1] = Spot(7, 1, Knight(false))
        boxes[7][2] = Spot(7, 2, Bishop(false))
        boxes[7][3] = Spot(7, 3, Queen(false))
        boxes[7][4] = Spot(7, 4, King(false))
        boxes[7][5] = Spot(7, 5, Bishop(false))
        boxes[7][6] = Spot(7, 6, Knight(false))
        boxes[7][7] = Spot(7, 7, Rook(false))
        for ( i in 2..5){
            for( j in 0 .. 7){
                boxes[i][j] =  Spot(i, j, null)
            }
        }


    }
}