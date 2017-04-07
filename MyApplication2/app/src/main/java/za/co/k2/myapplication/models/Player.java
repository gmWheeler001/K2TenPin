package za.co.k2.myapplication.models;

/**
 * Created by garrick.w on 2017/04/07.
 */
public class Player {
    private int currentFrame;
    private Frame[] frames;

    public Player(int maxframes) {
        this.frames = new Frame[maxframes];
        for (int i = 0; i < maxframes; i++) {
            this.frames[i] = new Frame();
        }

        this.currentFrame = 0;
    }

    public int getCurrentFrameIndex() {
        return currentFrame;
    }

    public Frame getCurrentFrame() {
        return frames[currentFrame];
    }

    public Frame getFrame(int index) {
        return frames[index];
    }

    public int getFrameCount() {
        return frames.length;
    }

    public void moveToNextFrame() {
        currentFrame++;
    }
}
