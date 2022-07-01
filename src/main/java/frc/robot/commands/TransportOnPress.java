package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TransportOnPress extends CommandBase{
    private double speed;

    public TransportOnPress(double speed){

        addRequirements(RobotContainer.getTransport());
        this.speed = speed;
    }

    @Override
    public void execute(){
        if(RobotContainer.getJoy().getRawButton(Constants.TRANSPORT_MOVE_BUTTON) && RobotContainer.getJoy().getRawButton(Constants.TRANSPORT_BACK_BUTTON)) {
            RobotContainer.getTransport().move(speed);
        } else{
            RobotContainer.getTransport().stop();;
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interupted){
        RobotContainer.getTransport().stop();
    }
    
}
