package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import net.guizhanss.guizhanlib.language.BooleanHelper;
import net.guizhanss.guizhanlib.minecraft.helper.DyeColorHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Parrot.Variant;

import java.util.List;

public class ParrotAdapter extends AbstractTameableAdapter<Parrot> {

    public ParrotAdapter() {
        super(Parrot.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "颜色: " + ChatColor.WHITE + DyeColorHelper.getName(json.get("variant").getAsString()));

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "坐下: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("sitting").getAsBoolean()));
        }

        return lore;
    }

    @Override
    public void apply(Parrot entity, JsonObject json) {
        super.apply(entity, json);

        entity.setVariant(Variant.valueOf(json.get("variant").getAsString()));
        entity.setSitting(json.get("sitting").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Parrot entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("variant", entity.getVariant().name());
        json.addProperty("sitting", entity.isSitting());

        return json;
    }

}
