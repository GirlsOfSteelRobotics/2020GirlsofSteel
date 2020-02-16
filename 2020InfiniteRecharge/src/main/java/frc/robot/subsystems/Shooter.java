package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    private static final double SHOOTER_KP = 0.0;
    private static final double SHOOTER_KFF = .000093; //1 / 10600.0;

    private static final double ALLOWABLE_ERROR_PERCENT = 1;          

    private static final int SLOT_ID = 0;

    private final CANSparkMax m_master;
    private final CANSparkMax m_follower;
    private final CANEncoder m_encoder;
    private CANPIDController m_pidController;

    private double goalRPM; 
    
    private final NetworkTable m_customNetworkTable;
    private final NetworkTableEntry m_dashboardKp;
    private final NetworkTableEntry m_dashboardKff;

    public Shooter() {
        m_master = new CANSparkMax(Constants.SHOOTER_SPARK_A, MotorType.kBrushed);
        m_follower = new CANSparkMax(Constants.SHOOTER_SPARK_B, MotorType.kBrushed);
        m_encoder  = m_master.getEncoder(EncoderType.kQuadrature, 2048);
        m_pidController = m_master.getPIDController();

        m_dashboardKp = SmartDashboard.getEntry("shooter_kp");
        m_dashboardKff = SmartDashboard.getEntry("shooter_kff");

        SmartDashboard.putNumber("shooter_kp", SHOOTER_KP);
        SmartDashboard.putNumber("shooter_kff", SHOOTER_KFF);
 
        m_master.restoreFactoryDefaults();

        m_master.setSmartCurrentLimit(Constants.SPARK_MAX_CURRENT_LIMIT);

        m_follower.follow(m_master, true);

        m_master.setInverted(false);

        m_pidController.setP(SHOOTER_KP);
        m_pidController.setFF(SHOOTER_KFF);
        m_encoder.setInverted(true);
        
        m_customNetworkTable = NetworkTableInstance.getDefault().getTable("SuperStructure/Shooter");
        NetworkTableInstance.getDefault().getTable("SuperStructure").getEntry(".type").setString("SuperStructure");
    } 

    
    public void setRPM(final double rpm) {
        goalRPM = rpm; 
        // m_master.set(rpm);
        m_pidController.setReference(rpm, ControlType.kVelocity);
        // double targetVelocityUnitsPer100ms = rpm * 4096 / 600;
        // m_master.set(0.7 /*targetVelocityUnitsPer100ms*/);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("RPM", getCurrentRpm());
        SmartDashboard.putNumber("Encoder Position", m_encoder.getPosition());
        m_customNetworkTable.getEntry("Speed").setDouble(m_master.get());
        m_customNetworkTable.getEntry("Current RPM").setDouble(getCurrentRpm());
        m_customNetworkTable.getEntry("Goal RPM").setDouble(goalRPM);
        

        m_pidController.setP(m_dashboardKp.getDouble(SHOOTER_KP));
        m_pidController.setFF(m_dashboardKff.getDouble(SHOOTER_KFF));
        System.out.println("kp: " + m_dashboardKp.getDouble(SHOOTER_KP) + ", " + m_dashboardKff.getDouble(SHOOTER_KFF) + " goal: " + goalRPM + "== " + getCurrentRpm());
    }

    private double getCurrentRpm()
    {
        return m_encoder.getVelocity();
    }

    public boolean isAtFullSpeed() {
        double currentRPM = getCurrentRpm();
        double percentError = (goalRPM - currentRPM) / goalRPM * 100;
        return Math.abs(percentError) <= ALLOWABLE_ERROR_PERCENT;
    }

    public void stop() {
        m_master.set(0);
        //m_pidController.setReference(0, ControlType.kVelocity);
    }
}
