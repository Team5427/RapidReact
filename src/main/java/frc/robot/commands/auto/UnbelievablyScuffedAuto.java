package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class UnbelievablyScuffedAuto extends SequentialCommandGroup{
    public UnbelievablyScuffedAuto(){
        addCommands(new AutoTiltDown(true), new ForwardTimer(.4, 0.4),new Wait(1), new AutoShoot(true));
    
    }
}
