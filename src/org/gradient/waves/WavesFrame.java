package org.gradient.waves;

import javax.swing.*;

/**
 * <h1>Waves Frame</h1>
 * WavesFrame is a class, extending the JFrame class, which renders
 * the gradient waves corresponding to the loaded in GradientSettings frame
 * settings.
 * @see JFrame
 * @see GradientSettings
 *
 * The custom graphics rendered in the window are defined in WavesPanel class.
 * @see WavesPanel
 */
public class WavesFrame extends JFrame {
    public WavesFrame(int width, int height, int red, int green, int blue, int alpha) {
        this.add(new WavesPanel(width, height, red, green, blue, alpha));

        this.setTitle("Gradient Waves");
        this.setResizable(false);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
