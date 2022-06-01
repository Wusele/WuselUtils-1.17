/*
 * Copyright (c) 2022.
 * Class created by Wusel
 */

package me.wusel.wuselutils.utilitity;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.bukkit.Bukkit.getLogger;

public class Downloader {

    public static void download(String url, String file) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("plugins/" + file)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
            getLogger().warning("Could not download " + file + " from " + url);
            e.printStackTrace();
        }
    }

    public static void downloadFile(URL url, String outputFileName) throws IOException
    {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(outputFileName));
        }
    }


    public static void downloadPlugin(String resourceId, String file) {
        download("https://api.spiget.org/v2/resources/" + resourceId + "/download", file);
    }
}
