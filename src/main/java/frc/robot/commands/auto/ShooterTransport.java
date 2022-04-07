package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class ShooterTransport extends ParallelCommandGroup{
    public ShooterTransport(){
        addCommands(new TimedShooter(5, 1), new TimedTransport(3));
    }
}
