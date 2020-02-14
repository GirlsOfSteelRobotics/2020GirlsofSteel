package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.lib.SmartSpeedController;
import frc.robot.lib.SparkMaxWrapper;

public class Shooter extends SubsystemBase {

    private static final double SHOOTER_KP = 0.1;
    private static final double SHOOTER_KFF = 0.00139;

    private static final double ALLOWABLE_ERROR_PERCENT = 1;          

    private final SmartSpeedController m_master;
    private final SmartSpeedController m_follower;
    // private final CANEncoder m_encoder;
    // private CANPIDController m_pidController;

    private double goalRPM; 
    
    private final NetworkTable m_customNetworkTable;

    public Shooter() {
        m_master = new SparkMaxWrapper(Constants.SHOOTER_SPARK_A, MotorType.kBrushed);
        m_follower = new SparkMaxWrapper(Constants.SHOOTER_SPARK_B, MotorType.kBrushed);
        
        //m_pidController = m_master.getPIDController();
 
        // m_master.restoreFactoryDefaults();

        // m_master.setSmartCurrentLimit(Constants.SPARK_MAX_CURRENT_LIMIT);

        m_follower.follow(m_master);

        m_master.setInverted(true);
        m_follower.setInverted(false);

        m_master.setP(SHOOTER_KP);
        m_master.setFF(SHOOTER_KFF);
        
        m_customNetworkTable = NetworkTableInstance.getDefault().getTable("SuperStructure/Shooter");
        NetworkTableInstance.getDefault().getTable("SuperStructure").getEntry(".type").setString("SuperStructure");
    } 

    
    public void setRPM(final double rpm) {
        goalRPM = rpm; 
        m_master.setVelocityRPM(rpm);
        //m_pidController.setReference(rpm, ControlType.kVelocity);
        // double targetVelocityUnitsPer100ms = rpm * 4096 / 600;
        // m_master.set(1.0 /*targetVelocityUnitsPer100ms*/);
    }

    @Override
    public void periodic() {
        //double rpm = m_encoder.getVelocity() * 600.0 / 4096;
        double rpm = m_master.getVelocityRPM();
        SmartDashboard.putNumber("RPM", rpm);
        m_customNetworkTable.getEntry("Speed").setDouble(m_master.getMotorPercentage());

    }

    public boolean isAtFullSpeed() {
        double currentRPM = m_master.getVelocityRPM();
        double percentError = (goalRPM - currentRPM) / goalRPM * 100;
        return Math.abs(percentError) <= ALLOWABLE_ERROR_PERCENT;
    }

    public void stop() {
        m_master.setMotorPercentage(0);
        //m_pidController.setReference(0, ControlType.kVelocity);
    }
}
