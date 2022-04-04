package io.github.thebusybiscuit.mobcapturer.mobs;

import com.google.gson.JsonObject;
import io.github.thebusybiscuit.mobcapturer.MobAdapter;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import net.guizhanss.guizhanlib.minecraft.helper.DyeColorHelper;
import net.guizhanss.guizhanlib.minecraft.helper.entity.TropicalFishHelper;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.TropicalFish.Pattern;

import java.util.List;

public class TropicalFishAdapter implements MobAdapter<TropicalFish> {

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        String pattern = json.get("pattern").getAsString();
        lore.add(ChatColor.GRAY + "基础颜色: " + ChatColor.WHITE + DyeColorHelper.getName(json.get("baseColor").getAsString()));
        lore.add(ChatColor.GRAY + "条纹类型: " + ChatColor.WHITE + TropicalFishHelper.getPatternName(Pattern.valueOf(pattern)));
        lore.add(ChatColor.GRAY + "条纹颜色: " + ChatColor.WHITE + DyeColorHelper.getName(json.get("patternColor").getAsString()));

        return lore;
    }

    @Override
    public void apply(TropicalFish entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setBodyColor(DyeColor.valueOf(json.get("baseColor").getAsString()));
        entity.setPattern(Pattern.valueOf(json.get("pattern").getAsString()));
        entity.setPatternColor(DyeColor.valueOf(json.get("patternColor").getAsString()));
    }

    @Override
    public JsonObject saveData(TropicalFish entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("baseColor", entity.getBodyColor().name());
        json.addProperty("pattern", entity.getPattern().name());
        json.addProperty("patternColor", entity.getPatternColor().name());

        return json;
    }

    @Override
    public Class<TropicalFish> getEntityClass() {
        return TropicalFish.class;
    }

}
