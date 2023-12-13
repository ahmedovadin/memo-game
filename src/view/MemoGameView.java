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
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
