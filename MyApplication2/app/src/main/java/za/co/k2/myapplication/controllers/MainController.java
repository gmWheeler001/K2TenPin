package za.co.k2.myapplication.controllers;

import java.util.concurrent.ThreadLocalRandom;

import za.co.k2.myapplication.MainActivity;
import za.co.k2.myapplication.models.Frame;
import za.co.k2.myapplication.models.Player;

/**
 * Created by garrick.w on 2017/04/07.
 * Application used to play bowling.. virtually
 */
public class MainController {
    private final int MAX_FRAMES = 10;
    private Player mCurrentPlayer;
    private MainActivity mMainActivity;
    private ScoreCalc mScoreCalc;

    public MainController(MainActivity mainActivity) {
        mCurrentPlayer = new Player(MAX_FRAMES);
        mScoreCalc = new ScoreCalc();
        this.mMainActivity = mainActivity;
    }

    public void ballWasBowled() {
        bowledPinsDown(getPinsDownAmount(mCurrentPlayer.getCurrentFrame()));
    }

    // used for random generation to be replace if better user interaction is added to the application
    private int getPinsDownAmount(Frame currentFrame) {
        return ThreadLocalRandom.current().nextInt(0, currentFrame.getRemainingPinCount() + 1);
    }

    private void updateScoreCard() {
        mMainActivity.updateTotalScore(mScoreCalc.getPlayerTotalScore(mCurrentPlayer));

        for (int i = 0; i < MAX_FRAMES; i++) {
            Frame workingFrame = mCurrentPlayer.getFrame(i);
            if(workingFrame.getRollOne() != -1) {
                mMainActivity.setFrameAndTry(workingFrame.getFirstTryPinCountString(), i + 1, 1);

                if(workingFrame.getRollOne() != -1) {
                    mMainActivity.setFrameAndTry(workingFrame.getSecondTryPinCountString(), i + 1, 2);

                    if(workingFrame.getRollThree() != -1) {
                        mMainActivity.setFrameAndTry(workingFrame.getThirdTryPinCountString(), i + 1, 3);
                    }
                }
            }

            if(workingFrame.getRollOne() != -1) {
                int frameScore = mScoreCalc.getScoreForFrame(mCurrentPlayer, i);
                mMainActivity.setFrameTotal(frameScore + "", i + 1);
            }
        }
    }

    public void resetClicked() {
        mCurrentPlayer = new Player(MAX_FRAMES);
        updateScoreCard();

        for (int i = 0; i < MAX_FRAMES; i++) {
            mMainActivity.setFrameAndTry("", i + 1, 1);
            mMainActivity.setFrameAndTry("", i + 1, 2);
            if(i == MAX_FRAMES - 1) {
                mMainActivity.setFrameAndTry("", i + 1, 3);
            }
            mMainActivity.setFrameTotal("", i + 1);
        }
    }

    public void bowledPinsDown(int pins) {
        Frame workingFrame = mCurrentPlayer.getCurrentFrame();
        workingFrame.addPinsDown(pins);
        // final frame
        if(mCurrentPlayer.getCurrentFrameIndex() == MAX_FRAMES - 1 ) {
            if((!workingFrame.hasBowledSecondTry() && pins == 10) ||
                    (workingFrame.hasBowledSecondTry() && workingFrame.getFirstTryPinCount() + pins >= 10)) {
                workingFrame.enableBonusShot();
            }

            if((workingFrame.hasBowledSecondTry() && !workingFrame.isBonusEnabled()) ||
                    (workingFrame.hasBowledSecondTry() && workingFrame.hasBowledBonusTry())) {
                mMainActivity.showScore(mScoreCalc.getPlayerTotalScore(mCurrentPlayer));
            }
        } else {
            // most frames
            if(workingFrame.hasBowledSecondTry() || pins == 10) {
                mCurrentPlayer.moveToNextFrame();
            }

        }
        updateScoreCard();
    }
}
