package com.example.gandhasiri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class LegalFragment : Fragment() {

    private val steps = listOf(
        Pair("Register Your Land",
            "Ensure your land is registered at the local Tahsildar office. Carry your RTC (Record of Rights) document."),
        Pair("Tag and Document Each Tree",
            "Register every sandalwood tree with GPS and girth data using Gandha-Siri. This creates your legal digital record."),
        Pair("Apply for Harvesting Permission",
            "Submit Form-10 to the Divisional Forest Officer (DFO) with your tree register. Allow 30-60 days for processing."),
        Pair("Get Tree Inspection Done",
            "A forest official will visit your land to verify tree count and girth (minimum 30cm for heartwood). Be present."),
        Pair("Receive Transit Permit",
            "After approval, you get a Transit Permit (Form-28). This is MANDATORY while transporting timber. Keep it always."),
        Pair("Sell to Licensed Depot",
            "Sell to KSDL or authorized private depots. Retain the sale receipt for at least 5 years.")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_legal, container, false)
        val container = view.findViewById<LinearLayout>(R.id.legalContainer)

        steps.forEachIndexed { index, (title, desc) ->
            val stepView = inflater.inflate(R.layout.item_legal_step, container, false)
            stepView.findViewById<TextView>(R.id.tvStepNum).text = (index + 1).toString()
            stepView.findViewById<TextView>(R.id.tvStepTitle).text = title
            stepView.findViewById<TextView>(R.id.tvStepDesc).text = desc
            container.addView(stepView)
        }

        return view
    }
}