/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class ColorUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final Random rndm = new Random();
    public static Color color = new Color(0, 0, 1);

    public static int randomColor() {
        return 0xFF000000 | (int)(Math.random() * 1.6777215E7);
    }

    public static int transparency(int color, double alpha) {
        if ((color & 0xFC000000) == 0) {
            color |= 0xFF000000;
        }
        Color c2 = new Color(color);
        float r2 = 0.003921569f * (float)c2.getRed();
        float g2 = 0.003921569f * (float)c2.getGreen();
        float b2 = 0.003921569f * (float)c2.getBlue();
        return new Color(r2, g2, b2, (float)alpha).getRGB();
    }

    public static int transparency(Color color, double alpha) {
        if (alpha > 1.0) {
            alpha = 1.0;
        } else if (alpha < 0.0) {
            alpha = 0.0;
        }
        return new Color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)alpha).getRGB();
    }

    public static double getClampedRandom(double min, double max) {
        return min + (max - min) * rndm.nextDouble();
    }

    public static double[] rgbFromHex(int hex) {
        int r2 = (hex & 0xFF0000) >> 16;
        int g2 = (hex & 0xFF00) >> 8;
        int b2 = hex & 0xFF;
        double[] rgb = new double[]{r2, g2, b2};
        return rgb;
    }

    public static Color rainbow(long offset, float fade) {
        float hue = (float)(System.nanoTime() + offset) / 1.0E10f % 1.0f;
        long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        Color c2 = new Color((int)color);
        return new Color((float)c2.getRed() / 255.0f * fade, (float)c2.getGreen() / 255.0f * fade, (float)c2.getBlue() / 255.0f * fade, (float)c2.getAlpha() / 255.0f);
    }

    public static float[] getRGBA(int color) {
        if ((color & 0xFC000000) == 0) {
            color |= 0xFF000000;
        }
        float a2 = (float)(color >> 24 & 0xFF) / 255.0f;
        float r2 = (float)(color >> 16 & 0xFF) / 255.0f;
        float g2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float b2 = (float)(color & 0xFF) / 255.0f;
        return new float[]{r2, g2, b2, a2};
    }

    public static void glColor(int color) {
        float[] colors = ColorUtils.getRGBA(color);
        GL11.glColor4f(colors[0], colors[1], colors[2], colors[3]);
    }

    public static int intFromHex(String hex) {
        try {
            return Integer.parseInt(hex, 16);
        }
        catch (NumberFormatException var2) {
            return -1;
        }
    }

    public static String hexFromInt(int color) {
        if ((color & 0xFC000000) == 0) {
            color |= 0xFF000000;
        }
        return ColorUtils.hexFromColor(new Color(color));
    }

    public static String hexFromColor(Color color) {
        return Integer.toHexString(color.getRGB()).substring(2);
    }

    public static int intFromColor(Color color) {
        return ColorUtils.intFromHex(ColorUtils.hexFromColor(color));
    }

    public static Color blend(Color color1, Color color2, double ratio) {
        float r2 = (float)ratio;
        float ir2 = 1.0f - r2;
        float[] rgb1 = new float[3];
        float[] rgb2 = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        Color color = new Color(rgb1[0] * r2 + rgb2[0] * ir2, rgb1[1] * r2 + rgb2[1] * ir2, rgb1[2] * r2 + rgb2[2] * ir2);
        return color;
    }

    public static Color blend(Color color1, Color color2) {
        return ColorUtils.blend(color1, color2, 0.5);
    }

    public static Color darken(Color color, double fraction) {
        int red = (int)Math.round((double)color.getRed() * (1.0 - fraction));
        int green = (int)Math.round((double)color.getGreen() * (1.0 - fraction));
        int blue = (int)Math.round((double)color.getBlue() * (1.0 - fraction));
        if (red < 0) {
            red = 0;
        } else if (red > 255) {
            red = 255;
        }
        if (green < 0) {
            green = 0;
        } else if (green > 255) {
            green = 255;
        }
        if (blue < 0) {
            blue = 0;
        } else if (blue > 255) {
            blue = 255;
        }
        int alpha = color.getAlpha();
        return new Color(red, green, blue, alpha);
    }

    public static Color lighten(Color color, double fraction) {
        int red = (int)Math.round((double)color.getRed() * (1.0 + fraction));
        int green = (int)Math.round((double)color.getGreen() * (1.0 + fraction));
        int blue = (int)Math.round((double)color.getBlue() * (1.0 + fraction));
        if (red < 0) {
            red = 0;
        } else if (red > 255) {
            red = 255;
        }
        if (green < 0) {
            green = 0;
        } else if (green > 255) {
            green = 255;
        }
        if (blue < 0) {
            blue = 0;
        } else if (blue > 255) {
            blue = 255;
        }
        int alpha = color.getAlpha();
        return new Color(red, green, blue, alpha);
    }

    public static int getColor(char character) {
        return Minecraft.fontRendererObj.func_175064_b(character);
    }
}

