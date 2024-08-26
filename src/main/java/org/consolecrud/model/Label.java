package org.consolecrud.model;

public class Label {
    int id;
    String name;
    PostStatus status;
    public Label (int id, String name, PostStatus status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    public Label() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }
}
