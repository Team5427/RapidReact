package frc.robot.commands.TalonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ExampleCommand extends CommandBase {
    public ExampleCommand(String bah) {
        addRequirements();

    }
    @Override
    public void initialize(){

    }
    @Override
    public void execute() {
        // RobotContainer.getTilt().toggleTilt();
    }
    @Override
    public boolean isFinished() {
        return true;
        
    }
    @Override
    public void end(boolean interupted) {

    }


    
}
