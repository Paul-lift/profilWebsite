package ch.paul.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomStatusBar {
    private int x, y;
    private int width, height;
    private int currentValue, maxValue;

    private BufferedImage leftImage;
    private BufferedImage midImage;
    private BufferedImage midEmptyImage;
    private BufferedImage rightImage;

    private BufferedImage leftImageScaled;
    private BufferedImage midImageScaled;
    private BufferedImage midEmptyImageScaled;
    private BufferedImage rightImageScaled;


    public CustomStatusBar(int x, int y, int width, int height, int currentValue, int maxValue, BufferedImage leftImage, BufferedImage midImage,
                           BufferedImage midEmptyImage, BufferedImage rightImage) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentValue = currentValue;
        this.maxValue = maxValue;

        this.leftImage = leftImage;
        this.midImage = midImage;
        this.midEmptyImage = midEmptyImage;
        this.rightImage = rightImage;

        this.leftImageScaled = scaleImage(leftImage, height);
        this.midImageScaled = scaleImage(midImage, height);
        this.midEmptyImageScaled = scaleImage(midEmptyImage, height);
        this.rightImageScaled = scaleImage(rightImage, height);
    }

    public void render(Graphics g) {
        int currentX = x;

        g.drawImage(leftImageScaled, currentX, y, leftImageScaled.getWidth(), height, null);
        currentX += leftImageScaled.getWidth();


        int midWidth = midImageScaled.getWidth(); //how many MID pieces

        int fillableWidth = width - leftImageScaled.getWidth() - rightImageScaled.getWidth();   //actual width (without left and right part)
        int totalMidCount = fillableWidth / midWidth;   //how many mid parts have space

        //how many should be filled
        float fillRatio = (float) currentValue / maxValue;
        int filledCount = Math.round(totalMidCount * fillRatio);

        //draws filled midParts
        for (int i = 0; i < filledCount; i++) {
            g.drawImage(midImageScaled, currentX, y, midWidth, height, null);
            currentX += midWidth;
        }

        //draws empty mid parts
        int emptyCount = totalMidCount - filledCount;
        for (int i = 0; i < emptyCount; i++) {
            g.drawImage(midEmptyImageScaled, currentX, y, midWidth, height, null);
            currentX += midWidth;
        }

        g.drawImage(rightImageScaled, currentX, y, rightImageScaled.getWidth(), height, null);


    }

    public int getValue() {
        return currentValue;
    }

    public void increase(int amount) {
        currentValue = Math.min(currentValue + amount, maxValue);
    }

    public void decrease(int amount) {
        currentValue = Math.max(currentValue - amount, 0);
    }

    //GPT generated, scales image right proportional(height)
    private BufferedImage scaleImage(BufferedImage original, int targetHeight) {
        int targetWidth = (int) ((double) targetHeight / original.getHeight() * original.getWidth());
        Image scaled = original.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage buffered = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffered.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        g2d.dispose();
        return buffered;
    }


}
