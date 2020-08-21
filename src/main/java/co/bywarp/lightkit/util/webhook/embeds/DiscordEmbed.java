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

package co.bywarp.lightkit.util.webhook.embeds;

import co.bywarp.lightkit.json.JsonSerializable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class DiscordEmbed implements JsonSerializable {

    private String avatarName;
    private String avatarUrl;
    private String title;
    private String description;
    private ArrayList<DiscordEmbedField> fields;
    private String footerText;

    public static final String MELON_ICON = "https://cdn.discordapp.com/attachments/308077256465448961/695787599578660944/Transparent_Background.png";
    public static final int EMBED_COLOR = 4946924;

    public DiscordEmbed(String avatarName, String avatarUrl, String title, String description, String footerText) {
        this.avatarName = avatarName;
        this.avatarUrl = avatarUrl;
        this.title = title;
        this.description = description;
        this.footerText = footerText;
    }

    public DiscordEmbed(String avatarName, String avatarUrl, String title, String description, String footerText, DiscordEmbedField... fields) {
        this.avatarName = avatarName;
        this.avatarUrl = avatarUrl;
        this.title = title;
        this.description = description;
        this.fields = new ArrayList<>(Arrays.asList(fields));
        this.footerText = footerText;
    }

    @Override
    public JSONObject toJson() {
        JSONObject response = new JSONObject()
                .put("author", new JSONObject()
                    .put("name", avatarName)
                    .put("icon_url", avatarUrl))
                .put("title", title)
                .put("description", description)
                .put("color", EMBED_COLOR)
                .put("footer", new JSONObject()
                    .put("text", footerText));

        if (fields != null && !fields.isEmpty()) {
            JSONArray fieldList = new JSONArray();
            fields.forEach(field -> fieldList.put(field.toJson()));

            response.put("fields", fieldList);
        }

        return response;
    }

}
