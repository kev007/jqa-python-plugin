package org.jqassistant.contrib.plugin.python.impl.scanner.walker;

import com.buschmais.jqassistant.core.scanner.api.ScannerContext;
import com.buschmais.jqassistant.core.store.api.model.Descriptor;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.jqassistant.contrib.plugin.python.api.model.PythonFile;
import org.jqassistant.contrib.plugin.python.api.model.PythonPackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * The helper delegates creation and caching of store objects
 *
 * @author Kevin M. Shrestha
 */
@AllArgsConstructor
public class StoreHelper {
    private PythonPackage packageDescriptor;
    private PythonFile pythonFile;
    private ScannerContext scannerContext;
    private Cache cache = new Cache();

    public StoreHelper(final PythonPackage packageDescriptor,
            final PythonFile pythonFile,
            final ScannerContext scannerContext) {
        this.packageDescriptor = packageDescriptor;
        this.pythonFile = pythonFile;
        this.scannerContext = scannerContext;

        cache.put(this.packageDescriptor.getName(), new RuleCache());
    }

    public <T extends Descriptor> T createAndCache(Class<T> fileClass, ParserRuleContext ctx) {
        T storeObject = scannerContext.getStore().create(fileClass);
        int ruleIndex = ctx.getRuleIndex();

        RuleCache ruleCache = cache.get(pythonFile.getName());
        if (!ruleCache.containsKey(ruleIndex)) {
            ruleCache.put(ruleIndex, new ContextEntityCache());
        }
        ContextEntityCache contextEntityCache = ruleCache.get(ruleIndex);

        contextEntityCache.add(new ContextEntity(ctx, storeObject));

        return storeObject;
    }

    public ContextEntityCache getCacheByRuleIndex(int ruleIndex) {
        RuleCache ruleCache = cache.get(pythonFile.getName());
        return ruleCache.get(ruleIndex);
    }

    public Set<Integer> getCacheKeySet() {
        return cache.get(pythonFile.getName()).keySet();
    }
}

class Cache extends HashMap<String, RuleCache> {}

class RuleCache extends TreeMap<Integer, ContextEntityCache> {}

class ContextEntityCache extends HashSet<ContextEntity> {}

