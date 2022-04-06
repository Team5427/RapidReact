package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveRightArm extends CommandBase{
    private double speed;


    public MoveRightArm(double speed){
        addRequirements(RobotContainer.getTeleR());
        this.speed = speed;
    }

    @Override
    public void initialize(){
        

    }

    @Override 
    public void execute(){
        
        RobotContainer.getTeleR().extendRight(speed);

        

    }

    @Override
    public boolean isFinished(){
        return RobotContainer.getJoy().getRawButton(Constants.MANUAL_ARM_RIGHT_DOWN_BUTTON_2) && !RobotContainer.getJoy().getRawButton(Constants.MANUAL_ARM_RIGHT_UP_BUTTON_2);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTeleR().stopExtend();
    }
}
