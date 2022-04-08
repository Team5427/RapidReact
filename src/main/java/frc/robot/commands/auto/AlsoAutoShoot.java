package frc.robot.commands.auto;

import java.lang.annotation.Target;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DynamicShooting;

public class AlsoAutoShoot extends SequentialCommandGroup{
    public AlsoAutoShoot(){
        addCommands(
            new ParallelCommandGroup(
                new TargetVision(true),
                new DynamicShooting()
            )
        );
    }
}