package com.example.chess

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import java.util.*
import java.util.concurrent.TimeUnit

var gameName : String = "null"
class GameActivity : AppCompatActivity() {
    var timerCounter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameName = intent.getStringExtra("gameName").toString()
        val gameType = findViewById<TextView>(R.id.gameType)
        gameType.text = gameName
        simpleGame(gameName)
        val exitGame = findViewById<ImageView>(R.id.exitGame)
        exitGame.setOnClickListener{
            exit()
        }
    }
    override fun onBackPressed() {
        exit()
    }
    private fun exit(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.exitgame_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val exitBtn : AppCompatButton = dialog.findViewById<AppCompatButton>(R.id.exitExit)
        val cancelBtn : AppCompatButton = dialog.findViewById<AppCompatButton>(R.id.exitCancel)
        exitBtn.setOnClickListener{
            this.finish()
        }
        cancelBtn.setOnClickListener{
            dialog.cancel()
        }
        dialog.show()
    }
    private fun simpleGame(gameName : String){
        val player1  = Player( true )
        val player2 = Player ( false )

        var startX : Int = 0
        var startY : Int = 0
        var endX : Int = 0
        var endY : Int = 0
        val game = Game( player1, player2)
        val board = game.getBoard()
        var clickCounter = 0
        startTimer(timerCounter, game, gameName)
        val moveList = findViewById<CardView>(R.id.cardviewMoves)
        val cardviewMoves = findViewById<CardView>(R.id.cardviewListOfMoves)
        val killedList = findViewById<CardView>(R.id.cardviewKilled)
        val closeCardviewMoves = findViewById<ImageView>(R.id.moveListClose)
        val listOfMoves = findViewById<TextView>(R.id.listOfMoves)
        val resignBtn = findViewById<CardView>(R.id.cardviewResign)
        moveList.setOnClickListener{
            cardviewMoves.isVisible = true
            for(i in game.getMoves()){
                if(i == game.getMoves().get(0))
                    listOfMoves.text = "${i}"
                else
                    listOfMoves.text = "${listOfMoves.text}\n${i}"
            }
        }
        resignBtn.setOnClickListener{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.resigngame_dialog)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(true)
            val resignBtn : AppCompatButton = dialog.findViewById<AppCompatButton>(R.id.resignResign)
            val cancelBtn : AppCompatButton = dialog.findViewById<AppCompatButton>(R.id.resignCancel)
            resignBtn.setOnClickListener{
                if(game.getTurn().isWhiteSide){
                    game.setStatus(GameStatus.BLACK_WIN)
                }
                else{
                    game.setStatus(GameStatus.WHITE_WIN)
                }
                openWinDialog(game)
            }
            cancelBtn.setOnClickListener{
                dialog.cancel()
            }
            dialog.show()

        }
        closeCardviewMoves.setOnClickListener{
            cardviewMoves.isVisible = false
        }
        val row0col0 = findViewById<ConstraintLayout>(R.id.row0col0)
        row0col0.setOnClickListener{
            if (clickCounter == 0){
                startX = 0
                startY = 0
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 0
                endY = 0
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row0col1 = findViewById<ConstraintLayout>(R.id.row0col1)
        row0col1.setOnClickListener{
            if (clickCounter == 0){
                startX = 0
                startY = 1
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 0
                endY = 1
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row0col2 = findViewById<ConstraintLayout>(R.id.row0col2)
        row0col2.setOnClickListener{
            if (clickCounter == 0){
                startX = 0
                startY = 2
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 0
                endY = 2
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row0col3 = findViewById<ConstraintLayout>(R.id.row0col3)
        row0col3.setOnClickListener{
            if (clickCounter == 0){
                startX = 0
                startY = 3
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 0
                endY = 3
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row0col4 = findViewById<ConstraintLayout>(R.id.row0col4)
        row0col4.setOnClickListener{
            if (clickCounter == 0){
                startX = 0
                startY = 4
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 0
                endY = 4
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row0col5 = findViewById<ConstraintLayout>(R.id.row0col5)
        row0col5.setOnClickListener{
            if (clickCounter == 0){
                startX = 0
                startY = 5
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 0
                endY = 5
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row0col6 = findViewById<ConstraintLayout>(R.id.row0col6)
        row0col6.setOnClickListener{
            if (clickCounter == 0){
                startX = 0
                startY = 6
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 0
                endY = 6
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row0col7 = findViewById<ConstraintLayout>(R.id.row0col7)
        row0col7.setOnClickListener{
            if (clickCounter == 0){
                startX = 0
                startY = 7
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 0
                endY = 7
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row1col0 = findViewById<ConstraintLayout>(R.id.row1col0)
        row1col0.setOnClickListener{
            if (clickCounter == 0){
                startX = 1
                startY = 0
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 1
                endY = 0
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row1col1 = findViewById<ConstraintLayout>(R.id.row1col1)
        row1col1.setOnClickListener{
            if (clickCounter == 0){
                startX = 1
                startY = 1
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 1
                endY = 1
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row1col2 = findViewById<ConstraintLayout>(R.id.row1col2)
        row1col2.setOnClickListener{
            if (clickCounter == 0){
                startX = 1
                startY = 2
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 1
                endY = 2
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row1col3 = findViewById<ConstraintLayout>(R.id.row1col3)
        row1col3.setOnClickListener{
            if (clickCounter == 0){
                startX = 1
                startY = 3
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 1
                endY = 3
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row1col4 = findViewById<ConstraintLayout>(R.id.row1col4)
        row1col4.setOnClickListener{
            if (clickCounter == 0){
                startX = 1
                startY = 4
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 1
                endY = 4
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row1col5 = findViewById<ConstraintLayout>(R.id.row1col5)
        row1col5.setOnClickListener{
            if (clickCounter == 0){
                startX = 1
                startY = 5
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 1
                endY = 5

                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row1col6 = findViewById<ConstraintLayout>(R.id.row1col6)
        row1col6.setOnClickListener{
            if (clickCounter == 0){
                startX = 1
                startY = 6
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 1
                endY = 6
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row1col7 = findViewById<ConstraintLayout>(R.id.row1col7)
        row1col7.setOnClickListener{
            if (clickCounter == 0){
                startX = 1
                startY = 7
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 1
                endY = 7

                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row2col0 = findViewById<ConstraintLayout>(R.id.row2col0)
        row2col0.setOnClickListener{
            if (clickCounter == 0){
                startX = 2
                startY = 0
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 2
                endY = 0
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row2col1 = findViewById<ConstraintLayout>(R.id.row2col1)
        row2col1.setOnClickListener{
            if (clickCounter == 0){
                startX = 2
                startY = 1
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 2
                endY = 1
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row2col2 = findViewById<ConstraintLayout>(R.id.row2col2)
        row2col2.setOnClickListener{
            if (clickCounter == 0){
                startX = 2
                startY = 2
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 2
                endY = 2
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row2col3 = findViewById<ConstraintLayout>(R.id.row2col3)
        row2col3.setOnClickListener{
            if (clickCounter == 0){
            startX = 2
            startY = 3
            clickCounter ++
            showPosibilities(board, startX, startY)
        }
        else{
            endX = 2
            endY = 3
            if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
            {
                updateBoard(board, startX, startY, endX, endY)
                startTimer(timerCounter, game, gameName)
            }
            clickCounter = 0
                closePosibilities(board)
        }
        }
        val row2col4 = findViewById<ConstraintLayout>(R.id.row2col4)
        row2col4.setOnClickListener{
            if (clickCounter == 0){
                startX = 2
                startY = 4
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 2
                endY = 4
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row2col5 = findViewById<ConstraintLayout>(R.id.row2col5)
        row2col5.setOnClickListener{
            if (clickCounter == 0){
                startX = 2
                startY = 5
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 2
                endY = 5
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row2col6 = findViewById<ConstraintLayout>(R.id.row2col6)
        row2col6.setOnClickListener{
            if (clickCounter == 0){
                startX = 2
                startY = 6
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 2
                endY = 6
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row2col7 = findViewById<ConstraintLayout>(R.id.row2col7)
        row2col7.setOnClickListener{
            if (clickCounter == 0){
                startX = 2
                startY = 7
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 2
                endY = 7
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row3col0 = findViewById<ConstraintLayout>(R.id.row3col0)
        row3col0.setOnClickListener{
            if (clickCounter == 0){
                startX = 3
                startY = 0
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 3
                endY = 0
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row3col1 = findViewById<ConstraintLayout>(R.id.row3col1)
        row3col1.setOnClickListener{
            if (clickCounter == 0){
                startX = 3
                startY = 1
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 3
                endY = 1
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row3col2 = findViewById<ConstraintLayout>(R.id.row3col2)
        row3col2.setOnClickListener{
            if (clickCounter == 0){
                startX = 3
                startY = 2
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 3
                endY = 2
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row3col3 = findViewById<ConstraintLayout>(R.id.row3col3)
        row3col3.setOnClickListener{
            if (clickCounter == 0){
                startX = 3
                startY = 3
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 3
                endY = 3
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)

                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row3col4 = findViewById<ConstraintLayout>(R.id.row3col4)
        row3col4.setOnClickListener{
            if (clickCounter == 0){
                startX = 3
                startY = 4
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 3
                endY = 4
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row3col5 = findViewById<ConstraintLayout>(R.id.row3col5)
        row3col5.setOnClickListener{
            if (clickCounter == 0){
                startX = 3
                startY = 5
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 3
                endY = 5
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row3col6 = findViewById<ConstraintLayout>(R.id.row3col6)
        row3col6.setOnClickListener{
            if (clickCounter == 0){
                startX = 3
                startY = 6
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 3
                endY = 6
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row3col7 = findViewById<ConstraintLayout>(R.id.row3col7)
        row3col7.setOnClickListener{
            if (clickCounter == 0){
                startX = 3
                startY = 7
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 3
                endY = 7
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row4col0 = findViewById<ConstraintLayout>(R.id.row4col0)
        row4col0.setOnClickListener{
            if (clickCounter == 0){
                startX = 4
                startY = 0
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 4
                endY = 0
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row4col1 = findViewById<ConstraintLayout>(R.id.row4col1)
        row4col1.setOnClickListener{
            if (clickCounter == 0){
                startX = 4
                startY = 1
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 4
                endY = 1
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row4col2 = findViewById<ConstraintLayout>(R.id.row4col2)
        row4col2.setOnClickListener{
            if (clickCounter == 0){
                startX = 4
                startY = 2
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 4
                endY = 2
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row4col3 = findViewById<ConstraintLayout>(R.id.row4col3)
        row4col3.setOnClickListener{
            if (clickCounter == 0){
                startX = 4
                startY = 3
                clickCounter ++
                showPosibilities(board, startX, startY)

            }
            else{
                endX = 4
                endY = 3
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row4col4 = findViewById<ConstraintLayout>(R.id.row4col4)
        row4col4.setOnClickListener{
            if (clickCounter == 0){
                startX = 4
                startY = 4
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 4
                endY = 4
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard( board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row4col5 = findViewById<ConstraintLayout>(R.id.row4col5)
        row4col5.setOnClickListener{
            if (clickCounter == 0){
                startX = 4
                startY = 5
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 4
                endY = 5
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row4col6 = findViewById<ConstraintLayout>(R.id.row4col6)
        row4col6.setOnClickListener{
            if (clickCounter == 0){
                startX = 4
                startY = 6
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
            endX = 4
            endY = 6
            if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
            {
                updateBoard(board, startX, startY, endX, endY)
                startTimer(timerCounter, game, gameName)
            }
            clickCounter = 0
            closePosibilities(board)

            }
        }
        val row4col7 = findViewById<ConstraintLayout>(R.id.row4col7)
        row4col7.setOnClickListener{
            if (clickCounter == 0){
                startX = 4
                startY = 7
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 4
                endY = 7
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row5col0 = findViewById<ConstraintLayout>(R.id.row5col0)
        row5col0.setOnClickListener{
            if (clickCounter == 0){
                startX = 5
                startY = 0
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 5
                endY = 0
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row5col1 = findViewById<ConstraintLayout>(R.id.row5col1)
        row5col1.setOnClickListener{
            if (clickCounter == 0){
                startX = 5
                startY = 1
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX =5
                endY = 1
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row5col2 = findViewById<ConstraintLayout>(R.id.row5col2)
        row5col2.setOnClickListener{
            if (clickCounter == 0){
                startX = 5
                startY = 2
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 5
                endY = 2
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row5col3 = findViewById<ConstraintLayout>(R.id.row5col3)
        row5col3.setOnClickListener{
            if (clickCounter == 0){
                startX = 5
                startY = 3
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 5
                endY = 3
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row5col4 = findViewById<ConstraintLayout>(R.id.row5col4)
        row5col4.setOnClickListener{
            if (clickCounter == 0){
                startX = 5
                startY = 4
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 5
                endY = 4
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row5col5 = findViewById<ConstraintLayout>(R.id.row5col5)
        row5col5.setOnClickListener{
            if (clickCounter == 0){
                startX = 5
                startY = 5
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 5
                endY = 5
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row5col6 = findViewById<ConstraintLayout>(R.id.row5col6)
        row5col6.setOnClickListener{
            if (clickCounter == 0){
                startX = 5
                startY = 6
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 5
                endY = 6
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row5col7 = findViewById<ConstraintLayout>(R.id.row5col7)
        row5col7.setOnClickListener{
            if (clickCounter == 0){
                startX =5
                startY = 7
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 5
                endY = 7
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row6col0 = findViewById<ConstraintLayout>(R.id.row6col0)
        row6col0.setOnClickListener{
            if (clickCounter == 0){
                startX =6
                startY =0
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 6
                endY = 0
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row6col1 = findViewById<ConstraintLayout>(R.id.row6col1)
        row6col1.setOnClickListener{
            if (clickCounter == 0){
                startX = 6
                startY = 1
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 6
                endY = 1
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row6col2 = findViewById<ConstraintLayout>(R.id.row6col2)
        row6col2.setOnClickListener{
            if (clickCounter == 0){
                startX = 6
                startY = 2
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 6
                endY = 2
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard( board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row6col3 = findViewById<ConstraintLayout>(R.id.row6col3)
        row6col3.setOnClickListener{
            if (clickCounter == 0){
                startX =6
                startY = 3
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 6
                endY = 3
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row6col4 = findViewById<ConstraintLayout>(R.id.row6col4)
        row6col4.setOnClickListener{
            if (clickCounter == 0){
                startX =6
                startY = 4
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 6
                endY = 4
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
                openWinDialog(game)
            }
        }
        val row6col5 = findViewById<ConstraintLayout>(R.id.row6col5)
        row6col5.setOnClickListener{
            if (clickCounter == 0){
                startX = 6
                startY = 5
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 6
                endY = 5
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row6col6 = findViewById<ConstraintLayout>(R.id.row6col6)
        row6col6.setOnClickListener{
            if (clickCounter == 0){
                startX =6
                startY = 6
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 6
                endY = 6
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row6col7 = findViewById<ConstraintLayout>(R.id.row6col7)
        row6col7.setOnClickListener{
            if (clickCounter == 0){
                startX =6
                startY = 7
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 6
                endY = 7
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row7col0 = findViewById<ConstraintLayout>(R.id.row7col0)
        row7col0.setOnClickListener{
            if (clickCounter == 0){
                startX =7
                startY = 0
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 7
                endY = 0
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row7col1 = findViewById<ConstraintLayout>(R.id.row7col1)
        row7col1.setOnClickListener{
            if (clickCounter == 0){
                startX = 7
                startY = 1
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 7
                endY = 1
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row7col2 = findViewById<ConstraintLayout>(R.id.row7col2)
        row7col2.setOnClickListener{
            if (clickCounter == 0){
                startX =7
                startY = 2
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 7
                endY = 2
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row7col3 = findViewById<ConstraintLayout>(R.id.row7col3)
        row7col3.setOnClickListener{
            if (clickCounter == 0){
                startX =7
                startY = 3
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 7
                endY = 3
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row7col4 = findViewById<ConstraintLayout>(R.id.row7col4)
        row7col4.setOnClickListener{
            if (clickCounter == 0){
                startX = 7
                startY = 4
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 7
                endY = 4
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row7col5 = findViewById<ConstraintLayout>(R.id.row7col5)
        row7col5.setOnClickListener{
            if (clickCounter == 0){
                startX =7
                startY = 5
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 7
                endY = 5
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)

            }
        }
        val row7col6 = findViewById<ConstraintLayout>(R.id.row7col6)
        row7col6.setOnClickListener{
            if (clickCounter == 0){
                startX =7
                startY = 6
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 7
                endY = 6
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }
        val row7col7 = findViewById<ConstraintLayout>(R.id.row7col7)
        row7col7.setOnClickListener{
            if (clickCounter == 0){
                startX =7
                startY = 7
                clickCounter ++
                showPosibilities(board, startX, startY)
            }
            else{
                endX = 7
                endY = 7
                if(game.playerMove(game.getTurn(), startX, startY, endX, endY))
                {
                    updateBoard(board, startX, startY, endX, endY)
                    startTimer(timerCounter, game, gameName)
                }
                clickCounter = 0
                closePosibilities(board)
            }
        }

    }
    public fun openWinDialog(game : Game){
        var title : String = "Black"
        if(game.getStatus() == GameStatus.ACTIVE){
            return
        }
        if(game.getStatus() == GameStatus.WHITE_WIN){
            title = "Player White Wins!"
            addMatchToDatabase("White", "Player1", game.getMoves(), gameName,"false")
        }
        if(game.getStatus() == GameStatus.BLACK_WIN){
            title = "Player Black Wins!"
            addMatchToDatabase("Black", "Player2", game.getMoves(), gameName,"false")

        }
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.checkmate_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val returnBtn : AppCompatButton = dialog.findViewById<AppCompatButton>(R.id.returnBtn)
        val winTitle : TextView = dialog.findViewById<TextView>(R.id.winTitle)
        winTitle.text = title
        returnBtn.setOnClickListener{
            val intent = Intent(this@GameActivity, ScreenActivity::class.java)
            startActivity(intent)
        }
        dialog.show()
    }

    private fun addMatchToDatabase(colorWon: String, playerWon: String, moves: ArrayList<String>, gameName: String, isOnline: String) {
        var moveList : String = ""
        for(i in 0 until moves.size){
            moveList = "$moveList${moves.get(i)}\n"
        }
        val databaseHandler : DatabaseHandler = DatabaseHandler(this)
        if(!colorWon.isEmpty() &&!playerWon.isEmpty() &&!moves.isEmpty() &&!gameName.isEmpty() &&!isOnline.isEmpty())
        {
            databaseHandler.addMatch(MatchModelClass(0, colorWon, playerWon, moveList, gameName, isOnline))
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateBoard(board: Board, startX : Int, startY : Int, endX : Int, endY : Int){
        var tablePieces = Array(8){IntArray(8)}
        tablePieces[0][0] = R.id.row0col0img
        tablePieces[0][1] = R.id.row0col1img
        tablePieces[0][2] = R.id.row0col2img
        tablePieces[0][3] = R.id.row0col3img
        tablePieces[0][4] = R.id.row0col4img
        tablePieces[0][5] = R.id.row0col5img
        tablePieces[0][6] = R.id.row0col6img
        tablePieces[0][7] = R.id.row0col7img
        tablePieces[1][0] = R.id.row1col0img
        tablePieces[1][1] = R.id.row1col1img
        tablePieces[1][2] = R.id.row1col2img
        tablePieces[1][3] = R.id.row1col3img
        tablePieces[1][4] = R.id.row1col4img
        tablePieces[1][5] = R.id.row1col5img
        tablePieces[1][6] = R.id.row1col6img
        tablePieces[1][7] = R.id.row1col7img
        tablePieces[2][0] = R.id.row2col0img
        tablePieces[2][1] = R.id.row2col1img
        tablePieces[2][2] = R.id.row2col2img
        tablePieces[2][3] = R.id.row2col3img
        tablePieces[2][4] = R.id.row2col4img
        tablePieces[2][5] = R.id.row2col5img
        tablePieces[2][6] = R.id.row2col6img
        tablePieces[2][7] = R.id.row2col7img
        tablePieces[3][0] = R.id.row3col0img
        tablePieces[3][1] = R.id.row3col1img
        tablePieces[3][2] = R.id.row3col2img
        tablePieces[3][3] = R.id.row3col3img
        tablePieces[3][4] = R.id.row3col4img
        tablePieces[3][5] = R.id.row3col5img
        tablePieces[3][6] = R.id.row3col6img
        tablePieces[3][7] = R.id.row3col7img
        tablePieces[4][0] = R.id.row4col0img
        tablePieces[4][1] = R.id.row4col1img
        tablePieces[4][2] = R.id.row4col2img
        tablePieces[4][3] = R.id.row4col3img
        tablePieces[4][4] = R.id.row4col4img
        tablePieces[4][5] = R.id.row4col5img
        tablePieces[4][6] = R.id.row4col6img
        tablePieces[4][7] = R.id.row4col7img
        tablePieces[5][0] = R.id.row5col0img
        tablePieces[5][1] = R.id.row5col1img
        tablePieces[5][2] = R.id.row5col2img
        tablePieces[5][3] = R.id.row5col3img
        tablePieces[5][4] = R.id.row5col4img
        tablePieces[5][5] = R.id.row5col5img
        tablePieces[5][6] = R.id.row5col6img
        tablePieces[5][7] = R.id.row5col7img
        tablePieces[6][0] = R.id.row6col0img
        tablePieces[6][1] = R.id.row6col1img
        tablePieces[6][2] = R.id.row6col2img
        tablePieces[6][3] = R.id.row6col3img
        tablePieces[6][4] = R.id.row6col4img
        tablePieces[6][5] = R.id.row6col5img
        tablePieces[6][6] = R.id.row6col6img
        tablePieces[6][7] = R.id.row6col7img
        tablePieces[7][0] = R.id.row7col0img
        tablePieces[7][1] = R.id.row7col1img
        tablePieces[7][2] = R.id.row7col2img
        tablePieces[7][3] = R.id.row7col3img
        tablePieces[7][4] = R.id.row7col4img
        tablePieces[7][5] = R.id.row7col5img
        tablePieces[7][6] = R.id.row7col6img
        tablePieces[7][7] = R.id.row7col7img

        val cell1 = findViewById<ImageView>(tablePieces[endX][endY])
        val cell2 = findViewById<ImageView>(tablePieces[startX][startY])
        cell1.setImageDrawable(cell2.drawable)
        cell1.isVisible = true
        cell2.isVisible = false
        if(endX==0 && board.getBox(endX, endY).piece!!.pieceString()=="Queen" && !board.getBox(endX, endY).piece!!.isWhite){
            cell1.setImageDrawable(resources.getDrawable(R.drawable.blackqueen))
        }
        if(endX==7 && board.getBox(endX, endY).piece!!.pieceString()=="Queen" && board.getBox(endX, endY).piece!!.isWhite){
            cell1.setImageDrawable(resources.getDrawable(R.drawable.whitequeen))
        }

    }
    private fun startTimer( counterTimer : Int, game : Game, gameName : String){
        timerCounter++
        val player1Time : TextView = findViewById(R.id.player1Time)
        player1Time.isVisible = false
        val player2Time : TextView = findViewById(R.id.player2Time)
        player2Time.isVisible = false
        val player1Timer : ImageView = findViewById(R.id.player1Timer)
        player1Timer.isVisible = false
        val player2Timer : ImageView = findViewById(R.id.player2Timer)
        player2Timer.isVisible = false
        var duration : Long = TimeUnit.MINUTES.toMillis(2)
        duration = when(gameName){
            "CLASSIC" -> TimeUnit.HOURS.toMillis(1)
            "BLITZ" -> TimeUnit.MINUTES.toMillis(1)
            "BLITZ 5" -> TimeUnit.MINUTES.toMillis(5)
            "RAPID 10" -> TimeUnit.MINUTES.toMillis(10)
            else -> TimeUnit.MINUTES.toMillis(2)
        }
        val timer = object: CountDownTimer(duration, 1000){

            override fun onTick(millsUntilFinished: Long) {
                var minutesRemaining = (millsUntilFinished/60000).toInt()
                if(gameName == "BLITZ" || gameName == "BLITZ 5")
                {
                    if(counterTimer == timerCounter-1)
                    {
                        val secondsRemaining : String
                        if((millsUntilFinished/1000 - minutesRemaining*60)<10)
                            secondsRemaining = "0${(millsUntilFinished/1000 - minutesRemaining*60)}"
                        else
                            secondsRemaining = (millsUntilFinished/1000 - minutesRemaining*60).toString()

                        val stringDuration : String = "$minutesRemaining:$secondsRemaining"
                        if(game.getTurnString() == "Player1"){
                            player1Time.text = stringDuration
                            player1Time.isVisible = true
                            player1Timer.isVisible = true
                            player2Time.isVisible = false
                            player2Timer.isVisible = false
                        }
                        else{
                            player2Time.text = stringDuration
                            player2Time.isVisible = true
                            player2Timer.isVisible = true
                            player1Time.isVisible = false
                            player1Timer.isVisible = false
                        }
                    }
                    else
                    {
                        cancel()
                    }
                }
                else{
                    if(counterTimer == 0){
                        val secondsRemaining : String
                        if((millsUntilFinished/1000 - minutesRemaining*60)<10)
                            secondsRemaining = "0${(millsUntilFinished/1000 - minutesRemaining*60)}"
                        else
                            secondsRemaining = (millsUntilFinished/1000 - minutesRemaining*60).toString()

                        val stringDuration : String = "$minutesRemaining:$secondsRemaining"
                        if(game.getTurnString() == "Player1"){
                            player1Time.text = stringDuration
                            player1Time.isVisible = true
                            player1Timer.isVisible = true
                            player2Time.isVisible = false
                            player2Timer.isVisible = false
                        }
                        else{
                            player2Time.text = stringDuration
                            player2Time.isVisible = true
                            player2Timer.isVisible = true
                            player1Time.isVisible = false
                            player1Timer.isVisible = false
                        }

                    }
                    else{
                        cancel()
                    }
                }

            }

            override fun onFinish() {
                if(game.getTurnString() == "Player1" )
                    game.setStatus(GameStatus.BLACK_WIN)
                else
                    game.setStatus(GameStatus.WHITE_WIN)
                openWinDialog(game)
            }
        }
        timer.start()

    }
    private fun showPosibilities ( board : Board, startX : Int, startY : Int){
        val tableBoxes = Array(8){IntArray(8)}
        tableBoxes[0][0] = R.id.row0col0
        tableBoxes[0][1] = R.id.row0col1
        tableBoxes[0][2] = R.id.row0col2
        tableBoxes[0][3] = R.id.row0col3
        tableBoxes[0][4] = R.id.row0col4
        tableBoxes[0][5] = R.id.row0col5
        tableBoxes[0][6] = R.id.row0col6
        tableBoxes[0][7] = R.id.row0col7
        tableBoxes[1][0] = R.id.row1col0
        tableBoxes[1][1] = R.id.row1col1
        tableBoxes[1][2] = R.id.row1col2
        tableBoxes[1][3] = R.id.row1col3
        tableBoxes[1][4] = R.id.row1col4
        tableBoxes[1][5] = R.id.row1col5
        tableBoxes[1][6] = R.id.row1col6
        tableBoxes[1][7] = R.id.row1col7
        tableBoxes[2][0] = R.id.row2col0
        tableBoxes[2][1] = R.id.row2col1
        tableBoxes[2][2] = R.id.row2col2
        tableBoxes[2][3] = R.id.row2col3
        tableBoxes[2][4] = R.id.row2col4
        tableBoxes[2][5] = R.id.row2col5
        tableBoxes[2][6] = R.id.row2col6
        tableBoxes[2][7] = R.id.row2col7
        tableBoxes[3][0] = R.id.row3col0
        tableBoxes[3][1] = R.id.row3col1
        tableBoxes[3][2] = R.id.row3col2
        tableBoxes[3][3] = R.id.row3col3
        tableBoxes[3][4] = R.id.row3col4
        tableBoxes[3][5] = R.id.row3col5
        tableBoxes[3][6] = R.id.row3col6
        tableBoxes[3][7] = R.id.row3col7
        tableBoxes[4][0] = R.id.row4col0
        tableBoxes[4][1] = R.id.row4col1
        tableBoxes[4][2] = R.id.row4col2
        tableBoxes[4][3] = R.id.row4col3
        tableBoxes[4][4] = R.id.row4col4
        tableBoxes[4][5] = R.id.row4col5
        tableBoxes[4][6] = R.id.row4col6
        tableBoxes[4][7] = R.id.row4col7
        tableBoxes[5][0] = R.id.row5col0
        tableBoxes[5][1] = R.id.row5col1
        tableBoxes[5][2] = R.id.row5col2
        tableBoxes[5][3] = R.id.row5col3
        tableBoxes[5][4] = R.id.row5col4
        tableBoxes[5][5] = R.id.row5col5
        tableBoxes[5][6] = R.id.row5col6
        tableBoxes[5][7] = R.id.row5col7
        tableBoxes[6][0] = R.id.row6col0
        tableBoxes[6][1] = R.id.row6col1
        tableBoxes[6][2] = R.id.row6col2
        tableBoxes[6][3] = R.id.row6col3
        tableBoxes[6][4] = R.id.row6col4
        tableBoxes[6][5] = R.id.row6col5
        tableBoxes[6][6] = R.id.row6col6
        tableBoxes[6][7] = R.id.row6col7
        tableBoxes[7][0] = R.id.row7col0
        tableBoxes[7][1] = R.id.row7col1
        tableBoxes[7][2] = R.id.row7col2
        tableBoxes[7][3] = R.id.row7col3
        tableBoxes[7][4] = R.id.row7col4
        tableBoxes[7][5] = R.id.row7col5
        tableBoxes[7][6] = R.id.row7col6
        tableBoxes[7][7] = R.id.row7col7
        if(board.getBox(startX, startY).piece == null){
            return
        }
        for(i in 0..7){
            for(j in 0..7){
                val cell = findViewById<ConstraintLayout>(tableBoxes[i][j])
                if(board.getBox(startX, startY).piece!!.canMove(board, board.getBox(startX, startY),board.getBox(i, j))){
                    if((i+j)%2==0){
                        cell.setBackgroundColor(getColor(R.color.green_chessboardblack_selected))
                    }
                    else{
                        cell.setBackgroundColor(getColor(R.color.green_chessboardwhite_selected))
                    }
                }
            }
        }

    }
    private fun closePosibilities(board : Board?){
        val tableBoxes = Array(8){IntArray(8)}
        tableBoxes[0][0] = R.id.row0col0
        tableBoxes[0][1] = R.id.row0col1
        tableBoxes[0][2] = R.id.row0col2
        tableBoxes[0][3] = R.id.row0col3
        tableBoxes[0][4] = R.id.row0col4
        tableBoxes[0][5] = R.id.row0col5
        tableBoxes[0][6] = R.id.row0col6
        tableBoxes[0][7] = R.id.row0col7
        tableBoxes[1][0] = R.id.row1col0
        tableBoxes[1][1] = R.id.row1col1
        tableBoxes[1][2] = R.id.row1col2
        tableBoxes[1][3] = R.id.row1col3
        tableBoxes[1][4] = R.id.row1col4
        tableBoxes[1][5] = R.id.row1col5
        tableBoxes[1][6] = R.id.row1col6
        tableBoxes[1][7] = R.id.row1col7
        tableBoxes[2][0] = R.id.row2col0
        tableBoxes[2][1] = R.id.row2col1
        tableBoxes[2][2] = R.id.row2col2
        tableBoxes[2][3] = R.id.row2col3
        tableBoxes[2][4] = R.id.row2col4
        tableBoxes[2][5] = R.id.row2col5
        tableBoxes[2][6] = R.id.row2col6
        tableBoxes[2][7] = R.id.row2col7
        tableBoxes[3][0] = R.id.row3col0
        tableBoxes[3][1] = R.id.row3col1
        tableBoxes[3][2] = R.id.row3col2
        tableBoxes[3][3] = R.id.row3col3
        tableBoxes[3][4] = R.id.row3col4
        tableBoxes[3][5] = R.id.row3col5
        tableBoxes[3][6] = R.id.row3col6
        tableBoxes[3][7] = R.id.row3col7
        tableBoxes[4][0] = R.id.row4col0
        tableBoxes[4][1] = R.id.row4col1
        tableBoxes[4][2] = R.id.row4col2
        tableBoxes[4][3] = R.id.row4col3
        tableBoxes[4][4] = R.id.row4col4
        tableBoxes[4][5] = R.id.row4col5
        tableBoxes[4][6] = R.id.row4col6
        tableBoxes[4][7] = R.id.row4col7
        tableBoxes[5][0] = R.id.row5col0
        tableBoxes[5][1] = R.id.row5col1
        tableBoxes[5][2] = R.id.row5col2
        tableBoxes[5][3] = R.id.row5col3
        tableBoxes[5][4] = R.id.row5col4
        tableBoxes[5][5] = R.id.row5col5
        tableBoxes[5][6] = R.id.row5col6
        tableBoxes[5][7] = R.id.row5col7
        tableBoxes[6][0] = R.id.row6col0
        tableBoxes[6][1] = R.id.row6col1
        tableBoxes[6][2] = R.id.row6col2
        tableBoxes[6][3] = R.id.row6col3
        tableBoxes[6][4] = R.id.row6col4
        tableBoxes[6][5] = R.id.row6col5
        tableBoxes[6][6] = R.id.row6col6
        tableBoxes[6][7] = R.id.row6col7
        tableBoxes[7][0] = R.id.row7col0
        tableBoxes[7][1] = R.id.row7col1
        tableBoxes[7][2] = R.id.row7col2
        tableBoxes[7][3] = R.id.row7col3
        tableBoxes[7][4] = R.id.row7col4
        tableBoxes[7][5] = R.id.row7col5
        tableBoxes[7][6] = R.id.row7col6
        tableBoxes[7][7] = R.id.row7col7
        for( i in 0..7){
            for(j in 0..7){
                val cell = findViewById<ConstraintLayout>(tableBoxes[i][j])
                if((i+j)%2==0){
                    cell.setBackgroundColor(getColor(R.color.green_chessboardblack))
                }
                else
                    cell.setBackgroundColor(getColor(R.color.green_chessboardwhite))
            }
        }
    }

}
