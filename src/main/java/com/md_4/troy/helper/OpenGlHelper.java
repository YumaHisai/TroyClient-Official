package com.md_4.troy.helper;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.Display;

public final class OpenGlHelper {
  public static void setWindowIcon(String px32, String px64) throws IOException {
    Display.setIcon(new ByteBuffer[]{loadIcon(new URL(px32)), loadIcon(new URL(px64))});
  }

  public static int rainbowColor(int speed, int offset) {
    float hue = (System.currentTimeMillis() + offset) % speed;
    hue /= speed;
    return Color.getHSBColor(hue, 0.35F, 1.0F).getRGB();
  }

  private static ByteBuffer loadIcon(URL url) throws IOException {
    InputStream inputStream = url.openStream();
    Throwable var2 = null;

    ByteBuffer var5;
    try {
      PNGDecoder decoder = new PNGDecoder(inputStream);
      ByteBuffer buffer = ByteBuffer.allocateDirect(decoder.getWidth() * decoder.getHeight() * 4);
      decoder.decode(buffer, decoder.getWidth() * 4, Format.RGBA);
      buffer.flip();
      var5 = buffer;
    } catch (Throwable var14) {
      var2 = var14;
      throw var14;
    } finally {
      if (inputStream != null) {
        if (var2 != null) {
          try {
            inputStream.close();
          } catch (Throwable var13) {
            var2.addSuppressed(var13);
          }
        } else {
          inputStream.close();
        }
      }

    }

    return var5;
  }
}

