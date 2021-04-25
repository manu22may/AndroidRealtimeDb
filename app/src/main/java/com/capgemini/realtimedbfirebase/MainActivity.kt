package com.capgemini.realtimedbfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveB.setOnClickListener {
            saveUser()
        }

        displayRecyclerFragment()

    }

    //----DATABASE : WRITING----
    private fun saveUser() {
        val name = nameE.text.toString().trim()
        val phone = phoneE.text.toString()
        if(name.isEmpty()){ nameE.error="Please enter name"
            return }
        if(phone.isEmpty()){ phoneE.error="Please enter name"
            return }

        //----FIREBASE : ADD---
        val ref =FirebaseDatabase.getInstance().getReference("users") //create a base path
        val userid =ref.push().key!! //generate new key(primary)
        val user = User(userid,name,phone,ratingBar.numStars.toString())
        ref.child(userid).setValue(user).addOnCompleteListener {
            Toast.makeText(this,"User added",Toast.LENGTH_SHORT).show() //on  user added toast
        }

    }


    //-----DATABASE : READING(RECYCLER FRAGMENT)----
    private fun displayRecyclerFragment() {
        val frag = UserFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.parentL,frag)
            .commit()
    }



}