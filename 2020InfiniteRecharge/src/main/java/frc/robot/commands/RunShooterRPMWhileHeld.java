package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;


public class RunShooterRPMWhileHeld extends CommandBase {

    private final Shooter m_shooter;
    private final double m_goalRPM;

    public RunShooterRPMWhileHeld(Shooter shooter, double goalRPM) {
        this.m_shooter = shooter;
        this.m_goalRPM = goalRPM;

        super.addRequirements(shooter);
    }

    @Override
    public void initialize() {
        System.out.println("init RunShooterRPMWhileHeld");
    }

    @Override
    public void execute() {
        double goalRPM;
        goalRPM = SmartDashboard.getNumber("Tune RPM", 0);
        
        m_shooter.setRPM(goalRPM);
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.stop();
        System.out.println("end RunShooterRPMWhileHeld");

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
