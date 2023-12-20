package org.gradient.waves;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * <h1>Saved History Panel</h1>
 * SavedHistoryPanel is the class rendering the settings saved by the user,
 * which extends JPanel class.
 * @see JPanel
 * <br>
 * The saved settings are stored in and read from the saved-settings.ser file,
 * using the getData() method of FileUtil class.
 * @see FileUtil
 */
public class SavedHistoryPanel extends JPanel {
    public SavedHistoryPanel() {
        ArrayList<SavedSetting> data = (ArrayList<SavedSetting>) FileUtil.getData();

        if (data == null) {
            String infoString = "We couldn't load the saved settings!";
            JLabel infoLabel = new JLabel(infoString);

            SpringLayout layout = new SpringLayout();
            this.setLayout(layout);

            this.add(infoLabel);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, infoLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, infoLabel, 0, SpringLayout.VERTICAL_CENTER, this);
        } else if (data.isEmpty()) {
            String infoString = "You haven't saved any settings yet ;)";
            JLabel infoLabel = new JLabel(infoString);

            SpringLayout layout = new SpringLayout();
            this.setLayout(layout);

            this.add(infoLabel);

            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, infoLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, infoLabel, 0, SpringLayout.VERTICAL_CENTER, this);
        } else {
            JList<String> savedList = new JList<>(getFormattedData(data));

            this.setLayout(new BorderLayout());
            this.add(new JScrollPane(savedList), BorderLayout.CENTER);
        }
    }

    /**
     * getFormattedData() is a method of the SavedHistoryPanel class, which
     * converts the attributes of SavedSetting objects to the String type.
     * For the further purposes to render the data in the JList.
     * @see SavedSetting
     * @see JList
     *
     * @param data is of type ArrayList<SavedSetting>
     * @return String[]
     */
    public static String[] getFormattedData(ArrayList<SavedSetting> data) {
        final int size = data.size();

        String[] formattedData = new String[size];

        for (int i = 0; i < size; i++) {
            SavedSetting dataUnit = data.get(i);

            String formattedDataUnit = "Name: \"" + dataUnit.name + "\", R: " + dataUnit.r
                    + ", G: " + dataUnit.g + ", B: " + dataUnit.b + ", A: " + dataUnit.a;

            formattedData[i] = formattedDataUnit;
        }

        return formattedData;
    }
}
