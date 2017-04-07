package za.co.k2.myapplication.controllers;

import java.util.concurrent.ThreadLocalRandom;

import za.co.k2.myapplication.models.Frame;
import za.co.k2.myapplication.models.Player;

/**
 * Created by garrick.w on 2017/04/07.
 */
public class MainController {
    private final int MAX_FRAMES = 10;
    private Player mCurrentPlayer;

    public MainController() {
        mCurrentPlayer = new Player(MAX_FRAMES);
    }

    public void ballWasBowled() {
        mCurrentPlayer.getCurrentFrame().addPinsDown(getPinsDownAmount(mCurrentPlayer.getCurrentFrame()));

        if(mCurrentPlayer.getCurrentFrame().hasBowledSecondTry()) {
            mCurrentPlayer.moveToNextFrame();
        }
    }

    private int getPinsDownAmount(Frame currentFrame) {
        return ThreadLocalRandom.current().nextInt(0, currentFrame.getRemainingPinCount() + 1);
    }

    public void resetClicked() {
        mCurrentPlayer = new Player(MAX_FRAMES);
        // TODO clearValues from ScoreCard
    }
}
