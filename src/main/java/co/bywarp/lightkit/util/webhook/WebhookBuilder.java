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

package co.bywarp.lightkit.util.webhook;

import co.bywarp.lightkit.util.webhook.embeds.DiscordEmbed;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import kong.unirest.Unirest;

public class WebhookBuilder {

    private String url;
    private ArrayList<DiscordEmbed> embeds;

    public WebhookBuilder(String url) {
        this.url = url;
        this.embeds = new ArrayList<>();
    }

    public WebhookBuilder addEmbed(DiscordEmbed embed) {
        embeds.add(embed);
        return this;
    }

    public JSONObject getJson() {
        JSONArray embedList = new JSONArray();
        embeds.forEach(embed -> embedList.put(embed.toJson()));

        return new JSONObject().put("embeds", embedList);
    }

    public void execute() {
        CompletableFuture.runAsync(() -> Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(getJson().toString())
                .connectTimeout(5000)
                .socketTimeout(5000)
                .asJson());
    }

}
