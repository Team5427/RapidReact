package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveLeftArm extends CommandBase{
    private double speed;


    public MoveLeftArm(double speed){
        addRequirements(RobotContainer.getTelescopicArm());
        this.speed = speed;
    }

    @Override
    public void initialize(){
        

    }

    @Override 
    public void execute(){
        
        RobotContainer.getTelescopicArm().extendLeft(speed);

        

    }

    @Override
    public boolean isFinished(){
        return RobotContainer.getJoy().getRawButton(Constants.MANUAL_ARM_LEFT_DOWN_BUTTON_2) && !RobotContainer.getJoy().getRawButton(Constants.MANUAL_ARM_LEFT_UP_BUTTON_2);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTelescopicArm().stopExtend();
    }
}
