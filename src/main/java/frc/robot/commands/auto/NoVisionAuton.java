package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class NoVisionAuton extends SequentialCommandGroup{
    public NoVisionAuton(){
        addCommands(new RampDrive(0.5, 0.1, 1.5, 0.6, true), new ForwardTimer(0.1, 0.6), new RampDrive(0.5, 0.6, 1.5, 0.1, false));
    }
}
