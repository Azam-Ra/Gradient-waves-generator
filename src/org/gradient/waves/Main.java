package org.gradient.waves;

/**
 * <h1>Gradient Waves</h1>
 * The overall program is a simple GUI application, which renders
 * gradient waves of different colors based on the Perlin noise's algorithm.
 *
 * @author Azam Rakhmatillaev
 */
public class Main {

    /**
     * The main() function of the class start the program.
     * The mainFrame window is generated and
     * further the user can navigate inside the program freely.
     *
     * @see MainFrame
     * @param args
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}