/*
 * Copyright (c) 2020 Warp Studios
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package co.bywarp.lightkit.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class IOUtils {

    /**
     * Reads from a File to a String
     *
     * @param file the file
     * @return the String
     * @throws IOException
     */
    public static String toString(File file) throws IOException {
        if (!IOUtils.isReadable(file)) {
            return "";
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String ret = IOUtils.readAll(reader);
        reader.close();

        return ret;
    }

    /**
     * Reads from a URL to a String
     *
     * @param url
     * @param charset the Charset
     * @return the String
     * @throws IOException
     */
    public static String toString(URL url, Charset charset) throws IOException {
        InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        String ret = IOUtils.readAll(reader);
        reader.close();

        return ret;
    }

    /**
     * Reads from a URL to a String
     *
     * @param url
     * @return the String
     * @throws IOException
     */
    public static String toString(URL url) throws IOException {
        return IOUtils.toString(url, Charset.defaultCharset());
    }

    /**
     * Reads all the lines of a BufferedReader
     *
     * @param reader the BufferedReader
     * @return
     */
    public static String readAll(BufferedReader reader) {
        StringBuilder builder = new StringBuilder();
        reader.lines().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Checks if a file can be read from
     *
     * @param file the file
     * @return if a the file can be read from
     */
    public static boolean isReadable(File file) {
        return file.exists() && !file.isDirectory();
    }

}

