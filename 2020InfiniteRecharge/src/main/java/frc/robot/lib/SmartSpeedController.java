package frc.robot.lib;

public interface SmartSpeedController {

    void setMotorPercentage(double motorPercentage);

    void setVelocityRPM(double rpm);

    double getVelocityRPM();

    double getMotorPercentage();

    void setP(double p);

    void setI(double i);

    void setD(double d);

    void setFF(double ff);

	void follow(SmartSpeedController m_master);

	void setInverted(boolean b);
}
