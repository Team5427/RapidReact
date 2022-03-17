package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveToRange;
import frc.robot.commands.ShooterTransport;

public class AutoShoot extends SequentialCommandGroup{
    public AutoShoot(){
        addCommands(new TargetVision(true), new DriveToRange(), new ShooterTransport());
    }
}
