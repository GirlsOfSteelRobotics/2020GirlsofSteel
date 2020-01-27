package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.ShooterConveyor;
import frc.robot.OI;
import frc.robot.Robot;

public class AutoShoot extends CommandBase {
	Command autonomousCommand;
	Shooter shooter;
	ShooterConveyor shooterConveyor;
	OI OI;

	private double m_rpm;
	private double m_time;

	public AutoShoot(Shooter shooter, ShooterConveyor shooterConveyor, double rpm, double time) {
		this.shooter = shooter;
		this.shooterConveyor = shooterConveyor;
		m_rpm = rpm;
		m_time = time;
		//requires(Robot.Shooter);
		shooter = Robot.shooter;
		OI = Robot.OI;
		// Use requires() here to declare subsystem dependencies
		super.addRequirements(shooter); 
		super.addRequirements(shooterConveyor);
	}

    //private void requires(Object shooter2) {}

	public void initialize() {
    }

	// Called repeatedly when this Command is scheduled to run
	public void execute() { 
		//if(OI.XboxController.)
	}

	// Make this return true when this Command no longer needs to run execute()
	public boolean isFinished() {
        return false;
	}

	// Called once after isFinished returns true
	public void end(boolean interrupted) {
  }
}