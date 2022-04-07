package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.CheckShooter;

public class AutoShoot extends SequentialCommandGroup{
    public AutoShoot(){
        addCommands(
            new ParallelRaceGroup(
                new ParallelCommandGroup(
                    new TargetVision(true), 
                    new SequentialCommandGroup(
                        new BumpTransport(.1, -.25),
                        new TimedShooter(100)
                    )
                ),
                new CheckShooter()
            )
        );
    }
}
