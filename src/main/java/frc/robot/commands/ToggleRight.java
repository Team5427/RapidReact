package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ToggleRight extends CommandBase {
    public ToggleRight() {
        addRequirements(RobotContainer.getTilt());

    }
    @Override
    public void execute() {
        RobotContainer.getTilt().toggleRight();;
    }
    @Override
    public boolean isFinished() {
        return true;
        
    }
    @Override
    public void end(boolean interupted) {

    }


    
}
