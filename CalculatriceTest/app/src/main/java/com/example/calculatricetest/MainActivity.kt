package com.example.calculatricetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {

    private lateinit var textViewDisplay: TextView

    //variable pour gestion de calcule addition

    private var firstNumber: Double = 0.0
    private var isAdditionClicked: Boolean = false
    private var isSubtractionClicked: Boolean = false
    private var isMultiplicationClicked: Boolean = false
    private var isDivisionClicked: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextDisplay = findViewById<EditText>(R.id.editTextDisplay)

        // Récupération des références des boutons en utilisant leur ID

        val buttonPourcentage = findViewById<Button>(R.id.button_pourcentage)
        val buttonDivision = findViewById<Button>(R.id.button_division)

        val buttonSept = findViewById<Button>(R.id.button_sept)
        val buttonHuit = findViewById<Button>(R.id.button_huit)
        val buttonNeuf = findViewById<Button>(R.id.button_neuf)
        val buttonSoustraction = findViewById<Button>(R.id.button_soustraction)

        val buttonQuatre = findViewById<Button>(R.id.button_quatre)
        val buttonCinq = findViewById<Button>(R.id.button_cinq)
        val buttonSix = findViewById<Button>(R.id.button_six)
        val buttonMultpilier = findViewById<Button>(R.id.button_multiplier)

        val buttonUn = findViewById<Button>(R.id.button_un)
        val buttonDeux = findViewById<Button>(R.id.button_deux)
        val buttonTrois = findViewById<Button>(R.id.button_trois)
        val buttonAdditionner = findViewById<Button>(R.id.button_additionner)

        val buttonZero = findViewById<Button>(R.id.button_zero)
        val buttonPoint = findViewById<Button>(R.id.button_point)
        val buttonEgal = findViewById<Button>(R.id.button_egale)


        // Ajout des écouteurs de clic pour chaque bouton

        buttonPourcentage.setOnClickListener {
            val currentText = editTextDisplay.text.toString()
            if (currentText.isNotEmpty()) {
                val number = currentText.toDouble()
                val result = number / 100 // Calcul du pourcentage
                editTextDisplay.setText(result.toString())
            }
        }

        buttonDivision.setOnClickListener {
            firstNumber = editTextDisplay.text.toString().toDouble()
            isDivisionClicked = true
            editTextDisplay.text.clear() // Efface le texte après avoir enregistré le premier nombre
        }

        buttonSept.setOnClickListener {
            editTextDisplay.append("7")
        }

        buttonHuit.setOnClickListener {
            editTextDisplay.append("8")
        }

        buttonNeuf.setOnClickListener {
            editTextDisplay.append("9")
        }

        buttonSoustraction.setOnClickListener {
            firstNumber = editTextDisplay.text.toString().toDouble()
            isSubtractionClicked = true
            editTextDisplay.text.clear() // Efface le texte après avoir enregistré le premier nombre
        }

        buttonQuatre.setOnClickListener {
            editTextDisplay.append("4")
        }

        buttonCinq.setOnClickListener {
            editTextDisplay.append("5")
        }

        buttonSix.setOnClickListener {
            editTextDisplay.append("6")
        }

        buttonMultpilier.setOnClickListener {
            firstNumber = editTextDisplay.text.toString().toDouble()
            isMultiplicationClicked = true
            editTextDisplay.text.clear() // Efface le texte après avoir enregistré le premier nombre
        }

        buttonUn.setOnClickListener {
            editTextDisplay.append("1")
        }

        buttonDeux.setOnClickListener {
            editTextDisplay.append("2")
        }

        buttonTrois.setOnClickListener {
            editTextDisplay.append("3")
        }

        buttonAdditionner.setOnClickListener {
            firstNumber = editTextDisplay.text.toString().toDouble()
            isAdditionClicked = true
            // Efface le texte après avoir enregistré le premier nombre
            editTextDisplay.text.clear()
        }

        buttonZero.setOnClickListener {
            editTextDisplay.append("0")
        }

        buttonPoint.setOnClickListener {
            editTextDisplay.append(".")
        }

        var lastResult: Double = 0.0

        buttonEgal.setOnClickListener {
            val secondNumber = editTextDisplay.text.toString().toDouble()
            var result = 0.0

            if (isAdditionClicked) {
                result = lastResult + secondNumber
            }

            if (isSubtractionClicked) {
                result = lastResult - secondNumber
            }

            if (isMultiplicationClicked) {
                result = lastResult * secondNumber
            }

            if (isDivisionClicked) {
                if (secondNumber != 0.0) {
                    result = lastResult / secondNumber
                }
            }

            lastResult = result // Stocke le résultat pour une utilisation ultérieure

            editTextDisplay.setText(result.toString())
        }

        // Écouteur pour le bouton de suppression

        val button = findViewById<Button>(R.id.button_supprimer)
        var startTime: Long = 0

        button.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    startTime = System.currentTimeMillis() // Enregistre le temps du clic
                    true // Retourne vrai pour indiquer que l'événement est consommé
                }
                MotionEvent.ACTION_UP -> {
                    val duration = System.currentTimeMillis() - startTime // Calcule la durée de l'appui
                    if (duration < 500) { // Si la durée est inférieure à 500 millisecondes, c'est un appui court
                        // Action pour un appui court
                        val currentText = editTextDisplay.text.toString()
                        if (currentText.isNotEmpty()) {
                            val newText = currentText.substring(0, currentText.length - 1)
                            editTextDisplay.setText(newText)
                            editTextDisplay.setSelection(newText.length)
                        }
                    } else {
                        // Action pour un appui long
                        editTextDisplay.setText("") // Efface tout le texte
                    }
                    true // Retourne vrai pour indiquer que l'événement est consommé
                }
                else -> false // Pour les autres actions, retourne faux
            }
        }

        editTextDisplay.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val enteredText = s.toString()
                // Vous pouvez effectuer des opérations ou des vérifications sur enteredText ici
                // Par exemple, vous pouvez vérifier la longueur du texte
                if (enteredText.length > 10) {
                    // Si le texte dépasse une certaine longueur, vous pouvez effectuer une action spécifique
                    // Par exemple, afficher un message à l'utilisateur ou exécuter une autre logique
                }
            }
        })

        //le reste du code
    }

}
