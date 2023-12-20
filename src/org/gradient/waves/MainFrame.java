package org.gradient.waves;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>Main Frame</h1>
 * The mainFrame is the only frame created by the program in the start.
 * <br>
 * User can navigate between the frames using BottomBar.
 * @see BottomBar
 *
 * Moreover, the main functionality of the window is to let a user choose the desired colors,
 * using RGBA format.
 * In the GradientSettings panel user can specify the colors, when entering RGBA values.
 * @see GradientSettings
 *
 * Furthermore, in the top of the frame there is a menu bar. In the given menu bar the user can
 * find the needed information about the functinality of the program and different nuances.
 * @see TopMenuBar
 *
 * When you close the given window, the program terminates.
 */
public class MainFrame extends JFrame {
    /**
     * In the MainFrame() constructor all the listed
     * components are distributed using BorderLayout.
     * @see BorderLayout
     *
     * The size of the frame is 400x320.
     */
    public MainFrame() {
        this.setJMenuBar(new TopMenuBar());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(new BottomBar(this, null), BorderLayout.SOUTH);

        GradientSettings gradientSettingsPanel = new GradientSettings();
        mainPanel.add(gradientSettingsPanel, BorderLayout.CENTER);

        this.add(mainPanel);

        this.setTitle("Gradient Waves");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 320);
        this.setResizable(false);
    }
}


