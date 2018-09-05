package com.mqtt.app.services;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author andremury
 */
public class PathCleaner {

    String folderName;

    public PathCleaner(String folderName) {
        this.folderName = folderName;
    }

    public void clean() throws IOException {

        FileUtils.deleteDirectory(new File(folderName));
        System.out.println("Cleanning");
    }

}
