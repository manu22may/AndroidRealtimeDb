package com.capgemini.realtimedbfirebase

data class User(
    val id:String,
    val name:String,
    val phone:String,
    val rating:String
){
   constructor():this("","","","")//important for firebase to work

    override fun toString(): String {
        return "Name:$name , Phone:$phone , $rating"
    }
}