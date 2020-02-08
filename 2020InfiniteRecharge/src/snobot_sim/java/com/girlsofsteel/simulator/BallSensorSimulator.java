package com.girlsofsteel.simulator;

import com.snobot.simulator.SensorActuatorRegistry;
import com.snobot.simulator.module_wrapper.interfaces.IDigitalIoWrapper;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class BallSensorSimulator {


    private final NetworkTable m_networkTable;
//    private final IDigitalIoWrapper m_wrapper;

    public BallSensorSimulator()
    {
        m_networkTable = NetworkTableInstance.getDefault().getTable("BallSensorSim");
        m_networkTable.getEntry("A").setBoolean(false);
        m_networkTable.getEntry("B").setBoolean(false);
        m_networkTable.getEntry("C").setBoolean(false);

//        m_wrapper = SensorActuatorRegistry.get().getDigitalSources().get(0);
    }

    public void update()
    {
        boolean buttonA = m_networkTable.getEntry("A").getBoolean(false);
        boolean buttonB = m_networkTable.getEntry("B").getBoolean(false);
        boolean buttonC = m_networkTable.getEntry("C").getBoolean(false);
        System.out.println(buttonA + ", " + buttonB + ", " + buttonC);
    }
}
