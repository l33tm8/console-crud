package org.consolecrud.view;

import org.consolecrud.model.PostStatus;
import org.consolecrud.model.Writer;

import java.util.Scanner;

public class WriterView extends AbstractView<Writer> {
    public String getLastName() {
        System.out.println("Enter the last name of the writer:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public int updateSelectionView() {
        System.out.println("Select which object's data you may need to have to be updated");
        System.out.println("1 - First Name");
        System.out.println("2 - Second Name");
        System.out.println("3 - status");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public String updateFirstNameView() {
        System.out.println("Enter the new First Name");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public String updateSecondNameView() {
        System.out.println("Enter the new Last Name");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

}
