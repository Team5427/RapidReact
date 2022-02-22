package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveArm extends CommandBase{
    private double speed;


    public MoveArm(double speed){
        addRequirements(RobotContainer.getTelescopicArm());
        this.speed = speed;
    }

    @Override
    public void initialize(){
        //Code assumes -speed is down
        //assumes false from limit is not pressed
        if(speed < 0 && (!RobotContainer.getTelescopicArm().getLeftLimit() || !RobotContainer.getTelescopicArm().getRightLimit())){
            RobotContainer.getTelescopicArm().move_Arm(speed);
        }

    }

    @Override 
    public void execute(){
        if(speed < 0 && (RobotContainer.getTelescopicArm().getLeftLimit() || RobotContainer.getTelescopicArm().getRightLimit())){
            RobotContainer.getTelescopicArm().stopExtend();;
        }

    }

    @Override
    public boolean isFinished(){
        if(speed > 0)
        {
            return RobotContainer.getTelescopicArm().getRightEncoder() >= rightLimit || RobotContainer.getTelescopicArm().getLeftEncoder() >= leftLimit|| !RobotContainer.getJoy().getRawButton(Constants.Ar);
        }
        else
        {
            return (RobotContainer.getElevator().getLimitLeft() && RobotContainer.getElevator().getLimitRight()) 
                || !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON);
        }    }

    @Override
    public void end(boolean interrupted){

    }
}
