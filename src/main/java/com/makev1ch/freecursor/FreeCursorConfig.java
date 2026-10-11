package com.makev1ch.freecursor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FreeCursorConfig {
    private static final String CONFIG_FILE_NAME = "freecursor.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    public boolean simulateF1 = false;
    public boolean disableBlur = false;
    // MacroTech patch (mt3, 2026-10-11): hide glow outlines while the cursor is free
    public boolean hideGlow = false;
    
    private static FreeCursorConfig instance;
    
    public static FreeCursorConfig getInstance() {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }
    
    public static FreeCursorConfig load() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME);
        
        if (Files.exists(configPath)) {
            try (Reader reader = Files.newBufferedReader(configPath)) {
                FreeCursorConfig config = GSON.fromJson(reader, FreeCursorConfig.class);
                if (config != null) {
                    FreeCursor.LOGGER.info("FreeCursor config loaded");
                    return config;
                }
            } catch (Exception e) {
                FreeCursor.LOGGER.error("Failed to load FreeCursor config", e);
            }
        }
        
        FreeCursorConfig defaultConfig = new FreeCursorConfig();
        defaultConfig.save();
        return defaultConfig;
    }
    
    public void save() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FILE_NAME);
        
        try (Writer writer = Files.newBufferedWriter(configPath)) {
            GSON.toJson(this, writer);
            FreeCursor.LOGGER.info("FreeCursor config saved");
        } catch (Exception e) {
            FreeCursor.LOGGER.error("Failed to save FreeCursor config", e);
        }
    }
    
    public boolean isSimulateF1() {
        return simulateF1;
    }
    
    public void setSimulateF1(boolean simulateF1) {
        this.simulateF1 = simulateF1;
        save();
    }
    
    public boolean isDisableBlur() {
        return disableBlur;
    }
    
    public void setDisableBlur(boolean disableBlur) {
        this.disableBlur = disableBlur;
        save();
    }

    // MacroTech patch (mt3, 2026-10-11)
    public boolean isHideGlow() {
        return hideGlow;
    }

    public void setHideGlow(boolean hideGlow) {
        this.hideGlow = hideGlow;
        save();
    }
}
