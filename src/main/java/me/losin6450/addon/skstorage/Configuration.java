package me.losin6450.addon.skstorage;


import java.util.List;

public interface Configuration {

    void save();

    Object get(String key);

    void set(String key, Object value);

    void remove(String key);

    void delete();
}
