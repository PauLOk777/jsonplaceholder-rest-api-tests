package com.typicode.jsonplaceholder.endpoints;

import com.typicode.jsonplaceholder.config.Config;
import com.typicode.jsonplaceholder.models.Post;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostsEndPoint {

    public static Response getPostById(long id) {
        return given()
                .pathParam(Config.ID, id)
                .when()
                .get(Config.POST_BY_ID)
                .then()
                .extract()
                .response();
    }

    public static Response createPost(Post post) {
        return given()
                .body(post)
                .when()
                .post(Config.POSTS)
                .then()
                .extract()
                .response();
    }

    public static Response modifyPost(Post post) {
        return given()
                .pathParam(Config.ID, post.getId())
                .body(post)
                .when()
                .put(Config.POST_BY_ID)
                .then()
                .extract()
                .response();
    }

    public static Response deletePost(long id) {
        return given()
                .pathParam(Config.ID, id)
                .when()
                .delete(Config.POST_BY_ID)
                .then()
                .extract()
                .response();
    }
}
