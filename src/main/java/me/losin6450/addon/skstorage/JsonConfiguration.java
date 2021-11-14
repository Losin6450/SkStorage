package me.losin6450.addon.skstorage;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonConfiguration implements Configuration {

    private File file;
    private Map map;

    public JsonConfiguration(File path){
        file = path;
        try {
            Map obj = new ObjectMapper().readValue(path, HashMap.class);
            if(obj == null){
                map = new HashMap();
            } else {
                map = obj;
            }
        } catch (IOException e){
            map = new HashMap();
            e.printStackTrace();
        }
    }
    @Override
    public void save() {
        try {
            new ObjectMapper().writeValue(file, map);
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
