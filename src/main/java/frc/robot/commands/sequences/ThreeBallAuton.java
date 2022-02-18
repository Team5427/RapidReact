package frc.robot.commands.sequences;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.*;

public class ThreeBallAuton extends SequentialCommandGroup{
    public ThreeBallAuton(){
        addCommands(new IntakeVision(0.2, 0.3, 0.5, true), new ForwardAuto(.5, .4), new ShootVision(0.5, 0.2, 0.15, true), new Wait(1.5), new PointTurn(300, 0.15, 0.4), new Wait(0.5), new IntakeVision(0.3, 0.4, 0.5, true), new ForwardAuto(.5, .4), new ShootVision(0.5, 0.2, 0.15, true));
        //, new VisionTurn(0, true), new Wait(4))
    }
}
