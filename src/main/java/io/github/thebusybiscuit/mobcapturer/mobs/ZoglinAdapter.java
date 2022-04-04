package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import net.guizhanss.guizhanlib.language.BooleanHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Hoglin;

import java.util.List;

public class ZoglinAdapter extends AnimalsAdapter<Hoglin> {

    public ZoglinAdapter() {
        super(Hoglin.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "幼年: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("baby").getAsBoolean()));

        return lore;
    }

    @Override
    public void apply(Hoglin entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAge(json.get("age").getAsInt());
    }

    @Override
    public JsonObject saveData(Hoglin entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());

        return json;
    }

}