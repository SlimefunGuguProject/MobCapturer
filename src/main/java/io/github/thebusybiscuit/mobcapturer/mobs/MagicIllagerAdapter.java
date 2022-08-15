package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Spellcaster;
import org.bukkit.entity.Spellcaster.Spell;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class MagicIllagerAdapter<T extends Spellcaster> extends RaiderAdapter<T> {

    public MagicIllagerAdapter(@Nonnull Class<T> entityClass) {
        super(entityClass);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "法术: " + ChatColor.WHITE + ChatUtils.humanize(json.get("spell").getAsString()));

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);

        entity.setSpell(Spell.valueOf(json.get("spell").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull T entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("spell", entity.getSpell().name());

        return json;
    }

}
