package org.roovitechologies.phpx.lib;

import org.roovitechologies.phpx.exceptions.TypeException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author aNNiMON
 */
public class MapMemory implements Memory, Iterable<Map.Entry<Memory, Memory>> {
    
    public static final MapMemory EMPTY = new MapMemory(1);
    
    public static MapMemory merge(MapMemory map1, MapMemory map2) {
        final MapMemory result = new MapMemory(map1.size() + map2.size());
        result.map.putAll(map1.map);
        result.map.putAll(map2.map);
        return result;
    }

    private final Map<Memory, Memory> map;

    public MapMemory(int size) {
        this.map = new HashMap<>(size);
    }

    public MapMemory(Map<Memory, Memory> map) {
        this.map = map;
    }

    public ArrayMemory toPairs() {
        final int size = map.size();
        final ArrayMemory result = new ArrayMemory(size);
        int index = 0;
        for (Map.Entry<Memory, Memory> entry : map.entrySet()) {
            result.set(index++, new ArrayMemory(new Memory[] {
                entry.getKey(), entry.getValue()
            }));
        }
        return result;
    }
    
    @Override
    public int type() {
        return Types.MAP;
    }
    
    public int size() {
        return map.size();
    }
    
    public boolean containsKey(Memory key) {
        return map.containsKey(key);
    }

    public Memory get(Memory key) {
        return map.get(key);
    }

    public void set(String key, Memory value) {
        set(new StringMemory(key), value);
    }

    public void set(String key, Function function) {
        set(new StringMemory(key), new FunctionMemory(function));
    }
    
    public void set(Memory key, Memory value) {
        map.put(key, value);
    }
    
    @Override
    public Object raw() {
        return map;
    }
    
    @Override
    public int toInteger() {
        throw new TypeException("Cannot cast map to integer");
    }

    @Override
    public double toDouble() {
        throw new TypeException("Cannot cast map to number");
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public Iterator<Map.Entry<Memory, Memory>> iterator() {
        return map.entrySet().iterator();
    }
    
}
