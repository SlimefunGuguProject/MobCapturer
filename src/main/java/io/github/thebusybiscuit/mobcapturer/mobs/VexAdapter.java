package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Vex;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

public class VexAdapter implements MobAdapter<Vex> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "冲锋状态: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("charging").getAsBoolean()));

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(Vex entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setCharging(json.get("charging").getAsBoolean());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Vex entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("charging", entity.isCharging());

        return json;
    }

    @Nonnull
    @Override
    public Class<Vex> getEntityClass() {
        return Vex.class;
    }

}
