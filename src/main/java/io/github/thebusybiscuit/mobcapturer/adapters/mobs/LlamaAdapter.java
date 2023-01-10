package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Llama.Color;

import net.guizhanss.guizhanlib.minecraft.helper.entity.HorseHelper;

public class LlamaAdapter<T extends Llama> extends ChestedHorseAdapter<T> {

    public LlamaAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "颜色: " + ChatColor.WHITE + HorseHelper.getColor(json.get("color").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setColor(Color.valueOf(json.get("color").getAsString()));
        entity.setStrength(json.get("spitStrength").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("color", entity.getColor().name());
        json.addProperty("spitStrength", entity.getStrength());

        return json;
    }

}
