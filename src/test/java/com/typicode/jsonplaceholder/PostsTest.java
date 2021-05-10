package com.typicode.jsonplaceholder;

import com.typicode.jsonplaceholder.config.Config;
import com.typicode.jsonplaceholder.models.Post;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.runner.RunWith;

import static com.typicode.jsonplaceholder.endpoints.PostsEndPoint.getPostById;
import static com.typicode.jsonplaceholder.endpoints.PostsEndPoint.createPost;
import static com.typicode.jsonplaceholder.endpoints.PostsEndPoint.modifyPost;
import static com.typicode.jsonplaceholder.endpoints.PostsEndPoint.deletePost;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class PostsTest {

    private static final int POST_ID = 1;
    private static final int NEXT_ID = 10;
    private static final int USER_ID = 1;
    private static final String MY_TITLE = "My title";
    private static final String MY_BODY = "My body";
    private static final Post EXISTING_POST = new Post(USER_ID, POST_ID, MY_TITLE, MY_BODY);
    private static final Post NEW_POST = new Post(USER_ID, MY_TITLE, MY_BODY);

    private static final int STATUS_CODE_OK = 200;
    private static final int STATUS_CODE_CREATED = 201;
    private static final int STATUS_NOT_FOUND = 404;

    @Before
    public void setUp() {
        RestAssured.baseURI = Config.BASE_URI;
    }

    @Test
    @Title("Should return 200 code (status ok) when try to get post by existing id")
    public void shouldReturnOkStatusAndPostWithSameId() {
        getPostById(POST_ID)
                .then()
                .body(Config.ID, equalTo(POST_ID))
                .and()
                .statusCode(STATUS_CODE_OK);
    }

    @Test
    @Title("Should return 404 code (not found status) when try to get post with non existing id")
    public void shouldReturnNotFoundStatusWhenEnterNonExistingId() {
        getPostById(NEXT_ID)
                .then()
                .statusCode(STATUS_NOT_FOUND);
    }

    @Test
    @Title("Should create new post and return 201 code (created status)")
    public void shouldReturnCreatedStatusAndCreateNewPostWithSameId() {
        createPost(NEW_POST)
                .then()
                .body(Config.ID, equalTo(NEXT_ID))
                .and()
                .statusCode(STATUS_CODE_CREATED);
    }

    @Test
    @Title("Should modify existing post and return 200 code (ok status)")
    public void shouldReturnOkStatusWhenModifyingExistingProduct() {
        modifyPost(EXISTING_POST)
                .then()
                .body(Config.ID, equalTo(POST_ID))
                .and()
                .statusCode(STATUS_CODE_OK);
    }

    @Test
    @Title("Should delete post and return 200 code (ok status)")
    public void shouldReturnOkStatusAndDeleteExistingPost() {
        deletePost(POST_ID)
                .then()
                .statusCode(STATUS_CODE_OK);
    }
}
