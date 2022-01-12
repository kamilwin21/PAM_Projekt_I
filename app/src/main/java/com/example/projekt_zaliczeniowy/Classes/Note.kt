package com.example.projekt_zaliczeniowy.Classes

class Note {

    var id: Int = 0
    var title: String = String()
    var description: String = String()


    constructor(

        title: String,
        description: String
    ){

        this.title = title
        this.description = description
    }
    constructor(
        id: Int,
        title: String,
        description: String
    ){
        this.id = id
        this.title = title
        this.description = description
    }
    constructor()
}