package org.consolecrud.view;

import org.consolecrud.model.Label;

import java.util.Scanner;

public class LabelView extends AbstractView<Label>{
    public int updateSelectionView()
    {
        System.out.println("Which type of data you want to update?");
        System.out.println("1 - name");
        System.out.println("2 - status");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}
