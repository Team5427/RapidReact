package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AutoTiltDown extends CommandBase {
    private boolean in;
    
    public AutoTiltDown(boolean in) {
        addRequirements(RobotContainer.getTilt());


    }

    @Override
    public void initialize(){
        RobotContainer.getTilt().toggleTilt();
    }

    @Override
    public void execute() {
    }
    @Override
    public boolean isFinished() {
        return true;
    }
    @Override
    public void end(boolean interupted) {

    }


    
}
