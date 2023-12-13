package controller;

import model.MemoGameModel;
import view.MemoGameView;

public class MemoGameController {
    private final MemoGameModel model;
    private final MemoGameView view;

    public MemoGameController(MemoGameModel model, MemoGameView view){
       this.model = model;
       this.view = view;
   }
    public void start(){
        int[] size = getUserBoardSize();
        model.initializeSize(size);

        if(!model.validateBoardSize()){
            view.displayErrorMessage();
            return;
        }

        model.createBoard();
        model.shuffleArray();
        view.printArray(model.getHiddenBoard());


        view.displayMessage("Width: " + model.getBoardWidth() + ", Height: " + model.getBoardHeight());
        view.displayMessage("Closed cards: " + model.getClosedCards());

        getUserCoordinates();
    }

    public int[] getUserBoardSize(){
        int[] size = new int[2];

        size[0] = view.receiveUserInput("Enter board width: "); // board width
        size[1] = view.receiveUserInput("Enter board height: "); // board height

        return size;
    }

    private void getUserCoordinates(){
        do {
            view.displayMessage("Enter the first and second card coordinates like (0 0 1 1): ");

            int x1 = view.getInput();
            int y1 = view.getInput();

            int x2 = view.getInput();
            int y2 = view.getInput();


            model.setCoordinates(x1, y1, x2, y2);

            if(model.areCardsOpened()){
                view.displayMessage("Cards are open, choose others");
                continue;
            }
            model.openCards();
            view.printArray(model.getHiddenBoard());

            model.checkCoordinates();

            view.displayMessage(model.areCardsMatch() ? "Pairs matched" : "Pairs did not match");
            view.printArray(model.getHiddenBoard());

            view.displayMessage("Attempts: " + model.getAttempts());
        } while( model.getClosedCards() > 0);

        view.displayMessage("You win, game ended.");
    }
}
