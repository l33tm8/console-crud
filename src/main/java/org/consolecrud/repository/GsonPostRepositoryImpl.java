package org.consolecrud.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.consolecrud.model.Post;
import org.consolecrud.model.PostStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GsonPostRepositoryImpl  implements PostRepository{
    @Override
    public Post getById(Integer id) {
        for (Post post : getAll()) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public List<Post> getAll() {
        try {
            String jsonContent = Files.readString(Path.of("src/main/resources/posts.json"));
            Type targetClassType = new TypeToken<ArrayList<Post>>() {}.getType();
            return new Gson().fromJson(jsonContent, targetClassType);
        } catch (IOException e)
        {
            System.out.println("cannot read posts.json");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Post save(Post post) {
        List<Post> posts = getAll();
        post.setId(posts.size());
        posts.add(post);
        saveToFile(posts);
        return post;
    }

    @Override
    public Post update(Post post) {
        List<Post> posts = getAll();
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == post.getId()) {
                posts.set(i, post);
            }
        }
        saveToFile(posts);
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        List<Post> posts = getAll();
        for (var post : posts) {
            post.setStatus(PostStatus.DELETED);
        }
        saveToFile(posts);
    }

    private void saveToFile(List<Post> posts) {
        String jsonList = new Gson().toJson(posts);
        try {
            Files.writeString(Path.of("src/main/resources/posts.json"), jsonList);
        } catch (IOException e) {
            System.out.println("Can't write to posts file");
        }
    }
}
