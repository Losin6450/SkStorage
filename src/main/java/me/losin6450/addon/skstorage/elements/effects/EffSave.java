package me.losin6450.addon.skstorage.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.losin6450.addon.skstorage.SkStorage;
import org.bukkit.event.Event;

public class EffSave extends Effect {

    static {
        Skript.registerEffect(EffSave.class, "[SkStorage] save %strings%");
    }

    private Expression<String> name;

    @Override
    protected void execute(Event e) {
        SkStorage.getInstance().save(name.getArray(e));
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        return true;
    }
}
