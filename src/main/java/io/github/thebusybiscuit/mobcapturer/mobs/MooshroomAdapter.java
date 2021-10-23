package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import net.guizhanss.minecraft.chineselib.minecraft.DyeColors;
import org.bukkit.ChatColor;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.MushroomCow.Variant;

import java.util.List;


public class MooshroomAdapter extends AnimalsAdapter<MushroomCow> {

    public MooshroomAdapter() {
        super(MushroomCow.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "颜色: " + ChatColor.WHITE + DyeColors.fromEnglish(json.get("variant").getAsString()));

        return lore;
    }

    @Override
    public void apply(MushroomCow entity, JsonObject json) {
        super.apply(entity, json);

        entity.setVariant(Variant.valueOf(json.get("variant").getAsString()));
    }

    @Override
    public JsonObject saveData(MushroomCow entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("variant", entity.getVariant().name());

        return json;
    }

}
