package org.consolecrud.controller;

import org.consolecrud.model.Writer;
import org.consolecrud.repository.WriterRepository;
import org.consolecrud.view.WriterView;

import java.util.List;

public class WriterController implements Controller {
    WriterView view;
    WriterRepository repository;

    public WriterController(WriterView view, WriterRepository repository)
    {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getModelById(Integer id) {
        Writer model = repository.getById(id);
        view.printModel(model);
    }

    @Override
    public void getAllModels() {
        List<Writer> models = repository.getAll();
        view.printAllModels(models);
    }

    @Override
    public void createModel() {
        String firstName = view.getName();
        String lastName = view.getLastName();
        int id = repository.getAll().size();
        Writer writer = new Writer();
        writer.setId(id);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        repository.save(writer);
        view.save(id);

    }

    @Override
    public void updateModel(Integer id) {
        Writer model = repository.getById(id);
        view.printModel(model);
        int selection = view.updateSelectionView();
        switch (selection)
        {
            case 1: {
                model.setFirstName(view.updateFirstNameView());
                break;
            }
            case 2: {
                model.setLastName(view.updateSecondNameView());
                break;
            }

            case 3: {
                model.setStatus(view.modelUpdateStatusView());
                break;
            }
        }
        repository.update(model);
        view.modelUpdateView(id);
    }

    @Override
    public void deleteModel(Integer id) {
        repository.deleteById(id);
        view.modelDeleteView(id);
    }
}
