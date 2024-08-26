package org.consolecrud.controller;

public interface Controller  {
    void getModelById(Integer id);
    void getAllModels();
    void createModel();
    void updateModel(Integer id);
    void deleteModel(Integer id);
}
