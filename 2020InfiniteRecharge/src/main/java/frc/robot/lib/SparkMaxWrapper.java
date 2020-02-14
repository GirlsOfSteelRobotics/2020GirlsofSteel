package frc.robot.lib;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class SparkMaxWrapper implements SmartSpeedController {
    CANSparkMax m_sparkMaxWrapper;
    CANPIDController m_canIDController;
    private final CANEncoder m_encoder;
    
    public SparkMaxWrapper(int id, MotorType motorType) {
        m_sparkMaxWrapper = new CANSparkMax(id, motorType);
        m_canIDController = m_sparkMaxWrapper.getPIDController();
        m_sparkMaxWrapper.restoreFactoryDefaults();
        m_sparkMaxWrapper.setSmartCurrentLimit(Constants.SPARK_MAX_CURRENT_LIMIT);
        m_encoder  = m_sparkMaxWrapper.getEncoder();
    }

    @Override
    public void setMotorPercentage(double motorPercentage) {
        m_sparkMaxWrapper.set(motorPercentage);
    }

    @Override
    public double getMotorPercentage() {
        return m_sparkMaxWrapper.get();
    }

    @Override
    public void setP(double p) {
        m_canIDController.setP(p);
    }

    @Override
    public void setI(double i) {
        m_canIDController.setI(i);
    }

    @Override
    public void setD(double d) {
        m_canIDController.setD(d);
    }

    @Override
    public void setFF(double ff) {
        m_canIDController.setFF(ff);
    }

    @Override
    public void setVelocityRPM(double rpm) {
       m_canIDController.setReference(rpm, ControlType.kVelocity);
    }

    @Override
    public double getVelocityRPM() {
        return m_encoder.getVelocity();
    }

    @Override
    public void follow(SmartSpeedController lead) {
      ((SparkMaxWrapper) lead).follow(lead);

    }

    @Override
    public void setInverted(boolean isInverted) {
        m_sparkMaxWrapper.setInverted(isInverted);

    }
}
