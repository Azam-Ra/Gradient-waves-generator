package org.gradient.waves;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>Saved Frame</h1>
 * The SavedFrame is the class which extends JFrame class,
 * where the user can find all of the saved settings.
 * @see JFrame
 * <br>
 * The rendering of the settings is defined in the SavedHistoryPanel class.
 * @see SavedHistoryPanel
 *
 */
public class SavedFrame extends JFrame{
    public SavedFrame(MainFrame mainFrame) {
        this.setJMenuBar(new TopMenuBar());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(new SavedHistoryPanel(), BorderLayout.CENTER);
        mainPanel.add(new BottomBar(mainFrame, this), BorderLayout.SOUTH);

        this.add(mainPanel);

        this.setTitle("Gradient Waves");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 320);
        this.setResizable(false);
    }
}
