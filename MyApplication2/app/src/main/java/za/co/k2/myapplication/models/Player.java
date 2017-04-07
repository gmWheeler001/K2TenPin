package za.co.k2.myapplication.models;

/**
 * Created by garrick.w on 2017/04/07.
 */
public class Player {
    private String userName;
    private int scrore;
    private int maxFrames;
    private int currentFrame;

    private Frame[] frames;

    public Player(String userName, int maxframes) {
        this.userName = userName;
        this.frames = new Frame[maxframes];
        this.currentFrame = 0;
    }

//    public int getCurrentFrame() {
//        return currentFrame;
//    }

    public Frame getFrame(int index) {
        return frames[index];
    }

    public int getFrameCount() {
        return frames.length;
    }
}
