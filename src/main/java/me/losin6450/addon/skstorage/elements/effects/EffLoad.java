package me.losin6450.addon.skstorage.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.losin6450.addon.skstorage.SkStorage;
import org.bukkit.event.Event;


public class EffLoad extends Effect {

    static {
        Skript.registerEffect(EffLoad.class, "[SkStorage] load %string% as %string%");
    }

    private Expression<String> path;
    private Expression<String> name;
    @Override
    protected void execute(Event e) {
        try {
            int result = SkStorage.getInstance().load(path.getSingle(e), name.getSingle(e));
            if(result != 0){
                switch (result){
                    case 1:
                        throw new Exception("There is already a loaded file with that name");
                    case 2:
                        throw new Exception("The file extension cannot be loaded");
                    case 3:
                        throw new Exception("An error occured while trying to create the file");
                    case 4:
                        throw new Exception("An error occured while creating the Configuration for the file");
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        path = (Expression<String>) exprs[0];
        name = (Expression<String>) exprs[1];
        return true;
    }
}
