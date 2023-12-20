package org.gradient.waves.testing;

import org.gradient.waves.PerlinNoise2D;
import org.junit.jupiter.api.Test;

import static org.gradient.waves.PerlinNoise2D.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Perlin Noise 2D Test</h1>
 * <code>PerlinNoise2DTest</code> class is defined to test the
 * correctness of the methods of the <code>PerlinNoise2D</code> class.
 * @see org.gradient.waves.PerlinNoise2D
 */
class PerlinNoise2DTest {
    @Test
    public void testLerp() {
        assertEquals(0.1, lerp(1, 0.2, 0.1));
        assertEquals(0.2, lerp(0, 0.2, 0.1));
        assertEquals(0.0, lerp(1, 0, 0));
    }

    @Test
    public void testFade() {
        assertEquals(0.0, fade(0));
        assertEquals(0.008560000000000003, fade(0.1));
        assertEquals(-0.011560000000000003, fade(-0.1));
    }

    @Test
    public void testGrad() {
        assertEquals(-0.2, grad(-1, 0.1, 0.1));
        assertEquals(0.2, grad(0, 0.1, 0.1));
        assertEquals(0.0, grad(1, 0.1, 0.1));
    }


}