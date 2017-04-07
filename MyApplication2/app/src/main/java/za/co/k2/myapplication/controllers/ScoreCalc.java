package za.co.k2.myapplication.controllers;

import za.co.k2.myapplication.models.Frame;
import za.co.k2.myapplication.models.Player;

/**
 * Created by garrick.w on 2017/04/07.
 */
public class ScoreCalc {

    public int getPlayerTotalScore(Player player) {
        int score = 0;

        for (int frameIndex = 0; frameIndex < player.getFrameCount(); frameIndex++) {
            Frame currentFrame = player.getFrame(frameIndex);
            if(isStrike(currentFrame)) {
                score += 10 + strikeBonus(player, frameIndex);
            } else if(isSpare(currentFrame)) {
                score += 10 + spareBonus(player, frameIndex);
            } else {
                score += player.getFrame(frameIndex).sumOfTries();
            }
        }

        return score;
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
