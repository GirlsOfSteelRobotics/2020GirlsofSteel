/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterConveyor;

public class Conveyor extends CommandBase {
  private final ShooterConveyor m_shooterConveyor;
  private final boolean m_intake;

  public Conveyor(ShooterConveyor shooterConveyor, boolean intake) {
    this.m_shooterConveyor = shooterConveyor;
    this.m_intake = intake;

    super.addRequirements(shooterConveyor);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (m_intake) {
      m_shooterConveyor.inConveyor();
    }
    else {
      m_shooterConveyor.outConveyor();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_shooterConveyor.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
