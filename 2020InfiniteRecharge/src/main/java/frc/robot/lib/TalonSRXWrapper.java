package frc.robot.lib;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSRXWrapper implements SmartSpeedController {
    TalonSRX m_talonSRX;

    public TalonSRXWrapper(int id) {
        m_talonSRX = new TalonSRX(id);
        m_talonSRX.configFactoryDefault();
    }

    @Override
    public void setMotorPercentage(double motorPercentage) {
        m_talonSRX.set(ControlMode.PercentOutput, motorPercentage);
    }

    @Override
    public double getMotorPercentage() {
        return m_talonSRX.getMotorOutputPercent();
    }

    @Override
    public void setP(double p) {
        m_talonSRX.config_kP(0, p);
    }

    @Override
    public void setI(double i) {
        m_talonSRX.config_kI(0, i);
    }

    @Override
    public void setD(double d) {
        m_talonSRX.config_kD(0, d);
    }

    @Override
    public void setFF(double ff) {
        m_talonSRX.config_kF(0, ff);
    }
}
