package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.complex.AutoShoot;

public class UnbelievablyScuffedAuto extends SequentialCommandGroup{
    public UnbelievablyScuffedAuto(){
        addCommands(new AutoTiltDown(), new ForwardTimer(.4, 0.4),new Wait(1), new AutoShoot(true));
    
    }
}
