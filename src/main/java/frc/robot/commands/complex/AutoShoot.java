package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auton.TimedTransport;

public class AutoShoot extends SequentialCommandGroup{
    public AutoShoot(boolean isAuto){
        addCommands(
                new ParallelCommandGroup(
                    new TargetVisionAutoShoot(true, isAuto), 
                    new SequentialCommandGroup(
                        new TimedTransport(.25, -.25),
                        new DynamicShooterTransport(isAuto)
                    )
                )
        );
    }


        
}
