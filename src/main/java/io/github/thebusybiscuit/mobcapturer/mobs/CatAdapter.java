package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import net.guizhanss.minecraft.chineselib.language.Boolean;
import net.guizhanss.minecraft.chineselib.minecraft.DyeColors;
import net.guizhanss.minecraft.chineselib.minecraft.entity.Cats;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;

import java.util.List;

public class CatAdapter extends AbstractTameableAdapter<Cat> {

    public CatAdapter() {
        super(Cat.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "类型: " + ChatColor.WHITE + Cats.Type.fromEnglish(json.get("catType").getAsString()));

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "项圈颜色: " + ChatColor.WHITE + DyeColors.fromEnglish(json.get("collarColor").getAsString()));
            lore.add(ChatColor.GRAY + "坐下: " + ChatColor.WHITE + Boolean.yesOrNo(json.get("sitting").getAsBoolean()));
        }

        return lore;
    }

    @Override
    public void apply(Cat entity, JsonObject json) {
        super.apply(entity, json);

        entity.setCatType(Type.valueOf(json.get("catType").getAsString()));
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setCollarColor(DyeColor.valueOf(json.get("collarColor").getAsString()));
    }

    @Override
    public JsonObject saveData(Cat entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("catType", entity.getCatType().name());
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("collarColor", entity.getCollarColor().name());

        return json;
    }

}
