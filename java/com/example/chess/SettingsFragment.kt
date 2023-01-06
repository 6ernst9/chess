package com.example.chess

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompatSideChannelService
import java.util.ArrayList

class SettingsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_settings, container, false)
        val createdByLogo : ImageView = view.findViewById(R.id.createdByLogo)
        val createdBy : TextView = view.findViewById(R.id.createdBy)
        val color = createdBy.currentTextColor
        if(color == resources.getColor(R.color.white)){
            createdByLogo.setImageDrawable(resources.getDrawable(R.drawable.logowhite))
        }
        if(color == resources.getColor(R.color.black)){
            createdByLogo.setImageDrawable(resources.getDrawable(R.drawable.logoblack))
        }
        val darkThemeSwitch : SwitchCompat = view.findViewById(R.id.darkThemeSwitch)

        when (requireContext().resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                darkThemeSwitch.isChecked = true
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                darkThemeSwitch.isChecked = false
            }
        }
        darkThemeSwitch.setOnCheckedChangeListener { _, isChecked: Boolean ->
            if(isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
            else
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
        return view
    }
    private fun reset(){
        val intent = Intent(activity,ScreenActivity::class.java)
        startActivity(intent)
    }



}