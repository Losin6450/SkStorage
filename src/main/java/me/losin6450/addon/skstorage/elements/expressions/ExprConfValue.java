package me.losin6450.addon.skstorage.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.losin6450.addon.skstorage.SkStorage;
import org.bukkit.event.Event;

import java.util.List;


public class ExprConfValue extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprConfValue.class, Object.class, ExpressionType.COMBINED, "[SkStorage] [config] value %string% of %string%");
    }

    private Expression<String> key;
    private Expression<String> name;
    private int pattern;

    @Override
    protected Object[] get(Event e) {
        Object value = SkStorage.getInstance().get(key.getSingle(e), name.getSingle(e));
        if(value instanceof List){
            return ((List<?>) value).toArray();
        } else {
            return new Object[] {value};
        }
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<?> getReturnType() {
        return Object.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        key = (Expression<String>) exprs[0];
        name = (Expression<String>) exprs[1];
        pattern = matchedPattern;
        return true;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        String k = key.getSingle(e);
        String n = name.getSingle(e);
        if(k != null && n != null){
            if(mode == Changer.ChangeMode.SET){
                SkStorage.getInstance().set(k, n, delta);
            } else if (mode == Changer.ChangeMode.DELETE){
                SkStorage.getInstance().remove(k, n);
            }
        }

    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if(mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE){
            return CollectionUtils.array(Object[].class);
        }
        return null;
    }

}
