package com.example.chess

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.firebase.database.*

var gameKey : String = "null"
var isCodeMaker = true;
var code = "null"
var codeFound = false
class MatchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view : View =  inflater.inflate(R.layout.fragment_match, container, false)
        val classicLayout : ConstraintLayout = view.findViewById(R.id.classicLayout)
        val blitzLayout : ConstraintLayout = view.findViewById(R.id.blitzLayout)
        val blitz5Layout : ConstraintLayout = view.findViewById(R.id.blitz5Layout)
        val rapid10Layout : ConstraintLayout = view.findViewById(R.id.rapid10Layout)
        val twoPlayerGameType : AppCompatButton = view.findViewById(R.id.twoPlayerGameType)
        val computerGameType : AppCompatButton = view.findViewById(R.id.computerGameType)
        val onlineGameType : AppCompatButton = view.findViewById(R.id.onlineGameType)
        val originalColor = computerGameType.currentTextColor
        var gameSelected : AppCompatButton =twoPlayerGameType
        code = "null"
        gameKey = "null"

        twoPlayerGameType.setOnClickListener{
            twoPlayerGameType.setTextColor(resources.getColor(R.color.homecard_purple))
            computerGameType.setTextColor(originalColor)
            onlineGameType.setTextColor(originalColor)
            gameSelected = twoPlayerGameType
        }
        computerGameType.setOnClickListener{
            twoPlayerGameType.setTextColor(originalColor)
            computerGameType.setTextColor(resources.getColor(R.color.homecard_purple))
            onlineGameType.setTextColor(originalColor)
            gameSelected = computerGameType
        }
        onlineGameType.setOnClickListener{
            twoPlayerGameType.setTextColor(originalColor)
            computerGameType.setTextColor(originalColor)
            onlineGameType.setTextColor(resources.getColor(R.color.homecard_purple))
            gameSelected = onlineGameType
        }
        classicLayout.setOnClickListener{ view ->
            if(gameSelected == onlineGameType) {
                onlineGameForm("CLASSIC")
            }
            else {
                val intent = Intent(activity, GameActivity::class.java)
                intent.putExtra("gameName", "CLASSIC")
                startActivity(intent)
            }
        }
        blitzLayout.setOnClickListener{ view ->
            if(gameSelected == onlineGameType) {
                onlineGameForm("BLITZ")
            }
            else{
                val intent = Intent(activity, GameActivity::class.java)
                intent.putExtra("gameName", "BLITZ")
                startActivity(intent)
            }
        }
        blitz5Layout.setOnClickListener{ view ->
            if(gameSelected == onlineGameType) {
                onlineGameForm("BLITZ 5")
            }
            else{
                val intent = Intent(activity, GameActivity::class.java)
                intent.putExtra("gameName", "BLITZ 5")
                startActivity(intent)
            }

        }
        rapid10Layout.setOnClickListener{ view ->
            if(gameSelected == onlineGameType) {
                onlineGameForm("RAPID 10")
            }
            else {
                val intent = Intent(activity, GameActivity::class.java)
                intent.putExtra("gameName", "RAPID 10")
                startActivity(intent)
            }
        }
        return view
    }
    private fun onlineGameForm(gameType : String){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.onlinegame_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val closeCodeBtn : ImageView = dialog.findViewById(R.id.closeCodeBtn)
        val createCodeBtn :AppCompatButton =  dialog.findViewById(R.id.createCodeBtn)
        val joinCodeBtn : AppCompatButton = dialog.findViewById(R.id.joinCodeBtn)
        val onlineCodeForm : EditText = dialog.findViewById(R.id.onlineCodeForm)
        val statusText : TextView = dialog.findViewById(R.id.statusText)
        val originalColor = statusText.currentTextColor
        closeCodeBtn.setOnClickListener{
            dialog.cancel()
        }
        createCodeBtn.setOnClickListener{
            code = "null"
            codeFound = false
            gameKey = "null"
            code = onlineCodeForm.text.toString()
            statusText.isVisible = true
            statusText.text = "Loading..."
            statusText.setTextColor(originalColor)

            if(code!="null" && code !="")
            {
                isCodeMaker = true
                FirebaseDatabase.getInstance().reference.child("codes").addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var check = isValueAvailable(snapshot, code)
                        Handler().postDelayed({
                            if(check == true){
                                statusText.isVisible = true
                                statusText.text = "Code not available. Please enter another code"
                                statusText.setTextColor(resources.getColor(R.color.homecard_red))
                            }
                            else{
                                FirebaseDatabase.getInstance().reference.child("codes").push().setValue(code)
                                isValueAvailable(snapshot, code)
                                Handler().postDelayed({
                                    accepted(gameType)
                                    statusText.isVisible = true
                                    onlineCodeForm.setText("null")
                                    code="null"
                                    gameKey = "null"
                                    check = true
                                    statusText.text = "Please don't go back"
                                    statusText.setTextColor(originalColor)
                                }, 300)
                                dialog.cancel()
                            }
                        },2000)
                    }

                    override fun onCancelled(p0: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
            }
            else{
                statusText.text = "Please enter a valid code"
                statusText.isVisible = true
                statusText.setTextColor(resources.getColor(R.color.homecard_red))

            }
        }
        joinCodeBtn.setOnClickListener{
            code = "null"
            codeFound = false
            gameKey = "null"
            code = onlineCodeForm.text.toString()
            statusText.isVisible = true
            statusText.text = "Loading..."
            statusText.setTextColor(originalColor)
            if(code!="null"&& code!="")
            {
                isCodeMaker = false
                FirebaseDatabase.getInstance().reference.child("codes").addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var data : Boolean = isValueAvailable(snapshot, code)
                        Handler().postDelayed({
                            if(data == true){
                                codeFound = true
                                accepted(gameType)
                                code="null"
                                gameKey="null"
                                data = false
                                onlineCodeForm.setText("")
                                statusText.isVisible = false
                            }
                            else{
                                statusText.isVisible = true
                                statusText.text = "Invalid code"
                                statusText.setTextColor(resources.getColor(R.color.homecard_red))
                            }
                        }, 2000)
                    }

                    override fun onCancelled(p0: DatabaseError) {
                    }
                })
            }
            else{
                statusText.isVisible = true
                statusText.text = "Please enter a valid code"
                statusText.setTextColor(resources.getColor(R.color.homecard_red))
            }
        }

        dialog.show()
    }
    fun accepted(gameType: String){
        val intent = Intent(activity, OnlineGameActivity::class.java)
        intent.putExtra("gameName", gameType)
        startActivity(intent)
    }
    fun isValueAvailable( snapshot: DataSnapshot, code : String): Boolean{
        val data = snapshot.children
        data.forEach{
            var value = it.value.toString()
            if(value==code){
                gameKey = it.key.toString()
                return true
            }
        }
        return false
    }

}