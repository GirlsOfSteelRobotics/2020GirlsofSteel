package com.gos.infinite_recharge.sd_widgets.super_structure.data;

import com.gos.infinite_recharge.sd_widgets.SmartDashboardNames;
import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.HashMap;
import java.util.Map;

public class ShooterConveyorData extends ComplexData<ShooterConveyorData> {

    private final double m_speed;


    public ShooterConveyorData() {
        this(0.0);
    }

    public ShooterConveyorData(Map<String, Object> map) {
        this("", map);
    }

    public ShooterConveyorData(String prefix, Map<String, Object> map) {
        this((Double) map.getOrDefault(prefix + "/" + SmartDashboardNames.CONTROL_PANEL_SPEED, 0.0));
    }

    public ShooterConveyorData(double speed) {
        m_speed = speed;
    }

    @Override
    public Map<String, Object> asMap() {
        return asMap("");
    }

    public Map<String, Object> asMap(String prefix) {
        Map<String, Object> map = new HashMap<>();
        map.put(prefix + SmartDashboardNames.CONTROL_PANEL_SPEED, m_speed);
        return map;
    }

    public static boolean hasChanged(Map<String, Object> changes) {
        return hasChanged(SmartDashboardNames.CONTROL_PANEL_TABLE_NAME + "/", changes);
    }

    public static boolean hasChanged(String prefix, Map<String, Object> changes) {
        boolean changed = false;
        changed |= changes.containsKey(prefix + SmartDashboardNames.CONTROL_PANEL_SPEED);

        return changed;
    }

    public double getSpeed() {
        return m_speed;
    }
}
