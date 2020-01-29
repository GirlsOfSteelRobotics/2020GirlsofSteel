/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class OuterShootAlign extends CommandBase {

  private final Limelight m_limelight;
  private final Chassis m_chassis;

  public OuterShootAlign(Chassis chassis, Limelight limelight) {
    this.m_limelight = limelight;
    this.m_chassis = chassis;

    addRequirements(limelight, chassis);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    //chassis.setSpeedAndSteer(limelight.getDriveCommand(), limelight.getSteerCommand());
    m_chassis.setSteer(m_limelight.getSteerCommand());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
