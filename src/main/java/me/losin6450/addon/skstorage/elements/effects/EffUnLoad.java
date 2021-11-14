package me.losin6450.addon.skstorage.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.losin6450.addon.skstorage.SkStorage;
import org.bukkit.event.Event;

public class EffUnLoad extends Effect {

    static {
        Skript.registerEffect(EffUnLoad.class, "[SkStorage] unload %strings%");
    }

    private Expression<String> names;

    @Override
    protected void execute(Event e) {
        SkStorage.getInstance().unload(names.getArray(e));
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        names = (Expression<String>) exprs[0];
        return true;
    }

}
