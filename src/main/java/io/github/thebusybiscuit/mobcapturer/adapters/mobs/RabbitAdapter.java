package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Rabbit.Type;

import net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity.RabbitHelper;

public class RabbitAdapter extends AnimalsAdapter<Rabbit> {

    public RabbitAdapter() {
        super(Rabbit.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "颜色: " + ChatColor.WHITE + RabbitHelper.getTypeName(json.get("rabbitType").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Rabbit entity, JsonObject json) {
        super.apply(entity, json);

        entity.setRabbitType(Type.valueOf(json.get("rabbitType").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Rabbit entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("rabbitType", entity.getRabbitType().name());

        return json;
    }

}
