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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class SpreadsheetUtils {

    /**
     * Attempts to read ranged-bounded information
     * from a Google Sheet provided a spreadsheet ID,
     * a Google Sheet API key, and a valid range.
     *
     * @param spreadsheetId the target spreadsheet id
     * @param apiKey a valid google sheets api key
     * @param rangeSelector an optional range selector (A1:B2, etc.)
     *
     * @return a list containing string arrays of row data for the requested range
     */
    public static List<String[]> readSpreadsheet(String spreadsheetId, String apiKey, Optional<String> rangeSelector) {
        try {
            JSONObject obj = JsonUtils.getFromUrl("https://sheets.googleapis.com/v4/spreadsheets/" + spreadsheetId + "/values/" + rangeSelector.orElse("Sheet1") + "?key=" + apiKey);
            return obj.getJSONArray("values")
                    .toList()
                    .stream()
                    .map(o -> {

                List<String> arr = (ArrayList<String>) o;
                String[] items = new String[arr.size()];
                for (int i = 0; i < items.length; i++) {
                    items[i] = arr.get(i);
                }

                return items;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            return null;
        }
    }

}