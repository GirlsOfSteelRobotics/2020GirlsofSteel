package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {

	private final CANSparkMax masterLeft;
	private final CANSparkMax followerLeft;

	private final CANSparkMax masterRight;
	private final CANSparkMax followerRight;

	private final CANEncoder rightEncoder;
	private final CANEncoder leftEncoder;

	private final PigeonIMU m_pigeon;
	
	private final DifferentialDrive drive;

	private final DifferentialDriveOdometry m_odometry;

	private final double[] m_Angles = new double[3];


	public Chassis () {
		masterLeft = new CANSparkMax(Constants.DRIVE_LEFT_MASTER_SPARK, MotorType.kBrushed);
		followerLeft = new CANSparkMax(Constants.DRIVE_LEFT_FOLLOWER_SPARK, MotorType.kBrushed);

		masterRight = new CANSparkMax(Constants.DRIVE_RIGHT_MASTER_SPARK, MotorType.kBrushed); 
		followerRight = new CANSparkMax(Constants.DRIVE_RIGHT_FOLLOWER_SPARK, MotorType.kBrushed); 

		rightEncoder = masterRight.getEncoder();
		leftEncoder = masterLeft.getEncoder();
		
		m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
		
		m_pigeon = new PigeonIMU(0);
		
		//masterLeft.setNeutralMode(NeutralMode.Brake);
		//followerLeft.setNeutralMode(NeutralMode.Brake);

		//masterRight.setNeutralMode(NeutralMode.Brake);
		//followerRight.setNeutralMode(NeutralMode.Brake);

		// inverted should be true for Laika
		// masterLeft.setInverted(true);
		// followerLeft.setInverted(true);

		// masterRight.setInverted(true);
		// followerRight.setInverted(true);
		
		followerLeft.follow(masterLeft, false);
		followerRight.follow(masterRight, false);
		
		drive = new DifferentialDrive(masterLeft, masterRight);
		drive.setSafetyEnabled(true);
		drive.setExpiration(0.1);
		drive.setMaxOutput(0.8);
	}

	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

	@Override
	public void periodic(){
		m_odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getPosition(),
		rightEncoder.getPosition());

		m_pigeon.getAccumGyro(m_Angles);
		
		SmartDashboard.putNumber("x", m_odometry.getPoseMeters().getTranslation().getX());
		SmartDashboard.putNumber("y", m_odometry.getPoseMeters().getTranslation().getY());
		SmartDashboard.putNumber("yaw", getHeading());
		SmartDashboard.putNumber("right encoder", getRightEncoder());
		SmartDashboard.putNumber("left encoder", getLeftEncoder());

	}
	
	public CANSparkMax getLeftSparkMax() {
		return masterLeft;
	}

	public CANSparkMax getRightSparkMax(){
		return masterRight;
	}

	public double getLeftEncoder() {
		return rightEncoder.getPosition();
	  }
	  
	 public double getRightEncoder() {
		return leftEncoder.getPosition();
	  }	

	  public double getAverageEncoderDistance() {
		return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
	  }

	  private double getHeading() {
		return m_Angles[0];
	}

	public Pose2d getPose() {
		return m_odometry.getPoseMeters();
	  }
    
    public void driveByJoystick(final double yDir, final double xDir) {
		SmartDashboard.putString("driveByJoystick?", yDir + "," + xDir); 
		final double forward = yDir*Math.abs(yDir);
    	drive.arcadeDrive(forward, xDir);
	}
	
	public void setSpeed(final double speed){
		drive.arcadeDrive(speed, 0);
	}

	public void setSpeedAndSteer(double speed, double steer){
		drive.arcadeDrive(speed, steer);
	}
	
    public void stop() {
    	drive.stopMotor(); 
	}
}