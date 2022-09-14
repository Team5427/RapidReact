package frc.robot.commands.auto.Trajectory;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class RamseteClass {

    private DriveTrain m_robotDrive;
    private PratsRamseteCommand command;
    
    public RamseteClass(Trajectory exampleTrajectory) {
        m_robotDrive = RobotContainer.getDriveTrain();

        var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                Constants.ksVolts,
                Constants.kvVoltSecondsPerMeter,
                Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            10);

        // Create config for trajectory
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.kMaxSpeedMetersPerSecond,
                    Constants.kMaxAccelerationMetersPerSecondSquared)
                // Add kinematics to ensure max speed is actually obeyed
                .setKinematics(Constants.kDriveKinematics)
                // Apply the voltage constraint
                .addConstraint(autoVoltageConstraint);

        // An example trajectory to follow.  All units in meters.
        // Trajectory exampleTrajectory =
        //     TrajectoryGenerator.generateTrajectory(
        //         new Pose2d(0, 0, new Rotation2d(0)),

        //             List.of(
        //                 new Translation2d(1,1), new Translation2d(2, -1)),
        //                 new Pose2d(3, 0, new Rotation2d(0))
        //                 // new Pose2d(2.938, 1.414, Rotation2d.fromDegrees(-45)),
        //                 // new Pose2d(4.352, 0, Rotation2d.fromDegrees(-45))
        //                 ,
        //         config);
        command =
            new PratsRamseteCommand(
                exampleTrajectory,
                m_robotDrive::getPose,
                new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
                new SimpleMotorFeedforward(
                    Constants.ksVolts,
                    Constants.kvVoltSecondsPerMeter,
                    Constants.kaVoltSecondsSquaredPerMeter),
                Constants.kDriveKinematics,
                m_robotDrive::getWheelSpeeds,
                new PIDController(Constants.kPDriveVel, 0, 0),
                new PIDController(Constants.kPDriveVel, 0, 0),
                // PratsRamseteCommand passes volts to the callback
                m_robotDrive::setVolts,
                m_robotDrive);

        // Reset odometry to the starting pose of the trajectory.
        m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose());
    }


    public PratsRamseteCommand getRamCom() {
        return command;
    }

}
