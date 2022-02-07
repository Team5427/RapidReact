/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot 
{
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  String trajectoryJSON = "paths/YourPath.wpilib.json";
  Trajectory trajectory = new Trajectory();

  private NetworkTable ballTable;
  private NetworkTable targetTable;
  public static double ball_pitch;
  public static double ball_yaw;
  public static double ball_skew;
  public static double ball_area;
  public static double ball_PixelX;
  public static double ball_PixelY;
  public static boolean ball_hasTarget;
  public static double target_pitch;
  public static double target_yaw;
  public static double target_skew;
  public static double target_area;
  public static double target_PixelX;
  public static double target_PixelY;
  public static boolean target_hasTarget;

  public static int setPointShooter;

  private double default_all = 0.0;

  @Override
  public void robotInit() 
  {
    m_robotContainer = new RobotContainer();

    RobotContainer.getDriveTrain().driveLeftInit();
    RobotContainer.getDriveTrain().driveRightInit();
    RobotContainer.getShooter().shooterInit();

    RobotContainer.getAHRS().reset();
    NetworkTableInstance PIInstance = NetworkTableInstance.create();
    PIInstance.setServer("targetvision");
    PIInstance.startClient();
    targetTable = PIInstance.getTable("photonvision").getSubTable("photoncam");
    NetworkTableInstance PI2Instance = NetworkTableInstance.create();
    PI2Instance.setServer("ballvision");
    PI2Instance.startClient();
    ballTable = PI2Instance.getTable("photonvision").getSubTable("photoncam2");

  }

  @Override
  public void robotPeriodic() 
  {
    CommandScheduler.getInstance().run();

    target_hasTarget = targetTable.getEntry("hasTarget").getBoolean(true);
    target_pitch = ballTable.getEntry("targetPitch").getDouble(default_all);
    target_yaw = ballTable.getEntry("targetYaw").getDouble(default_all);
    target_skew = ballTable.getEntry("targetSkew").getDouble(default_all);
    target_area = ballTable.getEntry("targetArea").getDouble(default_all);
    target_PixelX = ballTable.getEntry("targetPixelsX").getDouble(default_all);
    target_PixelY = ballTable.getEntry("targetPixelsY").getDouble(default_all);

    ball_hasTarget = ballTable.getEntry("hasTarget").getBoolean(true);
    ball_pitch = ballTable.getEntry("targetPitch").getDouble(default_all);
    ball_yaw = ballTable.getEntry("targetYaw").getDouble(default_all);
    ball_skew = ballTable.getEntry("targetSkew").getDouble(default_all);
    ball_area = ballTable.getEntry("targetArea").getDouble(default_all);
    ball_PixelX = ballTable.getEntry("targetPixelsX").getDouble(default_all);
    ball_PixelY = ballTable.getEntry("targetPixelsY").getDouble(default_all);

    setPointShooter = (((int)RobotContainer.getJoy().getRawAxis(4) * 3000) + 3000);

    SmartDashboard.putNumber("Shooter Setpoint", setPointShooter);
    SmartDashboard.putNumber("Shooter RPM", RobotContainer.getShooter().getShooterLeftEnc().getVelocity());
    SmartDashboard.putBoolean("Hoop Visible", target_hasTarget);
    SmartDashboard.putBoolean("Ball Visible", ball_hasTarget);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() 
  {
    RobotContainer.getAHRS().reset();

    m_autonomousCommand = RobotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() 
  {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() 
  {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }
}