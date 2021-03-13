package com.example.data

import com.example.data.collections.Post
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

private val client = KMongo.createClient().coroutine
private val database = client.getDatabase("PostsDatabase")
private val posts = database.getCollection<Post>()

suspend fun savePost(post: Post): Boolean{
    val postExists = posts.findOneById(post.id) != null
    return if(postExists){
        posts.updateOneById(post.id, post).wasAcknowledged()
    } else {
        posts.insertOne(post).wasAcknowledged()
    }
}

suspend fun getPosts(): List<Post> {

    return posts.find().toList()
}
