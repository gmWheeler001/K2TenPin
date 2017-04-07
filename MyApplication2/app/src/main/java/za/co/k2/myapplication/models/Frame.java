package za.co.k2.myapplication.models;

/**
 * Created by garrick.w on 2017/04/07.
 */
public class Frame {
    int rollOne;
    int rollTwo;
    int rollThree;
    boolean rollThreeEnabled = false;

    public Frame() {
        rollOne = -1;
        rollTwo = -1;
        rollThree = -1;
        rollThreeEnabled = false;
    }

    public int getRollOne() {
        return rollOne;
    }

    public int getRollTwo() {
        return rollTwo;
    }

    public int getRollThree() {
        return rollThree;
    }

    public String getFirstTryPinCountString() {
        return scoreStringCheck(getRollOne(), 1);
    }

    public String getSecondTryPinCountString() {
        return scoreStringCheck(getRollTwo(), 2);
    }

    private String scoreStringCheck(int input, int tryNumber) {
        if (input == 0) {
            return "-";
        } else if ((tryNumber == 1 && input == 10) || (isBonusEnabled() && input == 10)) {
            return "X";
        } else if ((tryNumber == 2 && input == 10) || (tryNumber == 2 && sumOfTries() == 10 && getFirstTryPinCount() != 10)) {
            return "/";
        } else if (input == -1) {
            return "";
        } else {
            return input + "";
        }
    }

    public int getFirstTryPinCount() {
        if(rollOne == -1) {
            return 0;
        }
        return rollOne;
    }

    public int getSecondTryPinCount() {
        if(rollTwo == -1) {
            return 0;
        }
        return rollTwo;
    }

    public int getThirdTryPinCount() {
        if(rollThree == -1) {
            return 0;
        }
        return rollThree;
    }

    public int sumOfTries() {
        return getFirstTryPinCount() + getSecondTryPinCount();
    }

    public void addPinsDown(int pinAmount) {
        if(rollOne == -1) {
            rollOne = pinAmount;
        } else {
            if(rollTwo == -1) {
                if (getRemainingPinCount() < pinAmount && !isBonusEnabled()) {
                    rollTwo = getRemainingPinCount();
                } else {
                    rollTwo = pinAmount;
                }
            } else {
                rollThree = pinAmount;
            }
        }
    }

    public boolean hasBowledSecondTry() {
        return rollTwo != -1;
    }

    public boolean hasBowledBonusTry() {
        return rollThree != -1;
    }

    public int getRemainingPinCount() {
        return 10 -  sumOfTries();
    }

    public void enableBonusShot() {
        rollThreeEnabled = true;
    }

    public boolean isBonusEnabled() {
        return rollThreeEnabled;
    }


}

