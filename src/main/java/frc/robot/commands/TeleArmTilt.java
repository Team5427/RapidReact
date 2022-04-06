package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;


public class TeleArmTilt extends CommandBase {

    public TeleArmTilt() {
        addRequirements(RobotContainer.getArmTilt());
    }
    @Override
    public void initialize(){
        RobotContainer.getArmTilt().toggleArmTilt();
    }

    @Override 
    public void execute(){
    
    }

    @Override
    public boolean isFinished(){
        return true;   
    }

    @Override
    public void end(boolean interrupted){
    }
}
