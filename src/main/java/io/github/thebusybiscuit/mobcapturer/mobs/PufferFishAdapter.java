package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import io.github.thebusybiscuit.mobcapturer.MobAdapter;
import net.guizhanss.guizhanlib.minecraft.helper.entity.PufferFishHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.PufferFish;

import java.util.List;

public class PufferFishAdapter implements MobAdapter<PufferFish> {

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "膨胀等级: " + ChatColor.WHITE + PufferFishHelper.getPuffState(json.get("puffState").getAsInt()));

        return lore;
    }

    @Override
    public void apply(PufferFish entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setPuffState(json.get("puffState").getAsInt());
    }

    @Override
    public JsonObject saveData(PufferFish entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("puffState", entity.getPuffState());

        return json;
    }

    @Override
    public Class<PufferFish> getEntityClass() {
        return PufferFish.class;
    }

}
