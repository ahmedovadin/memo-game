package model;

import java.util.Random;

public class MemoGameModel {
    int[][] board;

    private int count;
    private int randomNumber;

    private int boardWidth;
    private int boardHeight;
    private int closedCards;

    private int attempts = 0;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    String[][] hiddenBoard;
    private final RandomNumber random = new RandomNumber();

    public void initializeSize(int[] size){
        boardWidth = size[0];
        boardHeight = size[1];
        setClosedCards(boardWidth * boardHeight);
    }

    public void createBoard(){
        initializeBoard();
        initializeHiddenBoard();
    }

    public void initializeBoard(){
        board = new int[boardHeight][boardWidth];

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                count = count > 1 ? 1 : count+1;
                randomNumber = random.generateRandomNumber(randomNumber, count);
                board[i][j] = randomNumber;
            }
        }
    }

    public void initializeHiddenBoard(){
        hiddenBoard = new String[boardHeight][boardWidth];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                hiddenBoard[i][j] = "x";
            }
        }
    }

    public boolean validateBoardSize(){
        return (boardWidth * boardHeight) % 2 == 0;
    }

    public int getClosedCards() {
        return closedCards;
    }

    public void setClosedCards(int closedCards) {
        this.closedCards = closedCards;
    }

    public void shuffleArray(){
        Random random = new Random();
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                int temp = board[i][j];
                board[i][j] = board[m][n];
                board[m][n] = temp;
            }
        }
    }

    public boolean areCardsOpened(){
        return (hiddenBoard[x1][y1] != "x" || hiddenBoard[x2][y2] != "x");
    }

    public void openCards(){
        hiddenBoard[x1][y1] = String.valueOf(board[x1][y1]);
        hiddenBoard[x2][y2] = String.valueOf(board[x2][y2]);
    }

    public void hideCards(){
        hiddenBoard[x1][y1] = "x";
        hiddenBoard[x2][y2] = "x";
    }

    public void setCoordinates(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public boolean areCardsMatch(){
        return (board[x1][y1] == board[x2][y2]);
    }


    public void checkCoordinates(){
        if(areCardsMatch()){
            setClosedCards(getClosedCards()-2);
            attempts++;
        } else {
            hideCards();
        }
    }

    public int getAttempts() {
        return attempts;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public String[][] getHiddenBoard() {
        return hiddenBoard;
    }
}
