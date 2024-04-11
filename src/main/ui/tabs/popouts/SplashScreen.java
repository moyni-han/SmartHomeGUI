package ui.tabs.popouts;

import javax.swing.*;
import java.awt.*;

public class SplashScreen {
    private final JWindow window;
    private long startTime;
    private int minimumMilliseconds;

    public SplashScreen() {
        window = new JWindow();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var image = new ImageIcon("src/main/ui/tabs/images/smarthome.png");
        Image imageImage = image.getImage(); // transform it
        Image newimg = imageImage.getScaledInstance((int) (screenSize.getWidth() / 4),
                (int) (screenSize.getWidth() / 4),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        image = new ImageIcon(newimg);  // transform it back
        window.getContentPane().add(new JLabel("", image, SwingConstants.CENTER));
        window.setBounds((int) ((screenSize.getWidth() - image.getIconWidth()) / 2),
                (int) ((screenSize.getHeight() - image.getIconHeight()) / 2),
                (image.getIconWidth()), (image.getIconHeight()));
    }

    public void show(int minimumMilliseconds) {
        this.minimumMilliseconds = minimumMilliseconds;
        window.setVisible(true);
        startTime = System.currentTimeMillis();
    }

    public void hide() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        try {
            Thread.sleep(Math.max(minimumMilliseconds - elapsedTime, 0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
    }
}