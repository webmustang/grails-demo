package demo.asset.es6

import asset.pipeline.AssetCompiler
import asset.pipeline.AssetFile
import asset.pipeline.processors.BabelJsProcessor
import groovy.util.logging.Commons

@Commons
class FastBabelJsProcessor extends BabelJsProcessor {
    FastBabelJsProcessor(AssetCompiler precompiler) {
        super(precompiler)
    }

    @Override
    String process(String input, AssetFile assetFile) {
        def start = System.currentTimeMillis()
        def cache = FastBabelJsProcessorCache.getInstance()
        if (!cache.has(assetFile.path) || cache.isChanged(assetFile.path, input)) {
            cache.set(assetFile.path, input, super.process(input, assetFile))
        }
        log.debug("Duration: " + (System.currentTimeMillis() - start) + "\tFile: " + assetFile.name + "")
        return cache.get(assetFile.path)
    }
}
