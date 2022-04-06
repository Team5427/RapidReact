package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveArm extends CommandBase{
    private double speed;


    public MoveArm(double speed){
        addRequirements(RobotContainer.getTeleArmR(), RobotContainer.getTeleArmL());
        this.speed = speed;
    }

    @Override
    public void initialize(){
        //Code assumes -speed is down
        //assumes false from limit is not pressed
        // if(((RobotContainer.getJoy().getRawButton(Constants.ARM_EXTEND_DOWN_BUTTON_2)) && (!RobotContainer.getTeleArmR().getRightEncoderLimit() && !RobotContainer.getTeleArmR().getLeftEncoderLimit()))) {
        //     RobotContainer.getTeleArmR().moveArm(speed);
        // }
        // else if((RobotContainer.getJoy().getRawButton(Constants.ARM_EXTEND_DOWN_BUTTON_2))){
        //     RobotContainer.getTeleArmR().moveArm(speed);
        // }
        

    }

    @Override 
    public void execute(){
        // if(((RobotContainer.getJoy().getRawButton(Constants.ARM_EXTEND_DOWN_BUTTON_2)) && (RobotContainer.getTeleArmR().getRightEncoderLimit() || RobotContainer.getTeleArmR().getLeftEncoderLimit()))) {
        //     RobotContainer.getTeleArmR().stopExtend();
        // }
        RobotContainer.getTeleArmR().extendRight(speed);
        RobotContainer.getTeleArmL().extendLeft(speed);

        

    }

    @Override
    public boolean isFinished(){
        return RobotContainer.getJoy().getRawButton(Constants.ARM_EXTEND_DOWN_BUTTON_2) && !RobotContainer.getJoy().getRawButton(Constants.ARM_EXTEND_UP_BUTTON_2);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTeleArmR().stopExtend();
        RobotContainer.getTeleArmL().stopExtend();
    }
}
