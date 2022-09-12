package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoShoot extends SequentialCommandGroup{
    public AutoShoot(boolean isAuto){
        addCommands(
                new ParallelCommandGroup(
                    new TargetVisionAutoShoot(true), 
                    new SequentialCommandGroup(
                        new BumpTransport(.25, -.25),
                        new DynamicShooterTransport(isAuto)
                    )
                )
        );
    }

        
}
