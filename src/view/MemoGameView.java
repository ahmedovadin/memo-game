package view;

import java.util.Scanner;

public class MemoGameView {
    private final Scanner inner = new Scanner(System.in);
    public void displayErrorMessage(){
        System.out.println("The deck should consist of pairs. Exit.");
    }

    public void displayMessage(String message){
        System.out.println(message);
    }

    public int receiveUserInput(String message){
        System.out.print(message);
        return getInput();
    }

    public int getInput(){
        return inner.nextInt();
    }

    public void printArray(String [][] arr){
        for (String[] strings : arr) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
}
