package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import net.guizhanss.guizhanlib.java.BooleanHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Hoglin;

import java.util.List;

public class HoglinAdapter extends AnimalsAdapter<Hoglin> {

    public HoglinAdapter() {
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
        entity.setIsAbleToBeHunted(json.get("ableToBeHunted").getAsBoolean());
        entity.setConversionTime(json.get("conversionTime").getAsInt());
        entity.setImmuneToZombification(json.get("immuneToZombification").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Hoglin entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());
        json.addProperty("ableToBeHunted", entity.isAbleToBeHunted());
        json.addProperty("conversionTime", entity.getConversionTime());
        json.addProperty("immuneToZombification", entity.isImmuneToZombification());

        return json;
    }

}