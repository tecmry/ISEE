package com.example.mao.beautylife.event;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.LocationSource;

/**
 * Created by -- Mao on 2017/12/22.
 */

public class MapEvent {

    private AMapLocation location;

    public AMapLocation getLocation() {
        return location;
    }

    public void setLocation(AMapLocation location) {
        this.location = location;
    }
}
