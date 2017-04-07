package za.co.k2.myapplication.models;

/**
 * Created by garrick.w on 2017/04/07.
 */
public class Frame {
    int rollOne;
    int rollTwo;

    public Frame() {
        rollOne = 0;
        rollTwo = 0;
    }

    public int getFirstTryPinCount() {
        return rollOne;
    }

    public int getSecondTryPinCount() {
        return rollTwo;
    }

    public int sumOfTries() {
        return rollOne + rollTwo;
    }
}
