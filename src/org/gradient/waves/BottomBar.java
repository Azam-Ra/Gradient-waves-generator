package org.gradient.waves;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>Bottom Bar</h1>
 * BottomBar component is defined for the navigation between to main frames:
 * MainFrame and SavedFrame.
 * @see MainFrame
 * @see SavedFrame
 */
public class BottomBar extends JPanel {
    private static MainFrame mainFrame;
    private static SavedFrame savedFrame;

    /**
     * Using GridLayout I have tried to reach the bar effect.
     * So the buttons cover the whole available space.
     * @param mainFrame
     * @param savedFrame
     */
    public BottomBar(MainFrame mainFrame, SavedFrame savedFrame) {
        BottomBar.mainFrame = mainFrame;
        BottomBar.savedFrame = savedFrame;

        JButton mainButton = new JButton("Main");
        JButton savedButton = new JButton("Saved");

        mainButton.addActionListener(new MainListener());
        savedButton.addActionListener(new SavedListener());

        this.setLayout(new GridLayout(1,2,0,0));
        this.add(mainButton);
        this.add(savedButton);
    }


    static class MainListener implements ActionListener {
        /**
         * The actionPerformed() method of the Main button listener classes
         * navigates the user to the MainFrame.
         * Notice, that we process the click of the button only if the user
         * is calling the function inside the SavedFrame.
         *
         * @param ev the event to be processed
         */
        public void actionPerformed(ActionEvent ev) {
            if (savedFrame != null) {
                mainFrame.setVisible(true);
                savedFrame.dispose();
                savedFrame = null;
            }
        }
    }

    static class SavedListener implements ActionListener {
        /**
         * The actionPerformed() method of the Save button listener classes
         * navigates the user to the MainFrame.
         * Notice, that we process the click of the button only if the user
         * is calling the function inside the MainFrame.
         * Moreover, we only hide the MainFrame for the response speed and the memory effect,
         * as the downside in the performance is not such a relevant problem in our case.
         *
         * @param ev the event to be processed
         */
        public void actionPerformed(ActionEvent ev) {
            if (savedFrame == null) {
                savedFrame = new SavedFrame(mainFrame);
                savedFrame.setVisible(true);
                mainFrame.setVisible(false);
            }
        }
    }
}
