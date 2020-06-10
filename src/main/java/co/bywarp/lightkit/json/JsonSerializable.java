package co.bywarp.lightkit.json;

import org.json.JSONObject;

public interface JsonSerializable {

    JSONObject toJson();
    <T> T fromJson();

}
