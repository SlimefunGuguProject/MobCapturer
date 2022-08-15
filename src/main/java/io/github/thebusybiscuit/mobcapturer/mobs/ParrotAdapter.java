package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Parrot.Variant;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class ParrotAdapter extends AbstractTameableAdapter<Parrot> {

    public ParrotAdapter() {
        super(Parrot.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "颜色: " + ChatColor.WHITE + DyeColorHelper.getName(json.get("variant").getAsString()));

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "坐下: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("sitting").getAsBoolean()));
        }

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Parrot entity, JsonObject json) {
        super.apply(entity, json);

        entity.setVariant(Variant.valueOf(json.get("variant").getAsString()));
        entity.setSitting(json.get("sitting").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Parrot entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("variant", entity.getVariant().name());
        json.addProperty("sitting", entity.isSitting());

        return json;
    }

}
