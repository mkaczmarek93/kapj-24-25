package pl.zt.mk.services;

import org.springframework.stereotype.Repository;

/**
 * Created by zbyszekt on 2016-06-05.
 */

public interface HeatCounterProvider {
    Double getHeatState();
}
