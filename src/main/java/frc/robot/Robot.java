/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot 
{
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private PhotonCamera ballCamera;
  private PhotonTrackedTarget ballTarget;
  private PhotonCamera targetCamera;
  private PhotonTrackedTarget targetTarget;

  public static double ball_pitch;
  public static double ball_yaw;
  public static double ball_skew;
  public static double ball_area;
  public static boolean ball_hasTarget;
  public static double target_pitch;
  public static double target_yaw;
  public static double target_skew;
  public static double target_area;
  public static boolean target_hasTarget;

  public static int setPointShooter;
  public static int autoSetPointShooter;
  public static double lidarDist;
  public static double lidarDistCm;

  @Override
  public void robotInit() 
  {
    m_robotContainer = new RobotContainer();

    RobotContainer.getDriveTrain().driveLeftInit();
    RobotContainer.getDriveTrain().driveRightInit();
    RobotContainer.getShooter().shooterInit();
    RobotContainer.getLIDAR().initLIDAR();

    RobotContainer.getAHRS().reset();

    ballCamera = new PhotonCamera("photoncam2");

    targetCamera = new PhotonCamera("photoncam");
  }

  @Override
  public void robotPeriodic() 
  {
    CommandScheduler.getInstance().run();

    ball_hasTarget = ballCamera.getLatestResult().hasTargets();

    if(ball_hasTarget){
      ballTarget = ballCamera.getLatestResult().getBestTarget();
      ball_pitch = ballTarget.getPitch();
      ball_yaw = ballTarget.getYaw();
      ball_skew = ballTarget.getSkew();
      ball_area = ballTarget.getArea();
    }

    target_hasTarget = targetCamera.getLatestResult().hasTargets();

    if(target_hasTarget){
      targetTarget = targetCamera.getLatestResult().getBestTarget();
      target_pitch = targetTarget.getPitch();
      target_yaw = targetTarget.getYaw();
      target_skew = targetTarget.getSkew();
      target_area = targetTarget.getArea();
    }

    lidarDist = (RobotContainer.getLIDAR().getDistance()/2.54);
    lidarDistCm = RobotContainer.getLIDAR().getDistance();
    setPointShooter = (((int)RobotContainer.getJoy().getRawAxis(3) * 3000) + 3000);
    autoSetPointShooter = (int)(lidarDist * Constants.DISTANCE_COEFFICIENT);

    SmartDashboard.putNumber("Shooter Setpoint", setPointShooter);
    SmartDashboard.putNumber("Auton Shooter Setpoint", autoSetPointShooter);
    SmartDashboard.putNumber("Shooter RPM", RobotContainer.getShooter().getShooterLeftEnc().getVelocity());
    SmartDashboard.putBoolean("Hoop Visible", target_hasTarget);
    SmartDashboard.putBoolean("Ball Visible", ball_hasTarget);
    SmartDashboard.putBoolean("Climber Limit Switch 1", RobotContainer.getClimber().getClimberLmtInner1().get());
    SmartDashboard.putBoolean("Climber Limit Switch 2", RobotContainer.getClimber().getClimberLmtInner1().get());
    SmartDashboard.putBoolean("Climber 2 Limit Switch 1", RobotContainer.getClimber().getClimberLmtOuter1().get());
    SmartDashboard.putBoolean("Climber 2 Limit Switch 2", RobotContainer.getClimber().getClimberLmtOuter1().get());
    SmartDashboard.putBoolean("Telesxopic Limit Switch 1", RobotContainer.getClimber().getClimberLmtTele1().get());
    SmartDashboard.putBoolean("Telescopic Limit Switch 2", RobotContainer.getClimber().getClimberLmtTele1().get());
    SmartDashboard.putNumber("LIDAR (In)", RobotContainer.getLIDAR().getDistance());
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