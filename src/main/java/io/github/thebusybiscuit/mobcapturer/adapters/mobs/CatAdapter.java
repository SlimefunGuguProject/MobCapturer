package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;

import io.github.thebusybiscuit.mobcapturer.utils.compatibility.CatTypeX;
import net.guizhanss.minecraft.guizhanlib.gugu.java.BooleanHelper;
import net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.DyeColorHelper;
import net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity.CatHelper;

public class CatAdapter extends AbstractTameableAdapter<Cat> {

    public CatAdapter() {
        super(Cat.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "类型: " + ChatColor.WHITE + CatHelper.getTypeName(json.get("catType").getAsString()));

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "项圈颜色: " + ChatColor.WHITE + DyeColorHelper.getName(json.get("collarColor").getAsString()));
            lore.add(ChatColor.GRAY + "坐下: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("sitting").getAsBoolean()));
        }

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Cat entity, JsonObject json) {
        super.apply(entity, json);

        CatTypeX.set(entity, json.get("catType").getAsString());
        entity.setSitting(json.get("sitting").getAsBoolean());
        entity.setCollarColor(DyeColor.valueOf(json.get("collarColor").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Cat entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("catType", CatTypeX.get(entity));
        json.addProperty("sitting", entity.isSitting());
        json.addProperty("collarColor", entity.getCollarColor().name());

        return json;
    }

}
