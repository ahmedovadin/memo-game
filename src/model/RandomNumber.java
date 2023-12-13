package model;

public class RandomNumber {
    public int generateRandomNumber(int previous, int count){
        if(count == 2 && previous > -1){
            return previous;
        } else {
            return getNumber() ;
        }
    }

    public int getNumber(){
        return (int) (Math.random() * 9);
    }
}
