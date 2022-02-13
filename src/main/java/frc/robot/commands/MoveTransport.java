package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveTransport extends CommandBase {
    private double speed;

    public MoveTransport(double speed){

           addRequirements(RobotContainer.getTransport());
           this.speed = speed;
       }

    @Override
    public void execute(){
        RobotContainer.getTransport().move(speed);
    }

    @Override
    public boolean isFinished(){
        if(RobotContainer.getJoy().getRawButton(Constants.TRANSPORT_BUTTON)) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interupted){
        RobotContainer.getTransport().stop();
    }
    
}
