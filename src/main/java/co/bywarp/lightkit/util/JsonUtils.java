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

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JsonUtils {

    /**
     * Retrieves JSON encoded data from a
     * specified URL.
     *
     * @param url the URL
     * @return the json encoded data
     * @throws IOException
     */
    public static JSONObject getFromUrl(String url) throws IOException {
        String jsonText = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
        return new JSONObject(jsonText);
    }

    /**
     * Retrieves JSON encoded data from
     * a specified file.
     *
     * @param file the target file
     * @return the json encoded data
     * @throws IOException
     */
    public static JSONObject getFromFile(File file) throws IOException {
        String text = IOUtils.toString(file);
        if (!Ensure.isJson(text)) {
            return null;
        }

        return new JSONObject(text);
    }

}

