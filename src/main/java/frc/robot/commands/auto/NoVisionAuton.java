package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoVisionAuton extends SequentialCommandGroup{
    public NoVisionAuton(){
        addCommands(new RampDrive(0.5, 0.1, 1.5, 0.6));
    }
}
