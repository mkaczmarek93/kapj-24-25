package pl.zt.mk.services.impl;

import org.neo4j.cypher.internal.compiler.v2_1.functions.Rand;
import org.springframework.stereotype.Service;
import pl.zt.mk.services.HeatCounterProvider;

import java.util.Random;

/**
 * Created by zbyszekt on 2016-06-05.
 */
@Service
public class HeatCounterProviderImpl implements HeatCounterProvider{
    @Override
    public Double getHeatState() {
        return new Random().nextDouble();
    }
}
