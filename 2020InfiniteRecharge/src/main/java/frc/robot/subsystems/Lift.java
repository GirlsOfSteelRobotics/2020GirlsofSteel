package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.lib.SmartSpeedController;
import frc.robot.lib.TalonSRXWrapper;

public class Lift extends SubsystemBase {

    private final SmartSpeedController m_motor;

    private final NetworkTable m_customNetworkTable;

    public Lift() {
        m_motor = new TalonSRXWrapper(Constants.LIFT_TALON);
        // m_motor.configFactoryDefault();
        // m_motor.setInverted(false);

        m_customNetworkTable = NetworkTableInstance.getDefault().getTable("SuperStructure/Lift");
    } 

    public void liftUp() {
        m_motor.setMotorPercentage(1);
    }

    public void liftDown() {
        m_motor.setMotorPercentage(-1);
    }

    public void periodic() {
        m_customNetworkTable.getEntry("Speed").setDouble(m_motor.getMotorPercentage());
    }

    public void stop() {
        m_motor.setMotorPercentage(0);
    }

}
