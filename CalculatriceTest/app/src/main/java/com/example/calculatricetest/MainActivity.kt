package com.example.calculatricetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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

        val buttonOpenParenthesis = findViewById<Button>(R.id.button_open_parenthesis)
        val buttonCloseParenthesis = findViewById<Button>(R.id.button_close_parenthesis)
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

        buttonOpenParenthesis.setOnClickListener {
            editTextDisplay.append("(")
        }

        buttonCloseParenthesis.setOnClickListener {
            editTextDisplay.append(")")
        }

        buttonPourcentage.setOnClickListener {
            editTextDisplay.append("%")
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

        buttonEgal.setOnClickListener {

            val secondNumber = editTextDisplay.text.toString().toDouble()
            var result = 0.0

            if (isAdditionClicked) {
                result = firstNumber + secondNumber
            }

            if (isSubtractionClicked) {
                result = firstNumber - secondNumber
            }

            if (isMultiplicationClicked) {
                result = firstNumber * secondNumber
            }

            if (isDivisionClicked) {
                if (secondNumber != 0.0) {
                    result = firstNumber / secondNumber
                }
            }

            editTextDisplay.setText(result.toString())
        }

        // Écouteur pour le bouton de suppression

        val buttonDelete = findViewById<Button>(R.id.button_supprimer)
        buttonDelete.setOnClickListener {
            val currentText = editTextDisplay.text.toString()
            if (currentText.isNotEmpty()) {
                val newText = currentText.substring(0, currentText.length - 1)
                editTextDisplay.setText(newText)
                editTextDisplay.setSelection(newText.length) // Met le curseur à la fin du texte
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
