package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import io.github.thebusybiscuit.mobcapturer.MobAdapter;
import net.guizhanss.guizhanlib.java.BooleanHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Vex;

import java.util.List;

public class VexAdapter implements MobAdapter<Vex> {

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "冲锋状态: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("charging").getAsBoolean()));

        return lore;
    }

    @Override
    public void apply(Vex entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setCharging(json.get("charging").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Vex entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("charging", entity.isCharging());

        return json;
    }

    @Override
    public Class<Vex> getEntityClass() {
        return Vex.class;
    }

}
