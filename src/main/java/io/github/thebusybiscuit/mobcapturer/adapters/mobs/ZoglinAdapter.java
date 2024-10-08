package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Hoglin;

import net.guizhanss.minecraft.guizhanlib.gugu.java.BooleanHelper;

public class ZoglinAdapter extends AnimalsAdapter<Hoglin> {

    public ZoglinAdapter() {
        super(Hoglin.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "幼年: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("baby").getAsBoolean()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Hoglin entity, JsonObject json) {
        super.apply(entity, json);

        entity.setAge(json.get("age").getAsInt());
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Hoglin entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("age", entity.getAge());
        json.addProperty("baby", !entity.isAdult());

        return json;
    }

}
