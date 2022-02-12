package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveTilt extends CommandBase {
    private  double speed;
    public MoveTilt(double speed) {
        addRequirements(RobotContainer.getTilt());
        this.speed = speed;

    }
    @Override
    public void execute() {
        RobotContainer.getTilt().setSpeed(speed);
    }
    @Override
    public boolean isFinished() {
        if (RobotContainer.getTilt().getLimit() || (!RobotContainer.getJoy().getRawButton(Constants.TILT_DOWN_BUTTON) && !RobotContainer.getJoy().getRawButton(Constants.TILT_UP_BUTTON))) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void end(boolean interupted) {
          RobotContainer.getTilt().stop();

    }


    
}
