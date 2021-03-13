package com.example.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Post (
    val userId: String,
    @BsonId
    val id: String = ObjectId().toString(),
    val title: String,
    val body: String,

    )