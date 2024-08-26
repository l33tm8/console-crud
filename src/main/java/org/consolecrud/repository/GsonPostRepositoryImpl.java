package org.consolecrud.repository;

import org.consolecrud.model.Post;

import java.util.List;

public class GsonPostRepositoryImpl  implements PostRepository{
    @Override
    public Post getById(Integer integer) {
        return null;
    }

    @Override
    public List<Post> getAll() {
        return List.of();
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
