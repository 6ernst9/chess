package com.example.chess

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "GamesDatabase"
        private const val TABLE_CONTACTS = "GamesTable"

        private  const val KEY_ID = "_id"
        private  const val KEY_COLORWON = "color"
        private  const val KEY_PLAYERWON = "player"
        private  const val KEY_MOVELIST = "movelist"
        private  const val KEY_GAMETYPE = "gametype"
        private  const val KEY_ONLINE = "online"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_COLORWON + " TEXT," +
                KEY_PLAYERWON + " TEXT," +
                KEY_MOVELIST + " TEXT," +
                KEY_GAMETYPE + " TEXT," +
                KEY_ONLINE + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }
    fun addMatch( matchModelClass: MatchModelClass) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_COLORWON, matchModelClass.colorWon)
        contentValues.put(KEY_PLAYERWON, matchModelClass.playerWon)
        contentValues.put(KEY_MOVELIST, matchModelClass.moveList)
        contentValues.put(KEY_GAMETYPE, matchModelClass.gameType)
        contentValues.put(KEY_ONLINE, matchModelClass.isOnline)

        val succes = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return succes
    }
    @SuppressLint("Range")
    fun viewMatch() : ArrayList<MatchModelClass>{
        val matchList : ArrayList<MatchModelClass> = ArrayList<MatchModelClass>()
        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor : Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }
        catch(e : SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id : Int
        var playerWon : String
        var colorWon : String
        var moveList : String
        var gameType : String
        var isOnline : String

        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                playerWon = cursor.getString(cursor.getColumnIndex(KEY_PLAYERWON))
                colorWon = cursor.getString(cursor.getColumnIndex(KEY_COLORWON))
                moveList = cursor.getString(cursor.getColumnIndex(KEY_MOVELIST))
                gameType = cursor.getString(cursor.getColumnIndex(KEY_GAMETYPE))
                isOnline = cursor.getString(cursor.getColumnIndex(KEY_ONLINE))

                val match = MatchModelClass(
                    id = id,
                    colorWon = colorWon,
                    playerWon = playerWon,
                    moveList =  moveList,
                    gameType = gameType,
                    isOnline = isOnline)
                matchList.add(match)
            }
        }
        return matchList
    }
    fun deleteMatch(match: MatchModelClass): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, match.id)
        val succes = db.delete(TABLE_CONTACTS, KEY_ID + "=" + match.id, null )
        db.close()
        return succes
    }
}