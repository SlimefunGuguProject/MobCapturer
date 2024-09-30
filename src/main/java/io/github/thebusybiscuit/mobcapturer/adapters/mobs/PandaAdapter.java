package io.github.thebusybiscuit.mobcapturer.adapters.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Panda.Gene;

import net.guizhanss.minecraft.guizhanlib.gugu.minecraft.helpers.entity.PandaHelper;

public class PandaAdapter extends AnimalsAdapter<Panda> {

    public PandaAdapter() {
        super(Panda.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "主要基因: " + ChatColor.WHITE + PandaHelper.getGeneName(json.get("mainGene").getAsString()));
        lore.add(ChatColor.GRAY + "隐藏基因: " + ChatColor.WHITE + PandaHelper.getGeneName(json.get("hiddenGene").getAsString()));

        return lore;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void apply(Panda entity, JsonObject json) {
        super.apply(entity, json);

        entity.setMainGene(Gene.valueOf(json.get("mainGene").getAsString()));
        entity.setHiddenGene(Gene.valueOf(json.get("hiddenGene").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull Panda entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("mainGene", entity.getMainGene().name());
        json.addProperty("hiddenGene", entity.getHiddenGene().name());

        return json;
    }

}
