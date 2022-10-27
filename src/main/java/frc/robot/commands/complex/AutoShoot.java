package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoShoot extends SequentialCommandGroup{
    public AutoShoot(boolean isAuto){
        addCommands(
            new ParallelCommandGroup(
                new Target(true, isAuto),
                new ShootDynamic(isAuto)
            )
        );
    }        
}
