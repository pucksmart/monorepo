package dev.pucksmart;

import dev.pucksmart.extract.nhlapi.ShiftsApi;
import dev.pucksmart.extract.nhlapi.StatsApi;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class GraalHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.proxies().registerJdkProxy(ShiftsApi.class);
        hints.proxies().registerJdkProxy(StatsApi.class);
    }
}
