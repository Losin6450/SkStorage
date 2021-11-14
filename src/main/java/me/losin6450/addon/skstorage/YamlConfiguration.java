package me.losin6450.addon.skstorage;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class YamlConfiguration implements Configuration{

    private org.bukkit.configuration.file.YamlConfiguration yaml;
    private File file;
    public YamlConfiguration(File path){
        file = path;
        yaml = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(path);
    }

    @Override
    public void save() {
        try {
            yaml.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key) {
        if(yaml.isList(key)){
            return yaml.getList(key);
        } else {
            return yaml.get(key);
        }
    }

    @Override
    public void set(String key, Object value) {
        yaml.set(key, value);
    }

    @Override
    public void remove(String key){
        yaml.set(key, null);
    }

    @Override
    public void delete(){
        file.delete();
    }

}
