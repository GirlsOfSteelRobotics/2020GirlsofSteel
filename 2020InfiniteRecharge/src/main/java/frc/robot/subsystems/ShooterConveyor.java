package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterConveyor extends SubsystemBase {

    private final CANSparkMax m_master;
    // private final CANSparkMax m_follower;

    private final DigitalInput m_breakSensorHandoff;
    private final DigitalInput m_breakSensorSecondary;
    private final DigitalInput m_breakSensorTop;
    private final CANSparkMax m_follower;
    
    private final NetworkTable m_customNetworkTable;

    public ShooterConveyor() {
        m_master = new CANSparkMax(Constants.SHOOTER_CONVEYOR_SPARK_A, MotorType.kBrushless);
        m_follower = new CANSparkMax(Constants.SHOOTER_CONVEYOR_SPARK_B, MotorType.kBrushless);

        m_follower.follow(m_master);

        m_master.restoreFactoryDefaults();
        m_master.setSmartCurrentLimit(Constants.SPARK_MAX_CURRENT_LIMIT);
        m_master.setInverted(false);

        m_breakSensorHandoff = new DigitalInput(Constants.DIGITAL_INPUT_SENSOR_HANDOFF);
        m_breakSensorSecondary = new DigitalInput(Constants.DIGITAL_INPUT_SENSOR_SECONDARY);
        m_breakSensorTop = new DigitalInput(Constants.DIGITAL_INPUT_SENSOR_TOP);

        m_customNetworkTable = NetworkTableInstance.getDefault().getTable("SuperStructure/ShooterConveyor");
        // m_follower = new CANSparkMax(Constants.SHOOTER_CONVEYOR_SPARK_B,
        // MotorType.kBrushless);
        // m_follower.follow(m_master);
    }


    public void periodic() {
        SmartDashboard.putBoolean("Break Sensor Handoff: ", m_breakSensorHandoff.get());
        SmartDashboard.putBoolean("Break Sensor Secondary: ", m_breakSensorSecondary.get());
        SmartDashboard.putBoolean("Break Sensor Top", m_breakSensorTop.get());

        m_customNetworkTable.getEntry("Speed").setDouble(m_master.get());
        m_customNetworkTable.getEntry("Handoff Ball Sensor").setBoolean(m_breakSensorHandoff.get());
        m_customNetworkTable.getEntry("Secondary Ball Sensor").setBoolean(m_breakSensorSecondary.get());
        m_customNetworkTable.getEntry("Top Ball Sensor").setBoolean(m_breakSensorTop.get());
    }

    public boolean getHandoff() {
        return m_breakSensorHandoff.get();
    }

    public boolean getSecondary() {
        return m_breakSensorSecondary.get();
    }

    public boolean getTop() {
        return m_breakSensorTop.get();
    }

    public void inConveyor() {
        m_master.set(1);
    }

    public void outConveyor() {
        m_master.set(-1);
    }

    public void stop() {
        m_master.set(0);
    }
}
