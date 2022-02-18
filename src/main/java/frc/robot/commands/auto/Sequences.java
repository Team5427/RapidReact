package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Sequences {

    public static SequentialCommandGroup seq1;
    public static SequentialCommandGroup seq2;
    public static SequentialCommandGroup seq3;
    public static SequentialCommandGroup seq4;

    public Sequences() {

    }

    public static void initSequences() {
        seq1 = new SequentialCommandGroup(
            new IntakeVision(0.2, 0.3, 0.5, true), 
            new ForwardAuto(.5, .4), 
            new ShootVision(0.5, 0.2, 0.15, true), 
            new Wait(1.5), 
            new PointTurn(300, 0.15, 0.4), 
            new Wait(0.5), 
            new IntakeVision(0.3, 0.4, 0.5, true), 
            new ForwardAuto(.5, .4), 
            new ShootVision(0.5, 0.2, 0.15, true)
        );

        seq2 = new SequentialCommandGroup(
            new IntakeVision(0.2, 0.3, 0.5, true), 
            new ForwardAuto(.5, .4), 
            new ShootVision(0.5, 0.2, 0.15, true), 
            new Wait(1.5), 
            new PointTurn(300, 0.15, 0.4), 
            new Wait(0.5), 
            new IntakeVision(0.3, 0.4, 0.5, true), 
            new ForwardAuto(.5, .4), 
            new ShootVision(0.5, 0.2, 0.15, true)
        );

        seq3 = new SequentialCommandGroup(
            new IntakeVision(0.2, 0.3, 0.5, true), 
            new ForwardAuto(.5, .4), 
            new ShootVision(0.5, 0.2, 0.15, true), 
            new Wait(1.5), 
            new PointTurn(300, 0.15, 0.4), 
            new Wait(0.5), 
            new IntakeVision(0.3, 0.4, 0.5, true), 
            new ForwardAuto(.5, .4), 
            new ShootVision(0.5, 0.2, 0.15, true)
        );

        seq4 = new SequentialCommandGroup(
            new IntakeVision(0.2, 0.3, 0.5, true), 
            new ForwardAuto(.5, .4), 
            new ShootVision(0.5, 0.2, 0.15, true), 
            new Wait(1.5), 
            new PointTurn(300, 0.15, 0.4), 
            new Wait(0.5), 
            new IntakeVision(0.3, 0.4, 0.5, true), 
            new ForwardAuto(.5, .4), 
            new ShootVision(0.5, 0.2, 0.15, true)
        );
    }

}
