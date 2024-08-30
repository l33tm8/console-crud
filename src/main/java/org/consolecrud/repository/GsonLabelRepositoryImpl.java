package org.consolecrud.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.consolecrud.model.Label;
import org.consolecrud.model.PostStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {
    @Override
    public Label getById(Integer id) {
        List<Label> labels = getAll();
        for (var label : labels) {
            if (label.getId() == id)
                return label;
        }
        return null;
    }

    @Override
    public List<Label> getAll() {
        try {
            String jsonContent = Files.readString(Path.of("src/main/resources/labels.json"));
            Type targetClassType = new TypeToken<ArrayList<Label>>() {}.getType();
            Collection<Label> labels = new Gson().fromJson(jsonContent, targetClassType);
            return (List<Label>) labels;
        }   catch (IOException e)
        {
            System.out.println("Can't read from labels file");
            return null;
        }
    }

    @Override
    public Label save(Label label) {
        List<Label> labels = getAll();
        if (labels != null) {
            int id = labels.size();
            label.setId(id);
            labels.add(label);
        }
        else {
            label.setId(0);
            labels = List.of(label);
        }
        saveToFile(labels);
        return label;
    }

    @Override
    public Label update(Label label) {
        List<Label> labels = getAll();
        for (int i = 0; i < labels.size(); i++)
        {
            if (labels.get(i).getId() == label.getId())
            {
                labels.set(i, label);
            }
        }
        saveToFile(labels);
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> labels = getAll();
        for (var label : labels) {
            if (label.getId() == id)
                label.setStatus(PostStatus.DELETED);
        }
        saveToFile(labels);
    }

    private void saveToFile(List<Label> labels) {
        String jsonList = new Gson().toJson(labels);
        try {
            Files.writeString(Path.of("src/main/resources/labels.json"), jsonList);
        } catch (IOException e) {
            System.out.println("Can't write to labels file");
        }
    }
}
