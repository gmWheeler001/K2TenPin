package za.co.k2.myapplication.controllers;

import android.app.Activity;
import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

import za.co.k2.myapplication.MainActivity;
import za.co.k2.myapplication.models.Frame;
import za.co.k2.myapplication.models.Player;

/**
 * Created by garrick.w on 2017/04/07.
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

    private int getPinsDownAmount(Frame currentFrame) {
        return ThreadLocalRandom.current().nextInt(0, currentFrame.getRemainingPinCount() + 1);
    }

    private void updateScoreCard() {
        mMainActivity.updateTotalScore(mScoreCalc.getPlayerTotalScore(mCurrentPlayer));

        for (int i = 0; i < MAX_FRAMES; i++) {
            if(mCurrentPlayer.getFrame(i).getRollOne() != -1) {
                mMainActivity.setFrameAndTry(mCurrentPlayer.getFrame(i).getFirstTryPinCountString(), i + 1, 1);

                if(mCurrentPlayer.getFrame(i).getRollOne() != -1) {
                    mMainActivity.setFrameAndTry(mCurrentPlayer.getFrame(i).getSecondTryPinCountString(), i + 1, 2);

                    if(mCurrentPlayer.getFrame(i).getRollThree() != -1) {
                        mMainActivity.setFrameAndTry(mCurrentPlayer.getFrame(i).getSecondTryPinCountString(), i + 1, 3);
                    }
                }
            }

            if(mCurrentPlayer.getFrame(i).getRollOne() != -1) {
                int frameScore = mScoreCalc.getScoreForFrame(mCurrentPlayer, i);
                mMainActivity.setFrameTotal(frameScore + "", i + 1);
            }
        }
    }

    public void resetClicked() {
        mCurrentPlayer = new Player(MAX_FRAMES);
        updateScoreCard();

        for (int i = 0; i < MAX_FRAMES; i++) {
            mMainActivity.setFrameAndTry(mCurrentPlayer.getFrame(i).getFirstTryPinCountString(), i + 1, 1);
            mMainActivity.setFrameAndTry(mCurrentPlayer.getFrame(i).getSecondTryPinCountString(), i + 1, 2);
            if(i == MAX_FRAMES - 1) {
                mMainActivity.setFrameAndTry(mCurrentPlayer.getFrame(i).getSecondTryPinCountString(), i + 1, 3);
            }
            mMainActivity.setFrameTotal("", i + 1);
        }
    }

    public void bowledPinsDown(int pins) {
        mCurrentPlayer.getCurrentFrame().addPinsDown(pins); // TODO math in here
        // final frame
        if(mCurrentPlayer.getCurrentFrameIndex() == MAX_FRAMES - 1 ) {
            if((!mCurrentPlayer.getCurrentFrame().hasBowledSecondTry() && pins == 10) ||
                    (mCurrentPlayer.getCurrentFrame().hasBowledSecondTry() && mCurrentPlayer.getCurrentFrame().getFirstTryPinCount() + pins == 10)) {
                mCurrentPlayer.getCurrentFrame().enableBonusShot();
            }

            if((mCurrentPlayer.getCurrentFrame().hasBowledSecondTry() && !mCurrentPlayer.getCurrentFrame().isBonusEnabled()) ||
                    (mCurrentPlayer.getCurrentFrame().hasBowledSecondTry() && mCurrentPlayer.getCurrentFrame().hasBowledBonusTry())) {
                mMainActivity.showScore(mScoreCalc.getPlayerTotalScore(mCurrentPlayer));
            }
        } else {
            // most frames
            if(mCurrentPlayer.getCurrentFrame().hasBowledSecondTry() || pins == 10) {
                mCurrentPlayer.moveToNextFrame();
            }

        }
        updateScoreCard();
    }
}
