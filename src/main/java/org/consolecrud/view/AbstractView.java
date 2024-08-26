package org.consolecrud.view;

import org.consolecrud.model.PostStatus;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractView<T> {
    public String getName() {
        System.out.println("Enter the name of the new model:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void save(Integer id)
    {
        System.out.println("object saved with id: " + id.toString());
    }

    public void printModel(T model)
    {
        System.out.println(model.toString());
    }

    public void printAllModels(List<T> models) {
        for (var model : models)
        {
            System.out.println(model.toString());
        }
    }

    public void modelUpdateView(Integer id)
    {
        System.out.println("object updated with id: " + id);
    }

    public void modelDeleteView(Integer id)
    {
        System.out.println("object deleted with id: " + id);
    }

    public PostStatus modelUpdateStatusView()
    {
        System.out.println("Select new status for the object:");
        System.out.print("0 - ACTIVE");
        System.out.println("1 - UNDER_REVIEW");
        System.out.println("2 - DELETED");
        Scanner in = new Scanner(System.in);
        return PostStatus.values()[in.nextInt()];
    }
}
