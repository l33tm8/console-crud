package org.consolecrud.controller;

import org.consolecrud.model.Label;
import org.consolecrud.model.PostStatus;
import org.consolecrud.repository.LabelRepository;
import org.consolecrud.view.LabelView;

import java.util.List;

public class LabelController implements Controller {

    LabelView view;
    LabelRepository repository;

    public  LabelController(LabelView view, LabelRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getModelById(Integer id) {
        Label model = repository.getById(id);
        view.printModel(model);
    }

    @Override
    public void getAllModels() {
        List<Label> models = repository.getAll();
        view.printAllModels(models);
    }

    @Override
    public void createModel() {
        Label model = new Label();
        String name = view.getName();
        PostStatus status = PostStatus.ACTIVE;
        model.setStatus(status);
        model.setName(name);
        repository.save(model);
    }

    @Override
    public void updateModel(Integer id) {
        int selection = view.updateSelectionView();
        Label model = repository.getById(id);
        switch (selection)
        {
            case 1: {
                String updatedName = view.getName();
                model.setName(updatedName);
                break;
            }
            case 2: {
                PostStatus updatedStatus = view.modelUpdateStatusView();
                model.setStatus(updatedStatus);
                break;
            }
        }
        repository.update(model);
    }

    @Override
    public void deleteModel(Integer id) {
        repository.deleteById(id);
        view.modelDeleteView(id);
    }
}
