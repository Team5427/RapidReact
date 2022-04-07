package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoShoot extends SequentialCommandGroup{
    public AutoShoot(){
        addCommands(new TargetVision(true), new DriveToRange());
    }
}
