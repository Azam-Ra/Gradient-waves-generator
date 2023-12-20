package org.gradient.waves.testing;

import org.junit.jupiter.api.Test;

import static org.gradient.waves.GradientSettings.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Gradient Settings Test</h1>
 * <code>GradientSettingsTest</code> class is defined to test the
 * correctness of the methods of the <code>GradientSetting</code> class.
 * @see org.gradient.waves.GradientSettings
 */
class GradientSettingsTest {
    @Test
    public void testGetIntegerDimensions() {
        int[] result = {1080, 720};
        assertArrayEquals(result, getIntegerDimensions("1080x720"));
    }

    @Test
    public void testIsCorrectlyFormattedName() {
        assertEquals(false, isCorrectlyFormattedName(" "));
        assertEquals(false,
                isCorrectlyFormattedName("123456789012345678901"));
        assertEquals(true,
                isCorrectlyFormattedName("12345678901234567890"));
        assertEquals(true, isCorrectlyFormattedName("Name"));
    }

    @Test
    public void testGetCorrectRGBA() {
        String[] input1 = {"0", "0", "0", "0"};
        assertArrayEquals(new int[]{0, 0, 0, 0}, getCorrectRGBA(input1));

        String[] input2 = {"a", "0", "0", "0"};
        assertArrayEquals(null, getCorrectRGBA(input2));

        String[] input3 = {"256", "0", "0", "0"};
        assertArrayEquals(null, getCorrectRGBA(input3));

        String[] input4 = {"-1", "0", "0", "0"};
        assertArrayEquals(null, getCorrectRGBA(input4));
    }
}