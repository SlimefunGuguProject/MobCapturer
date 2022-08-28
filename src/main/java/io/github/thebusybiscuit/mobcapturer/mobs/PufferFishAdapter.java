package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.PufferFish;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;

import net.guizhanss.guizhanlib.minecraft.helper.entity.PufferFishHelper;

public class PufferFishAdapter implements MobAdapter<PufferFish> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "膨胀等级: " + ChatColor.WHITE + PufferFishHelper.getPuffState(json.get("puffState").getAsInt()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(PufferFish entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setPuffState(json.get("puffState").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull PufferFish entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("puffState", entity.getPuffState());

        return json;
    }

    @Nonnull
    @Override
    public Class<PufferFish> getEntityClass() {
        return PufferFish.class;
    }

}
