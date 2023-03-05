package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar2.visibility = View.GONE
        btn3.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)


        }
        btn.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            var name = name.text.toString()
            var address = address.text.toString()
            var number = number.text.toString()

            if (name.isEmpty() || number.isEmpty() || address.isEmpty()) {
                progressBar2.visibility = View.GONE
                Toast.makeText(this, "The field is empty add data in field", Toast.LENGTH_SHORT)
                    .show()


            } else {

                val person = hashMapOf(
                    "number" to "$number",
                    "name" to "$name",
                    "address" to "$address"

                )
                db.collection("person").add(person).addOnSuccessListener { e ->
                    progressBar2.visibility = View.GONE

                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
//                    this.name.text = this.age.text = this.id.text = "";

                    this.number.text.clear()
                    this.address.text.clear()
                    this.name.text.clear()

                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

                }
            }

        }


    }
}