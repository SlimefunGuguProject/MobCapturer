package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Frog.Variant;

import net.guizhanss.guizhanlib.minecraft.helper.entity.FrogHelper;

public class FrogAdapter extends AnimalsAdapter<Frog> {

    public FrogAdapter() {
        super(Frog.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "种类: " + ChatColor.WHITE + FrogHelper.getVariant(json.get("variant").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Frog entity, JsonObject json) {
        super.apply(entity, json);

        entity.setVariant(Variant.valueOf(json.get("variant").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Frog entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("variant", entity.getVariant().name());

        return json;
    }

}
