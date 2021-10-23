package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import net.guizhanss.minecraft.chineselib.language.Boolean;
import net.guizhanss.minecraft.chineselib.minecraft.DyeColors;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Wolf;

import java.util.List;

public class WolfAdapter extends AbstractTameableAdapter<Wolf> {

    public WolfAdapter() {
        super(Wolf.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "项圈颜色: " + ChatColor.WHITE + DyeColors.fromEnglish(json.get("collarColor").getAsString()));
            lore.add(ChatColor.GRAY + "坐下: " + ChatColor.WHITE + Boolean.yesOrNo(json.get("sitting").getAsBoolean()));
        } else {
            lore.add(ChatColor.GRAY + "愤怒: " + ChatColor.WHITE + Boolean.yesOrNo(json.get("angry").getAsBoolean()));
        }

        return lore;
    }

    @Override
    public void apply(Wolf entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAngry(json.get("angry").getAsBoolean());
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setCollarColor(DyeColor.valueOf(json.get("collarColor").getAsString()));
    }

    @Override
    public JsonObject saveData(Wolf entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("angry", entity.isAngry());
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("collarColor", entity.getCollarColor().name());

        return json;
    }

}
