package com.example.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.litote.kmongo.util.idValue

data class Post (
    val userId: Int,
    @BsonId
    val id: Int,
    val title: String,
    val body: String
)