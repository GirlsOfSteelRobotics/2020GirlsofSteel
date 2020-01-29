package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class DriveToPoint extends CommandBase {

    Chassis chassis;

    private double m_distance;
    private double m_allowableError;
    private double m_errorDistance;

    private double m_angle;
    private double m_allowableErrorAngle;
    private double m_errorAngle;

    private double m_hyp;

    private double m_finalPositionX;
    private double m_finalPositionY;

    private double AUTO_KP = 0.05;
    private double AUTO_KP_ANGLE = 0.1;

	public DriveToPoint(Chassis chassis, double allowableError, double finalPositionX, double finalPositionY) {
		// Use requires() here to declare subsystem dependencies
        //super.addRequirements(Shooter); When a subsystem is written, add the requires line back in.
        this.chassis = chassis;

        m_finalPositionX = finalPositionX;
        m_finalPositionY = finalPositionY;
	}

    public void initialize(){
    }

	// Called repeatedly when this Command is scheduled to run
	public void execute() {

        double currentDistance;
        double currentAngle;
        double dx;
        double dy; 
        double angle;

        dx = m_finalPositionX - chassis.getX();
        dy = m_finalPositionY - chassis.getY();
        m_hyp = Math.sqrt((dx * dx) + (dy * dy));
        angle = Math.toDegrees(Math.atan2(dy, dx));

        currentDistance = chassis.getAverageEncoderDistance();
        m_errorDistance = m_hyp;
        double speed = m_errorDistance * AUTO_KP;

        currentAngle = chassis.getHeading();
        m_errorAngle = angle - currentAngle;
        double turnSpeed = m_errorAngle * AUTO_KP;

        System.out.println("angle" + angle + "hyp" + m_hyp + "speed" + speed + "turnSpeed" + turnSpeed);

        chassis.setSpeedAndSteer(speed, turnSpeed);

	}

	// Make this return true when this Command no longer needs to run execute()
	public boolean isFinished() {
        if(m_hyp < m_allowableError) {
            System.out.println("Done!");
            return true;
        }
        else {
            return false;
        }
	}

	// Called once after isFinished returns true
	public void end(boolean interrupted) {
        chassis.setSpeedAndSteer(0, 0);
  }
}