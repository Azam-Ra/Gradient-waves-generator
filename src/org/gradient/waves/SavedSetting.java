package org.gradient.waves;

import java.io.Serializable;

/**
 * <h1>Saved Setting</h1>
 * SavedSetting is the class, which is designed to store the RGBA settings
 * and the defining name for further serialization.
 */
public class SavedSetting implements Serializable {
    public final String name;
    public final int r;
    public final int g;
    public final int b;
    public final int a;

    public SavedSetting(String name, int r, int g, int b, int a) {
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
