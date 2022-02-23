// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveElevator extends CommandBase {
  double speed;
  public static double leftLimit= 6259.75;
  public static double rightLimit=-6264.0;

  /** Creates a new MoveElevtor. */
  public MoveElevator(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.getElevator());
 this.speed=speed;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(speed > 0 && (RobotContainer.getElevator().getLeftEncoder().getDistance() <= leftLimit && RobotContainer.getElevator().getRightEncoder().getDistance() >= rightLimit))
        {
            RobotContainer.getElevator().setElevatorSpeed(speed);
        }
        if(speed < 0 && (!RobotContainer.getElevator().getLeftLimit() || !RobotContainer.getElevator().getRightLimit()))
        {
            RobotContainer.getElevator().setElevatorSpeed(speed);
        }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(speed > 0 && (RobotContainer.getElevator().getLeftEncoder().getDistance() <= leftLimit && RobotContainer.getElevator().getRightEncoder().getDistance() >= rightLimit))
    {
        RobotContainer.getElevator().setElevatorSpeed(speed);
    }
    if(speed < 0 && (!RobotContainer.getElevator().getLeftLimit() || !RobotContainer.getElevator().getRightLimit()))
    {
        RobotContainer.getElevator().setElevatorSpeed(speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.getElevator().stopElevatorMotors();
  }

  public boolean getEncoderLimits()
  {
      return RobotContainer.getElevator().getLeftEncoder().getDistance() >= leftLimit && RobotContainer.getElevator().getRightEncoder().getDistance() <= rightLimit;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if(speed>0)
        {
            return getEncoderLimits() || !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON);
        }
        else
        {
            return (RobotContainer.getElevator().getLeftLimit() && RobotContainer.getElevator().getRightLimit()) 
                || !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON);
        }
  
  }
}
