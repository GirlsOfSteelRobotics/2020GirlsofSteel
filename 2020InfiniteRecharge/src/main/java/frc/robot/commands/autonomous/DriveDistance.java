package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class DriveDistance extends CommandBase {

    Chassis chassis;

    private double m_distance;
    private double m_allowableError;
    private double m_error;

	public DriveDistance(Chassis chassis, double distance, double error) {
		// Use requires() here to declare subsystem dependencies
        //super.addRequirements(Shooter); When a subsystem is written, add the requires line back in.
        this.chassis = chassis;

        m_distance = distance;
        m_error = error;
	}

    public void initialize(){
    }

	// Called repeatedly when this Command is scheduled to run
	public void execute() { 
        double currentDistance;
        currentDistance = chassis.getAverageEncoderDistance();

        if(currentDistance > m_distance) {
            chassis.setSpeed(1);
        }
        else {
            chassis.setSpeed(-1);
        }

        m_error = m_distance - currentDistance;
	}

	// Make this return true when this Command no longer needs to run execute()
	public boolean isFinished() {
        if(m_error < m_allowableError){
            return true;
        }
        else {
            return false;
        }
	}

	// Called once after isFinished returns true
	public void end(boolean interrupted) {
        chassis.setSpeed(0);
  }
}