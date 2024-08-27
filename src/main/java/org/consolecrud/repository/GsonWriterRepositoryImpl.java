package org.consolecrud.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.consolecrud.model.PostStatus;
import org.consolecrud.model.Writer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GsonWriterRepositoryImpl implements WriterRepository {
    @Override
    public Writer getById(Integer id) {
        for (var post : getAll()) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public List<Writer> getAll() {
        try {
            String jsonContent = Files.readString(Path.of("src/main/resources/writers.json"));
            Type targetClassType = new TypeToken<ArrayList<Writer>>() {}.getType();
            return new Gson().fromJson(jsonContent, targetClassType);
        } catch (IOException e)
        {
            System.out.println("cannot read writers.json");
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public Writer save(Writer writer) {
        List<Writer> writers = getAll();
        writer.setId(writers.size());
        writers.add(writer);
        saveToFile(writers);
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> writers = getAll();
        writers.set(writers.indexOf(writer), writer);
        saveToFile(writers);
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> writers = getAll();
        for (var writer : writers) {
            if (writer.getId() == id) {
                writer.setStatus(PostStatus.DELETED);
            }
        }
        saveToFile(writers);
    }

    private void saveToFile(List<Writer> writers) {
        String jsonList = new Gson().toJson(writers);
        try {
            Files.writeString(Path.of("src/main/resources/writers.json"), jsonList);
        } catch (IOException e) {
            System.out.println("Can't write to posts file");
        }
    }
}
