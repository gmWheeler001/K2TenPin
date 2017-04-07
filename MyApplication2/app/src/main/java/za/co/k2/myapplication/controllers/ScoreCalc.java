package za.co.k2.myapplication.controllers;

import za.co.k2.myapplication.models.Frame;
import za.co.k2.myapplication.models.Player;

/**
 * Created by garrick.w on 2017/04/07.
 * does all the work calculating score according to rules found online.
 */
public class ScoreCalc {

    public int getPlayerTotalScore(Player player) {
        int score = 0;

        for (int frameIndex = 0; frameIndex < player.getFrameCount(); frameIndex++) {
            score += getScoreForFrame(player, frameIndex);
        }

        return score;
    }

    public int getScoreForFrame(Player player, int frameIndex) {
        Frame currentFrame = player.getFrame(frameIndex);
        if(frameIndex == 9) {
            if(isStrike(currentFrame)) {
                return 10 + currentFrame.getSecondTryPinCount() + currentFrame.getThirdTryPinCount();
            } else if (isSpare(currentFrame)) {
                return 10 + currentFrame.getThirdTryPinCount();
            } else {
                return currentFrame.sumOfTries();
            }
        }
        if(isStrike(currentFrame)) {
            return 10 + strikeBonus(player, frameIndex);
        } else if(isSpare(currentFrame)) {
            return 10 + spareBonus(player, frameIndex);
        } else {
            return currentFrame.sumOfTries();
        }
    }

    private boolean isStrike(Frame frame) {
        return frame.getFirstTryPinCount() == 10;
    }

    private boolean isSpare(Frame frame) {
        return frame.sumOfTries() == 10;
    }

    private int strikeBonus(Player player, int frameIndex) {
        if(frameIndex + 1 < player.getFrameCount()) {
            if(player.getFrame(frameIndex + 1).getFirstTryPinCount() == 10 && frameIndex + 2 < player.getFrameCount()) {// strike again on next ball
                return 10 + player.getFrame(frameIndex + 2).getFirstTryPinCount();
            } else {
                return player.getFrame(frameIndex + 1).sumOfTries();
            }
        } else {
            return 0;
        }
    }

    private int spareBonus(Player player, int frameIndex) {
        if(frameIndex + 1 < player.getFrameCount()) {
            return player.getFrame(frameIndex + 1).getFirstTryPinCount();
        } else {
            return 0;
        }
    }
}
