package io.github.thebusybiscuit.mobcapturer;

import javax.annotation.Nonnull;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.mobcapturer.listeners.PelletListener;
import io.github.thebusybiscuit.mobcapturer.setup.Registry;
import io.github.thebusybiscuit.mobcapturer.setup.Setup;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;

import net.guizhanss.guizhanlibplugin.updater.GuizhanBuildsUpdaterWrapper;

/**
 * MobCapturer Slimefun addon
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public class MobCapturer extends JavaPlugin implements SlimefunAddon {

    private static MobCapturer instance;

    private Registry registry;

    @Nonnull
    public static MobCapturer getInstance() {
        return instance;
    }

    private static void setInstance(@Nonnull MobCapturer plugin) {
        instance = plugin;
    }

    @Nonnull
    public static Registry getRegistry() {
        return getInstance().registry;
    }

    @Override
    public void onEnable() {
        setInstance(this);

        Config cfg = new Config(this);
        new Metrics(this, 6672);

        if (cfg.getBoolean("options.auto-update") && getDescription().getVersion().startsWith("Build")) {
            GuizhanBuildsUpdaterWrapper.start(this, getFile(), "ybw0014", "MobCapturer-CN", "master", false);
        }

        registry = new Registry(cfg);

        Setup.setup();

        new PelletListener(this);
    }

    @Override
    @Nonnull
    public String getBugTrackerURL() {
        return "https://github.com/SlimefunGuguProject/MobCapturer/issues";
    }

    @Override
    @Nonnull
    public JavaPlugin getJavaPlugin() {
        return this;
    }
}
