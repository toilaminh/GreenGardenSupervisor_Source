package com.example.greengardensupervisor

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.greengardensupervisor.ui.theme.GreenGardenSupervisorTheme
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.annotations.Nullable
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import java.security.KeyStore.TrustedCertificateEntry

class MainActivity : ComponentActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        // Current T and H
        val cur_temp = findViewById<TextView>(R.id.cur_temp)
        val cur_humd = findViewById<TextView>(R.id.cur_humd)
        // Your current T and H setting
        val ur_temp = findViewById<TextView>(R.id.ur_temp)
        val ur_humd = findViewById<TextView>(R.id.ur_humd)
        // Your input data
        val temp = findViewById<EditText>(R.id.temp)
        val humd = findViewById<EditText>(R.id.humd)
        // Call Firebase database
        val database = Firebase.database
        // Ref
        val myRef1 = database.getReference("t")
        val myRef2 = database.getReference("h")
        val myRef_ct = database.getReference("ct")
        val myRef_ch = database.getReference("ch")

        // Current Temperature and Humidity read from DHT11
        myRef_ct.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<Int>()
                cur_temp.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        myRef_ch.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<Int>()
                cur_humd.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        // Your current setting
        myRef1.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<Int>()
                ur_temp.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        myRef2.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<Int>()
                ur_humd.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        // Update new setting
        val button = findViewById<Button>(R.id.btn_Confirm)
        button.setOnClickListener()
        {
            if(temp.text.isEmpty() || humd.text.isEmpty())
            {}
            else
            {
                val myRef3 = database.getReference("t")
                val myRef4 = database.getReference("h")
                myRef3.setValue(temp.text.toString().toInt())
                myRef4.setValue(humd.text.toString().toInt())
            }
        }
    }
}