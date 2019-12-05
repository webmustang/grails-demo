package demo.asset.es6

class FastBabelJsProcessorCache {

    private Map<String, FastBabelJsProcessedContainer> cache

    static FastBabelJsProcessorCache instance

    static FastBabelJsProcessorCache getInstance() {
        if (!instance) {
            instance = new FastBabelJsProcessorCache()
        }

        return instance
    }

    boolean has(String key) {
        return getCache().containsKey(key)
    }

    boolean isChanged(String key, String input) {
        getCache().get(key)?.input != input
    }

    void set(String key, String input, String value) {
        getCache().put(key, new FastBabelJsProcessedContainer(value, input))
    }

    String get(String key) {
        getCache().get(key).value
    }

    private Map<String, FastBabelJsProcessedContainer> getCache() {
        if (!cache) {
            cache = new HashMap<String, FastBabelJsProcessedContainer>()
        }

        cache
    }
}
