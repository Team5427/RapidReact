package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonThreeBallsBeta extends SequentialCommandGroup{
    public AutonThreeBallsBeta(){
        addCommands(new BallVision(0, 0.4, 0.5), new TargetVision(0, true), new Wait(1), new PointTurn(210, true, 0.15, 0.6, 30), new Wait(0.1), new ForwardTimer(1, 0.65), new BallVision(0, 0.3, 0.5), new Wait(0.3), new ForwardTimer(0.4, -0.4), new Wait(0.2), new PointTurn(179, true, 0.15, 0.6, 35), new Wait(0.2), new ForwardTimer(1.3, 0.7), new Wait(0.3), new TargetVision(0, true));
    
    }
}
