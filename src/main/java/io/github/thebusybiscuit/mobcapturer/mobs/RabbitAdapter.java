package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import net.guizhanss.guizhanlib.minecraft.helper.entity.RabbitHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Rabbit.Type;

import java.util.List;

public class RabbitAdapter extends AnimalsAdapter<Rabbit> {

    public RabbitAdapter() {
        super(Rabbit.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "颜色: " + ChatColor.WHITE + RabbitHelper.getType(json.get("rabbitType").getAsString()));

        return lore;
    }

    @Override
    public void apply(Rabbit entity, JsonObject json) {
        super.apply(entity, json);

        entity.setRabbitType(Type.valueOf(json.get("rabbitType").getAsString()));
    }

    @Override
    public JsonObject saveData(Rabbit entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("rabbitType", entity.getRabbitType().name());

        return json;
    }

}
