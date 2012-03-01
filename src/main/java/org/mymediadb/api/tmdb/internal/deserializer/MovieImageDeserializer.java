package org.mymediadb.api.tmdb.internal.deserializer;

import com.google.gson.*;
import org.mymediadb.api.tmdb.internal.model.ImageImpl;

import java.lang.reflect.Type;

public class MovieImageDeserializer implements JsonDeserializer<ImageImpl> {
    @Override
    public ImageImpl deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        //hack to bend api to my will
        //tmdb produces image content in json as follows {"image":{"<imagedata>"}}, my implementation skips the middleman.
        JsonObject obj = jsonElement.getAsJsonObject().get("image").getAsJsonObject();
        ImageImpl image = new ImageImpl(
                obj.getAsJsonPrimitive("type").getAsString(),
                obj.getAsJsonPrimitive("size").getAsString(),
                obj.getAsJsonPrimitive("height").getAsInt(),
                obj.getAsJsonPrimitive("width").getAsInt(),
                obj.getAsJsonPrimitive("url").getAsString(),
                obj.getAsJsonPrimitive("id").getAsString());
        return image;
    }
}
