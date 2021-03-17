package com.example.data

import com.example.data.collections.Post
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.setValue

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

suspend fun deletePost(noteId: Int): Boolean {
    val post = posts.findOne(Post::id eq noteId)
    post?.let { post ->
            return posts.deleteOneById(post.id).wasAcknowledged()
    } ?: return false

}
