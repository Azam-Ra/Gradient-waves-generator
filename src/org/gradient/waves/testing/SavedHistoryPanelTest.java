package org.gradient.waves.testing;

import static org.gradient.waves.SavedHistoryPanel.getFormattedData;
import org.gradient.waves.SavedSetting;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Saved History Panel Test</h1>
 * <code>SavedHistoryPanelTest</code> class is defined to test the
 * correctness of the methods of the <code>SavedHistoryPanel</code> class.
 * @see org.gradient.waves.PerlinNoise2D
 */
class SavedHistoryPanelTest {
    @Test
    public void testGetFormattedData() {
        String[] result1 = getFormattedData(new ArrayList<SavedSetting>());
        assertArrayEquals(new String[0], result1);

        ArrayList<SavedSetting> input = new ArrayList<>();
        input.add(new SavedSetting("", 0, 0, 0, 0));
        String[] result = {"Name: \"\", R: 0, G: 0, B: 0, A: 0"};
        assertArrayEquals(result, getFormattedData(input));
    }
}