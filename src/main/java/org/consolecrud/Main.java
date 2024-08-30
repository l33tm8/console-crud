package org.consolecrud;

import org.consolecrud.repository.*;
import org.consolecrud.view.*;
import org.consolecrud.controller.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WriterRepository writerRepository = new GsonWriterRepositoryImpl();
        LabelRepository labelRepository = new GsonLabelRepositoryImpl();
        PostRepository postRepository = new GsonPostRepositoryImpl();

        WriterView writerView = new WriterView();
        LabelView labelView = new LabelView();
        PostView postView = new PostView();

        WriterController writerController = new WriterController(writerView, writerRepository);
        LabelController labelController = new LabelController(labelView, labelRepository);
        PostController postController = new PostController(postView, postRepository);
        List<Controller> controllers = List.of(writerController, labelController, postController);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - make changes in writers.json");
            System.out.println("2 - make changes in labels.json");
            System.out.println("3 - make changes in posts.json");
            System.out.println("4 - exit");
            System.out.println("Select an option: ");
            int tableOption = scanner.nextInt();
            if (tableOption == 4) {
                break;
            }
            Controller controller = controllers.get(tableOption - 1);
            System.out.println("1 - create new model");
            System.out.println("2 - update model");
            System.out.println("3 - delete model");
            System.out.println("4 - list models");
            System.out.println("5 - get model by id");
            System.out.println("Select an option: ");
            int selectedOption = scanner.nextInt();
            if (selectedOption == 5) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                controller.updateModel(id);
            }
            else if (selectedOption == 4) {
                controller.getAllModels();
            }
            else if (selectedOption == 3) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                controller.deleteModel(id);
            }
            else if (selectedOption == 2) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                controller.updateModel(id);
            }
            else if (selectedOption == 1) {
                controller.createModel();
            }
        }
    }
}