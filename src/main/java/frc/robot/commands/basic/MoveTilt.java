package frc.robot.commands.basic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveTilt extends CommandBase {
    public MoveTilt() {
        addRequirements(RobotContainer.getTilt());

    }
    @Override
    public void initialize(){
        RobotContainer.getTilt().toggleTilt();

    }
    @Override
    public void execute() {
        // RobotContainer.getTilt().toggleTilt();
    }
    @Override
    public boolean isFinished() {
        return true;
        
    }
    @Override
    public void end(boolean interupted) {

    }


    
}
