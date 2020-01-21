package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterConveyor extends SubsystemBase {

    private final CANSparkMax m_motor;

	public ShooterConveyor () {
        m_motor = new CANSparkMax(Constants.SHOOTER_CONVEYOR_SPARK, MotorType.kBrushless);
    } 

    public void moveConveyor(){
        m_motor.set(1);
    }

    public void stop(){
        m_motor.set(0);
    }
}