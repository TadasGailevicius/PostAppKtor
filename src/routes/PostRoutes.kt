package com.example.routes

import com.example.data.*
import com.example.data.collections.Post
import io.ktor.application.call
import io.ktor.features.ContentTransformationException
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.HttpStatusCode.Companion.Conflict
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.postRoutes() {
    route("/getPosts") {
        get {
            val posts = getPosts()
            call.respond(OK, posts)
        }
    }


    route("/addPost") {
        post {
            val post = try {
                call.receive<Post>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            if (savePost(post)) {
                call.respond(OK)
            } else {
                call.respond(Conflict)
            }
        }
    }

}
