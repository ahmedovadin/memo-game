package controller;

import model.MemoGameModel;
import view.MemoGameView;

public class MemoGameController {
    private final MemoGameModel model;
    private final MemoGameView view;
    private final int[] size = new int[2];

    public MemoGameController(MemoGameModel model, MemoGameView view){
       this.model = model;
       this.view = view;
    }

    private int getUserInputFromView(String message){
        return view.receiveUserInput(message);
    }

    private void getUserBoardSizeFromView(){
        size[0] = getUserInputFromView("Enter board width: "); // board width
        size[1] = getUserInputFromView("Enter board height: "); // board height
    }

    private void serBoardSize(){
        model.initializeSize(size);
    }

    private boolean isValidBoardSize(){
        return model.validateBoardSize();
    }

    private void createBoard(){
        model.createBoard();
    }

    private void shuffleArray(){
        model.shuffleArray();
    }

    private void updateViewError(){
        view.displayErrorMessage();
    }
    private void updateView(String message){
        view.displayMessage(message);
    }

    private void updateViewArray(String [][] array){
        view.printArray(array);
    }

    private String[][] getHiddenBoard(){
        return model.getHiddenBoard();
    }

    private int getBoardWidth(){
        return model.getBoardWidth();
    }

    private int getBoardHeight(){
        return model.getBoardHeight();
    }

    private int getClosedCards() {
       return model.getClosedCards();
    }

    private int getInputFromView(){
        return view.getInput();
    }

    private void setCoordinates(int x1, int y1, int x2, int y2){
        model.setCoordinates(x1, y1, x2, y2);
    }

    private int getAttempts(){
        return model.getBoardWidth();
    }

    private void openCards(){
        model.openCards();
    }

    private void checkCoordinates(){
        model.checkCoordinates();
    }

    private boolean areCardsMatch(){
        return model.areCardsMatch();
    }
    public void start(){
        getUserBoardSizeFromView();
        serBoardSize();

        if(!isValidBoardSize()){
            updateViewError();
            return;
        }

        createBoard();
        shuffleArray();

        updateViewArray(getHiddenBoard());


        updateView("Width: " + getBoardWidth() + ", Height: " + getBoardHeight());
        updateView("Closed cards: " + getClosedCards());

        getUserCoordinates();
    }


    private void getUserCoordinates(){
        do {
            updateView("Enter the first and second card coordinates like (0 0 1 1): ");

            int x1 = getInputFromView();
            int y1 = getInputFromView();

            int x2 = getInputFromView();
            int y2 = getInputFromView();


            setCoordinates(x1, y1, x2, y2);

            if(model.areCardsOpened()){
                updateView("Cards are open, choose others");
                continue;
            }
            openCards();
            updateViewArray(getHiddenBoard());

            checkCoordinates();

            updateView(areCardsMatch() ? "Pairs matched" : "Pairs did not match");
            updateViewArray(getHiddenBoard());

            updateView("Attempts: " + getAttempts());

        } while( getClosedCards() > 0);

        updateView("You win, game ended.");
    }
}
