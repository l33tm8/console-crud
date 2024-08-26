package org.consolecrud.view;

import org.consolecrud.model.Post;

import java.util.Scanner;

public class PostView  extends AbstractView<Post> {
    public String getContent()
    {
        System.out.println("Write the content of the post");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public int getUpdateSelection()
    {
        System.out.println("Select which column do you need to update");
        System.out.println("1 - content");
        System.out.println("2 - status");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}
