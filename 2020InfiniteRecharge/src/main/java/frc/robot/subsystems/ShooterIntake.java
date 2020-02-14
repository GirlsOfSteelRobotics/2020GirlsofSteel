package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.lib.SmartSpeedController;
import frc.robot.lib.TalonSRXWrapper;

public class ShooterIntake extends SubsystemBase {

    private final SmartSpeedController m_motor;
    private final DoubleSolenoid m_piston;

    private final NetworkTable m_customNetworkTable;

    public ShooterIntake() {
        m_motor = new TalonSRXWrapper(Constants.SHOOTER_INTAKE_TALON);
        // m_motor.configFactoryDefault();
        // m_motor.setInverted(false);
        m_piston = new DoubleSolenoid(Constants.DOUBLE_SOLENOID_SHOOTER_INTAKE_FORWARD,
                Constants.DOUBLE_SOLENOID_SHOOTER_INTAKE_BACKWARD);

        m_customNetworkTable = NetworkTableInstance.getDefault().getTable("SuperStructure/ShooterIntake");
    }
    
    @Override
    public void periodic() {
        m_customNetworkTable.getEntry("Speed").setDouble(m_motor.getMotorPercentage());
        m_customNetworkTable.getEntry("Position").setBoolean(m_piston.get() == Value.kForward);
    }

    public void collectCells() {
        m_motor.setMotorPercentage(1);
    }

    public void decollectCells() {
        m_motor.setMotorPercentage(-1);
    }

    public void stop() {
        m_motor.setMotorPercentage(0);
    }

    public void moveToCollect() {
        m_piston.set(Value.kForward);
    }

    public void retract() {
        m_piston.set(Value.kReverse);
    }
}
