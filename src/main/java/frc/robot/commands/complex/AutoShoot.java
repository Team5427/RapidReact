package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auton.TimedTransport;

public class AutoShoot extends SequentialCommandGroup{
    public AutoShoot(boolean isAuto){
        addCommands(
            new ParallelCommandGroup(
                new Target(true, isAuto), 
                new TimedTransport(.3, -.2),
                new ShootDynamic(isAuto)
            )
        );
    }        
}
