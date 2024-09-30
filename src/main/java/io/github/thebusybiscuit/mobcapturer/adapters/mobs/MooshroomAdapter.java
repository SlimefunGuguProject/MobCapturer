package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.MushroomCow.Variant;

import net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.DyeColorHelper;

public class MooshroomAdapter extends AnimalsAdapter<MushroomCow> {

    public MooshroomAdapter() {
        super(MushroomCow.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "颜色: " + ChatColor.WHITE + DyeColorHelper.getName(json.get("variant").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(MushroomCow entity, JsonObject json) {
        super.apply(entity, json);

        entity.setVariant(Variant.valueOf(json.get("variant").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull MushroomCow entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("variant", entity.getVariant().name());

        return json;
    }

}
