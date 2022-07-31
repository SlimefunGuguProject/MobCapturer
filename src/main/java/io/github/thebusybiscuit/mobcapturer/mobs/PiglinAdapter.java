package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import net.guizhanss.guizhanlib.java.BooleanHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Piglin;

import java.util.List;

public class PiglinAdapter extends AbstractHumanoidAdapter<Piglin> {

    public PiglinAdapter() {
        super(Piglin.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "幼年: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("baby").getAsBoolean()));

        return lore;
    }

    @Override
    public void apply(Piglin entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAge(json.get("age").getAsInt());
        entity.setIsAbleToHunt(json.get("ableToHunt").getAsBoolean());
        entity.setConversionTime(json.get("conversionTime").getAsInt());
        entity.setImmuneToZombification(json.get("immuneToZombification").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Piglin entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());
        json.addProperty("ableToHunt", entity.isAbleToHunt());
        json.addProperty("conversionTime", entity.getConversionTime());
        json.addProperty("immuneToZombification", entity.isImmuneToZombification());

        return json;
    }

}