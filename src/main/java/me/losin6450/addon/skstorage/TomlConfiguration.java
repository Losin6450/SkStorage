package me.losin6450.addon.skstorage;


import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TomlConfiguration implements Configuration{

    private File file;
    private Map map;

    public TomlConfiguration(File path) throws IOException {

        file = path;
        map = new Toml().read(file).toMap();
    }
    @Override
    public void save() {
        try {
            new TomlWriter().write(map, file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public void set(String key, Object value) {
        map.put(key, value);
    }

    @Override
    public void remove(String key) {
        map.remove(key);
    }

    @Override
    public void delete() {
        file.delete();
    }
}
