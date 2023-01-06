package com.example.chess

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import java.lang.reflect.Array

class ScoreFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_score, container, false)

        val wins = view.findViewById<TextView>(R.id.wins)
        val loses = view.findViewById<TextView>(R.id.loses)
        var winCount = 0
        var loseCount = 0
        for(i in onlineMatches()){
            if(i.playerWon == "You"){
                winCount++
            }
            if(i.playerWon == "Your Friend"){
                loseCount++
            }
        }
        wins.text = "$winCount\nWins"
        loses.text = "$loseCount\nLoses"
        val pieChart = view.findViewById<PieChart>(R.id.pieChart)
        val pieEntriesList = ArrayList<PieEntry>()
        pieEntriesList.add(PieEntry(pieList()[0].toFloat()))
        pieEntriesList.add(PieEntry(pieList()[1].toFloat()))
        val pieDataSet = PieDataSet(pieEntriesList,"Score")
        val pieData = PieData(pieDataSet)
        pieChart?.data = pieData
        pieChart?.setHoleColor(resources.getColor(R.color.transparent))
        pieChart?.animateY(1000, Easing.EaseInOutQuad)
        pieChart?.transparentCircleRadius
        val colors: ArrayList<Int> = ArrayList()
        pieChart?.legend?.isEnabled = false
        colors.add(resources.getColor(R.color.homecard_green))
        colors.add(resources.getColor(R.color.homecard_purple))
        pieDataSet.sliceSpace = 1f
        pieDataSet.colors = colors
        pieChart?.description?.isEnabled = false
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(15f)
        pieData.setValueTextColor(Color.WHITE)
        val localRecyclerView : RecyclerView = view.findViewById(R.id.localRecyclerView)
        val onlineRecyclerView : RecyclerView = view.findViewById(R.id.onlineRecyclerView)
        val noMatchesOnline : TextView = view.findViewById(R.id.noMatchesOnline)
        val noMatchesOffline : TextView = view.findViewById(R.id.noMatchesOffline)
        setupLocalRecyclerView(localRecyclerView, noMatchesOffline)
        setupOnlineRecyclerView(onlineRecyclerView, noMatchesOnline)
        return view

    }
    fun pieList() : IntArray{
        val array = IntArray(2)
        var player1Wins : Double = 0.0
        var player2Wins : Double = 0.0

        for(i in offlineMatches())
        {
            if(i.playerWon == "Player1"){
                player1Wins++
            }
            else{
                player2Wins++
            }
        }
        val player1Percentage : Int = ((player1Wins/offlineMatches().size.toDouble())*100).toInt()
        val player2Percentage : Int = ((player2Wins/offlineMatches().size.toDouble())*100).toInt()
        array[0] = player1Percentage
        array[1] = player2Percentage
        return array

    }
    fun setupLocalRecyclerView( localRecyclerView : RecyclerView, noMatchesOffline : TextView){
        if(offlineMatches().size>0){
            noMatchesOffline.isVisible = false
            localRecyclerView.isVisible = true
            localRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            val matchAdapter = MatchAdapter(requireContext(), offlineMatches())
            localRecyclerView.adapter = matchAdapter
        }
        else{
            localRecyclerView.isVisible = false
            noMatchesOffline.isVisible = true

        }
    }
    fun setupOnlineRecyclerView(onlineRecyclerView : RecyclerView, noMachesOnline : TextView){
        if(onlineMatches().size>0){
            noMachesOnline.isVisible = false
            onlineRecyclerView.isVisible = true
            onlineRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            val matchAdapter = MatchAdapter(requireContext(), onlineMatches())
            onlineRecyclerView.adapter = matchAdapter
        }
        else{
            noMachesOnline.isVisible = true
            onlineRecyclerView.isVisible = false
        }
    }
    private fun onlineMatches() : ArrayList<MatchModelClass>{
        var onlineMatches : ArrayList<MatchModelClass> = ArrayList<MatchModelClass>()
        for(i in getItemsList()){
            if(i.isOnline == "true"){
                onlineMatches.add(i)
            }
        }
        return onlineMatches
    }
    private fun offlineMatches() : ArrayList<MatchModelClass>{
        var offlineMatches : ArrayList<MatchModelClass> = ArrayList<MatchModelClass>()
        for(i in getItemsList()){
            if(i.isOnline == "false"){
                offlineMatches.add(i)
            }
        }
        return offlineMatches
    }
    private fun getItemsList() : java.util.ArrayList<MatchModelClass> {
        val databaseHandler : DatabaseHandler = DatabaseHandler(requireContext())
        return databaseHandler.viewMatch()
    }

}