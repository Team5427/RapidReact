package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveTilt extends CommandBase {
    private  double speed;
    public MoveTilt(double speed) {
        addRequirements(RobotContainer.getTilt());
        this.speed = speed;

    }
    @Override
    public void execute() {
        RobotContainer.getTilt().getTilt().set(speed);
    }
    @Override
    public boolean isFinished() {
        if (RobotContainer.getTilt().getLimit().get()) {
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
