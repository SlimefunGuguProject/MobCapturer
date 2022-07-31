package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import io.github.thebusybiscuit.mobcapturer.MobAdapter;
import net.guizhanss.guizhanlib.java.BooleanHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Animals;

import java.util.List;

public class AnimalsAdapter<T extends Animals> implements MobAdapter<T> {

    private final Class<T> entityClass;

    public AnimalsAdapter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "幼年: " + ChatColor.WHITE + BooleanHelper.yesOrNo(json.get("baby").getAsBoolean()));

        return lore;
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("baby", !entity.isAdult());
        json.addProperty("_age", entity.getAge());
        json.addProperty("_ageLock", entity.getAgeLock());
        json.addProperty("_breedable", entity.canBreed());
        json.addProperty("_loveModeTicks", entity.getLoveModeTicks());

        return json;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setAge(json.get("_age").getAsInt());
        entity.setLoveModeTicks(json.get("_loveModeTicks").getAsInt());
        entity.setAgeLock(json.get("_ageLock").getAsBoolean());
        entity.setBreed(json.get("_breedable").getAsBoolean());
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

}
