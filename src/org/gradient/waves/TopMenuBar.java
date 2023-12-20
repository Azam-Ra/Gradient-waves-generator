package org.gradient.waves;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>Top Menu Bar</h1>
 * Top menu bar is serving the purpose to help the new user
 * understand the logic, constraints and nuances of the program.
 * <br>
 * It contain one Help JMenu, which contains the JMenu items,
 * which navigate to tips for the load and save buttons.
 * @see JMenu
 * @see JMenuItem
 */
public class TopMenuBar extends JMenuBar {
    public TopMenuBar() {
        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAboutLoad = new JMenuItem("About Load");
        JMenuItem jmiAboutSave = new JMenuItem("About Save");

        HelpListener helpListener = new HelpListener(this);
        jmiAboutLoad.addActionListener(helpListener);
        jmiAboutSave.addActionListener(helpListener);

        jmHelp.add(jmiAboutLoad);
        jmHelp.add(jmiAboutSave);

        this.add(jmHelp);
    }

    /**
     * HelpListener is the class to handle the button generated events,
     * which implements the ActionListener class.
     * @see ActionListener
     */
    static class HelpListener implements ActionListener {
        private TopMenuBar topMenuBar;

        public HelpListener(TopMenuBar topMenuBar) {
            this.topMenuBar = topMenuBar;
        }

        /**
         * actionPerformed() here based on the JMenuItem dislpays different text
         * in showMessageDialog.
         * @see JOptionPane
         *
         * @param ev the event to be processed
         */
        public void actionPerformed(ActionEvent ev) {
            if (ev.getActionCommand().equals("About Load")) {
                JOptionPane.showMessageDialog(topMenuBar,
                        "Couldn't think of helping text about the load yet :)");
            } else {
                JOptionPane.showMessageDialog(topMenuBar,
                        "Couldn't think of helping text about the save yet :)");
            }
        }
    }
}
