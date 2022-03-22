package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveElevator extends CommandBase{

    private double speed;

    public MoveElevator(double speed){
        addRequirements(RobotContainer.getElevator());
        this.speed = speed;
    }
    @Override
    public void initialize(){
        
    }

    @Override 
    public void execute(){
        
        if(RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON_2))
        {
            if(RobotContainer.getElevator().getElevatorEncoderLimit())
            {
                RobotContainer.getElevator().stop();
            } else {
                RobotContainer.getElevator().move(speed);
            }

        } else if (RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON_2)) {
            if (RobotContainer.getElevator().getElevatorLimit()) {
                RobotContainer.getElevator().stop();
            } else {
                RobotContainer.getElevator().move(speed);
            }
        }
    }    


    @Override
    public boolean isFinished(){
        return !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON_2) && !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON_2); 
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getElevator().stop();
    }
}
