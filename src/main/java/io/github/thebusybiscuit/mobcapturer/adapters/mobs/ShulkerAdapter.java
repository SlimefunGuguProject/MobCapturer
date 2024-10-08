package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Shulker;

import io.github.thebusybiscuit.mobcapturer.adapters.MobAdapter;

import net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.DyeColorHelper;

public class ShulkerAdapter implements MobAdapter<Shulker> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        JsonElement color = json.get("color");

        if (!color.isJsonNull()) {
            lore.add(ChatColor.GRAY + "颜色: " + ChatColor.WHITE + DyeColorHelper.getName(color.getAsString()));
        }

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Shulker entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        JsonElement color = json.get("color");
        if (!color.isJsonNull()) {
            entity.setColor(DyeColor.valueOf(color.getAsString()));
        }
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Shulker entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        DyeColor color = entity.getColor();
        json.addProperty("color", color == null ? null : color.name());

        return json;
    }

    @Nonnull
    @Override
    public Class<Shulker> getEntityClass() {
        return Shulker.class;
    }

}
