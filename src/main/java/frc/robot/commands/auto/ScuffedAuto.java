package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShooterTransport;

public class ScuffedAuto extends SequentialCommandGroup{
    public ScuffedAuto(){
        addCommands(new AutoTiltDown(true), new ForwardTimer(.4, 0.4),new Wait(1), new ShooterTransport());
    
    }
}
