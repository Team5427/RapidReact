package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.CheckShooter;

public class AutoAutoShoot extends SequentialCommandGroup{
    public AutoAutoShoot(){
        addCommands(
                new ParallelCommandGroup(
                    new SequentialCommandGroup(
                        new DynamicShooterTransport(true)
                    )
                )
        );
    }
}
