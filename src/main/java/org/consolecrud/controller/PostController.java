package org.consolecrud.controller;

import org.consolecrud.model.Post;
import org.consolecrud.model.PostStatus;
import org.consolecrud.repository.PostRepository;
import org.consolecrud.view.PostView;

import java.util.Date;
import java.util.List;

public class PostController implements Controller {

    PostView view;
    PostRepository repository;

    public PostController (PostView view, PostRepository repository) {
        this.view = view;
        this.repository = repository;
    }
    @Override
    public void getModelById(Integer id) {
        Post model = repository.getById(id);
        view.printModel(model);
    }

    @Override
    public void getAllModels() {
        List<Post> models = repository.getAll();
        view.printAllModels(models);
    }

    @Override
    public void createModel() {
        String content = view.getContent();
        Date created = new Date();
        PostStatus status = view.modelUpdateStatusView();
        Post model = new Post();
        model.setCreated(created);
        model.setUpdated(created);
        model.setStatus(status);
        model.setContent(content);
        repository.save(model);
        view.save(model.getId());
    }

    @Override
    public void updateModel(Integer id) {
        Post model = repository.getById(id);
        int selection = view.getUpdateSelection();
        switch (selection)
        {
            case 1:
                model.setContent(view.getContent());
                break;
            case 2:
                model.setStatus(view.modelUpdateStatusView());
                break;
        }
    }

    @Override
    public void deleteModel(Integer id) {
        repository.deleteById(id);
        view.modelDeleteView(id);
    }
}
