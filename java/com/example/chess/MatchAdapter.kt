package com.example.chess

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class MatchAdapter(val context: Context, private val matchList : ArrayList<MatchModelClass>) :
    RecyclerView.Adapter<MatchAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleGameName = itemView.findViewById<TextView>(R.id.titleGameName)
        val desGameName = itemView.findViewById<TextView>(R.id.desGameName)
        val viewMatch = itemView.findViewById<ImageView>(R.id.viewMatch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.match_row, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = matchList[position]
        val title : String = if(currentItem.isOnline=="true") "Online" else "Offline"
        holder.titleGameName.text = "$title ${currentItem.gameType} Match"
        holder.desGameName.text = "${currentItem.playerWon} Won"
        holder.viewMatch.setOnClickListener{
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.viewmatches_dialog)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(true)
            val closeMoveList : AppCompatButton = dialog.findViewById<AppCompatButton>(R.id.closeMoveList)
            val moveList : TextView = dialog.findViewById(R.id.moveList)
            val moveListTitle : TextView = dialog.findViewById(R.id.moveListVictoryTitle)
            val moveListType : TextView = dialog.findViewById(R.id.moveListVictoryType)
            moveListType.text = "${currentItem.gameType} Match"
            moveList.text = currentItem.moveList

            if(currentItem.playerWon == "You"){
                moveListTitle.text = "${currentItem.playerWon}rs Victory"
            }
            else{
                moveListTitle.text = "${currentItem.playerWon}'s Victory"
            }
            closeMoveList.setOnClickListener{
               dialog.cancel()
            }
            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return matchList.size
    }
}