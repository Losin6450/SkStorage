package me.losin6450.addon.skstorage;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.apache.commons.io.FilenameUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

public final class SkStorage extends JavaPlugin {

    private static SkriptAddon ADDON;
    private static SkStorage INSTANCE;
    private HashMap<String, Configuration> configurationHashMap;
    @Override
    public void onEnable() {
        configurationHashMap = new HashMap<String, Configuration>();
        getLogger().log(Level.INFO, "SkStorage has been enabled!");
        INSTANCE = this;
        ADDON = Skript.registerAddon(this);
        try {
            ADDON.loadClasses("me.losin6450.addon.skstorage.elements", "expressions", "effects");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "SkStorage has been disabled!");
    }

    public static SkStorage getInstance(){
        return INSTANCE;
    }

    public static SkriptAddon getAddonInstance(){
        return ADDON;
    }

    public int load(String path, String name) throws IOException {
        if(configurationHashMap.containsKey(name)) {
            return 1;
        }
        File file = new File(new File("").getAbsolutePath() + File.separator + path);
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
        Configuration conf = null;
        if(!file.exists()){
            file.getParentFile().mkdirs();
            boolean result = file.createNewFile();
            if(!result){
                return 3;
            }
        }
        switch (extension){
            case "yaml":
            case "yml":
                conf = new YamlConfiguration(file);
                break;
            case "toml":
                conf = new TomlConfiguration(file);
                break;
            case "json":
                conf = new JsonConfiguration(file);
                break;
            default:
                return 2;
        }
        if(conf != null) {
            configurationHashMap.put(name, conf);
            return 0;
        } else {
            return 4;
        }


    }

    public void save(String... names){
        for(String name : names){
            if(configurationHashMap.containsKey(name)){
                configurationHashMap.get(name).save();
            }
        }
    }

    public Object get(String key, String configurationid){
        if(configurationHashMap.containsKey(configurationid)) {
            return configurationHashMap.get(configurationid).get(key);
        }
        return null;
    }

    public void set(String key, String configurationid, Object... values){
        if(configurationHashMap.containsKey(configurationid)) {
            if (values.length > 1) {
                configurationHashMap.get(configurationid).set(key, values);
            } else {
                configurationHashMap.get(configurationid).set(key, values[0]);
            }
        }
    }

    public void unload(String... names){
        for(String name : names){
            configurationHashMap.remove(name);
        }
    }

    public void remove(String key, String name){
        if(configurationHashMap.containsKey(name)){
            configurationHashMap.get(name).remove(key);
        }
    }

    public void delete(String... names){
        for(String n : names){
            if(configurationHashMap.containsKey(n)){
                Configuration conf = configurationHashMap.get(n);
                conf.delete();
                configurationHashMap.remove(n);
            }
        }
    }
}
