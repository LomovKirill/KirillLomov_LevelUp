package ru.levelp.at.homework7;

import java.util.HashMap;
import java.util.Map;

public final class TestContext {

    private static TestContext instance;

    private final Map<String, Object> context;

    private TestContext() {
        context = new HashMap<>();
    }

    public void addObject(String key, Object object) {
        context.put(key, object);
    }

    public Object getObject(String key) {
        return context.get(key);
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
}
