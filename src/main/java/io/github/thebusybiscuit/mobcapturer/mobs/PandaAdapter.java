package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import net.guizhanss.guizhanlib.minecraft.helper.entity.PandaHelper;

import org.bukkit.ChatColor;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Panda.Gene;

public class PandaAdapter extends AnimalsAdapter<Panda> {

    public PandaAdapter() {
        super(Panda.class);
    }

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "主要基因: " + ChatColor.WHITE + PandaHelper.getGene(json.get("mainGene").getAsString()));
        lore.add(ChatColor.GRAY + "隐藏基因: " + ChatColor.WHITE + PandaHelper.getGene(json.get("hiddenGene").getAsString()));

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
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
