package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class CheckShooter extends CommandBase{
    @Override
    public boolean isFinished(){
        return !RobotContainer.getJoy().getRawButton(1);
    }
}
