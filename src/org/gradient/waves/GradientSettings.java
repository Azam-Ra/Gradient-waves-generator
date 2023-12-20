package org.gradient.waves;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * <h1>Gradient Settings Panel</h1>
 * GradientSettings panel displays the necessary settings to
 * adjust the colors of the gradient waves, render the frame with the waves,
 * save the chosen settings.
 */
public class GradientSettings extends JPanel{
    private JLabel sizeLabel = new JLabel("Size: ");
    private static String[] sizeOptions = {"960x480", "480x480"};
    private static JComboBox<String> sizeBox = new JComboBox<>(sizeOptions);

    private JLabel redLabel = new JLabel("R:");
    private static JTextField redTextLabel = new JTextField(3);

    private JLabel greenLabel = new JLabel("G:");
    private static JTextField greenTextLabel = new JTextField(3);

    private JLabel blueLabel = new JLabel("B:");
    private static JTextField blueTextLabel = new JTextField(3);

    private JLabel alphaLabel = new JLabel("A:");
    private static JTextField alphaTextLabel = new JTextField(3);

    private JButton loadButton = new JButton("Load");
    private JButton saveButton = new JButton("Save");

    public GradientSettings() {
        loadButton.addActionListener(new LoadListener());

        File file = new File("saved-settings.ser");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveButton.addActionListener(new SaveListener(this, outputStream));

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        this.add(sizeLabel);
        this.add(sizeBox);
        this.add(redLabel);
        this.add(redTextLabel);
        this.add(greenLabel);
        this.add(greenTextLabel);
        this.add(blueLabel);
        this.add(blueTextLabel);
        this.add(alphaLabel);
        this.add(alphaTextLabel);
        this.add(loadButton);
        this.add(saveButton);

        // Define spring constraints for the first row
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, sizeLabel, -40, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, sizeLabel, 25, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, sizeBox, 10, SpringLayout.EAST, sizeLabel);
        layout.putConstraint(SpringLayout.NORTH, sizeBox, 25, SpringLayout.NORTH, this);

        // Define spring constraints for the second row
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, redLabel, -20, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, redLabel, 10, SpringLayout.SOUTH, sizeBox);

        layout.putConstraint(SpringLayout.WEST, redTextLabel, 10, SpringLayout.EAST, redLabel);
        layout.putConstraint(SpringLayout.NORTH, redTextLabel, 10, SpringLayout.SOUTH, sizeBox);

        // Define spring constraints for the third row
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, greenLabel, -20, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, greenLabel, 10, SpringLayout.SOUTH, redTextLabel);

        layout.putConstraint(SpringLayout.WEST, greenTextLabel, 10, SpringLayout.EAST, greenLabel);
        layout.putConstraint(SpringLayout.NORTH, greenTextLabel, 10, SpringLayout.SOUTH, redTextLabel);

        // Define spring constraints for the fourth row
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, blueLabel, -20, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, blueLabel, 10, SpringLayout.SOUTH, greenTextLabel);

        layout.putConstraint(SpringLayout.WEST, blueTextLabel, 10, SpringLayout.EAST, blueLabel);
        layout.putConstraint(SpringLayout.NORTH, blueTextLabel, 10, SpringLayout.SOUTH, greenTextLabel);

        // Define spring constraints for the fifth row
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, alphaLabel, -20, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, alphaLabel, 10, SpringLayout.SOUTH, blueTextLabel);

        layout.putConstraint(SpringLayout.WEST, alphaTextLabel, 10, SpringLayout.EAST, alphaLabel);
        layout.putConstraint(SpringLayout.NORTH, alphaTextLabel, 10, SpringLayout.SOUTH, blueTextLabel);

        // Define spring constraints for the sixth row
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loadButton, -40, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, loadButton, 10, SpringLayout.SOUTH, alphaTextLabel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, saveButton, 40, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, saveButton, 10, SpringLayout.SOUTH, alphaTextLabel);
    }

    /**
     * The LoadListener is the actionListener of the Load button.
     * It reads the values of RGBA settings and desired dimensions
     * of rendering WavesFrame.
     * @see WavesFrame
     */
    static class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            int[] dimensions = getIntegerDimensions((String) sizeBox.getSelectedItem());

            int[] rgba = getCorrectRGBA(new String[]{redTextLabel.getText(), greenTextLabel.getText(),
                    blueTextLabel.getText(), alphaTextLabel.getText()});

            if (rgba == null) {
                JOptionPane.showMessageDialog(null,
                        "You did not set settings correctly!");

                return;
            }

            WavesFrame wavesFrame = new WavesFrame(dimensions[0], dimensions[1],
                    rgba[0], rgba[1], rgba[2], rgba[3]);
            wavesFrame.setVisible(true);
        }
    }

    /**
     * The SaveListener is the actionListener of the Save button.
     * It reads values of RGBA settings and assigns a name to the specific settings.
     * The settings are saved via SavedSetting class, which are further serialized
     * and stored inside the `saved-settings.ser` file.
     * @see SavedSetting
     */
    static class SaveListener implements ActionListener {
        private JPanel parentPanel;
        private ObjectOutputStream outputStream;

        public SaveListener(JPanel parentPanel, ObjectOutputStream outputStream) {
            this.parentPanel = parentPanel;
            this.outputStream = outputStream;
        }

        public void actionPerformed(ActionEvent ev) {
            String name = JOptionPane.showInputDialog(parentPanel,
                    "Choose a name for the saved configuration:", null);

            if (name == null) return;

            if (!isCorrectlyFormattedName(name)) {
                JOptionPane.showMessageDialog(parentPanel,
                        "There cannot be any spaces in the configuration name!");

                return;
            }

            int[] rgba = getCorrectRGBA(new String[]{redTextLabel.getText(), greenTextLabel.getText(),
                    blueTextLabel.getText(), alphaTextLabel.getText(),});

            if (rgba == null) {
                JOptionPane.showMessageDialog(parentPanel,
                        "You did not set RGBA settings correctly!");

                return;
            }

            SavedSetting savedSetting = new SavedSetting(name, rgba[0], rgba[1], rgba[2], rgba[3]);
            try {
                outputStream.writeObject(savedSetting);
                outputStream.flush();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parentPanel,
                        "We couldn't save the given settings!");
            }
        }
    }

    /**
     * `getIntegerDimensions()` is the method of the GradientSettings class to
     * convert size options of the string type from `sizeBox` to integer array of size 2,
     * containing the desired width and height.
     *
     * @param dimensions string from sizeBox
     * @return int[]
     */
    public static int[] getIntegerDimensions(String dimensions) {
        int xIndex = 0;
        while (dimensions.charAt(xIndex) != 'x') xIndex++;

        int width = Integer.parseInt(dimensions.substring(0, xIndex));
        int height = Integer.parseInt(dimensions.substring(xIndex+1));

        return new int[]{width, height};
    }

    /**
     * isCorrectlyFormattedName() is the method of the GradientSettings class to
     * ensure the correct format of the provided name.
     *
     * @param input name of the setting provided by the user
     * @return boolean
     */
    public static boolean isCorrectlyFormattedName(String input) {
        if (input.length() > 20) return false;

        final int length = input.length();

        for (int i = 0; i < length; i++) {
            if (input.charAt(i) == ' ') return false;
        }

        return true;
    }

    /**
     * getCorrectRGBA() is the method of the GradientSettings class to
     * ensure the correctness of the provided by the user RGBA settings.
     *
     * @param rgba String[]
     * @return int[4]
     */
    public static int[] getCorrectRGBA(String[] rgba) {
        int[] intData = new int[4];
        for (int i = 0; i < 4; i++) {
            try {
                intData[i] = Integer.parseInt(rgba[i]);

                if (intData[i] < 0 || intData[i] > 255)
                    return null;
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return intData;
    }
}
