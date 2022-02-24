package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutoArmExtend extends CommandBase{
    private double speed;
    private double leftLimit = 0;
    private double rightLimit = 0;

    public AutoArmExtend(double speed){
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
        if(speed > 0 && (RobotContainer.getTelescopicArm().getLeftEncoder() < leftLimit || RobotContainer.getTelescopicArm().getRightEncoder() < rightLimit)){
            RobotContainer.getTelescopicArm().move_Arm(speed);
        }
        

    }

    @Override 
    public void execute(){
        if(speed < 0 && (RobotContainer.getTelescopicArm().getLeftLimit() || RobotContainer.getTelescopicArm().getRightLimit())){
            RobotContainer.getTelescopicArm().stopExtend();
        }
        if(speed > 0 && (RobotContainer.getTelescopicArm().getLeftEncoder() >= leftLimit || RobotContainer.getTelescopicArm().getRightEncoder() >= rightLimit)){
            RobotContainer.getTelescopicArm().stopExtend();
        }
    }

    @Override
    public boolean isFinished(){
        if(speed < 0 && (RobotContainer.getTelescopicArm().getLeftLimit() || RobotContainer.getTelescopicArm().getRightLimit())){
            return true;
        }
        if(speed > 0 && (RobotContainer.getTelescopicArm().getLeftEncoder() >= leftLimit || RobotContainer.getTelescopicArm().getRightEncoder() >= rightLimit)){
            return true;
        }    

        return false;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTelescopicArm().stopExtend();
    }
}
