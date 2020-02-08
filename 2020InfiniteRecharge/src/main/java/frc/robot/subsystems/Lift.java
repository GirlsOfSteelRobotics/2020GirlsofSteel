package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lift extends SubsystemBase {

    private final WPI_TalonSRX m_motor;

    public Lift() {
        m_motor = new WPI_TalonSRX(Constants.LIFT_TALON);
        m_motor.configFactoryDefault();
        m_motor.setInverted(false);
    } 

    public void liftUp() {
        m_motor.set(ControlMode.PercentOutput, 1);
    }

    public void liftDown() {
        m_motor.set(ControlMode.PercentOutput, -1);
    }

    public void stop() {
        m_motor.set(ControlMode.PercentOutput, 0);
    }

}
