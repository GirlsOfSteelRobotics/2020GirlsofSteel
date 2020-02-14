package frc.robot.lib;

public interface SmartSpeedController {

    void setMotorPercentage(double motorPercentage);

    double getMotorPercentage();

    void setP(double p);

    void setI(double i);

    void setD(double d);

    void setFF(double ff);
}
