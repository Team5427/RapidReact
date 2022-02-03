// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.SPI;


import frc.robot.commands.MoveShooterTeleop;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static Joystick joy;
  private static Button shooterTeleop;

  //motors 
  public static CANSparkMax shooterMotorRight;
  public static CANSparkMax shooterMotorLeft;
  public static CANSparkMax topRight, topLeft, bottomRight, bottomLeft;
  public static MotorControllerGroup left, right;

  public static DifferentialDrive drive;

  //sensors
  private static RelativeEncoder shooterRightEnc;
  private static RelativeEncoder shooterLeftEnc;

  private static AHRS ahrs;

  //subsystems
  private static Shooter shooter;
  private static DriveTrain driveTrain;

  private static SparkMaxPIDController pidcontrol_shooter_Right;
  private static SparkMaxPIDController pidcontrol_shooter_Left;

  public static PowerDistribution pdp;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
  shooterMotorRight = new CANSparkMax(Constants.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);
  shooterMotorLeft = new CANSparkMax(Constants.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);

  topLeft = new CANSparkMax(Constants.TOP_LEFT_MOTOR, MotorType.kBrushless);
  topRight = new CANSparkMax(Constants.TOP_RIGHT_MOTOR, MotorType.kBrushless);
  bottomLeft = new CANSparkMax(Constants.BOTTOM_LEFT_MOTOR, MotorType.kBrushless);
  bottomRight = new CANSparkMax(Constants.BOTTOM_RIGHT_MOTOR, MotorType.kBrushless);

  drive = new DifferentialDrive(left, right);
  driveTrain = new DriveTrain(left, right, drive, topLeft);

  left = new MotorControllerGroup(topLeft, bottomLeft);
  right = new MotorControllerGroup(topRight, bottomRight);

  ahrs = new AHRS(SPI.Port.kMXP);

  pidcontrol_shooter_Right = shooterMotorRight.getPIDController();
  pidcontrol_shooter_Left = shooterMotorLeft.getPIDController();
  // Configure the button bindings

  shooterRightEnc = shooterMotorRight.getEncoder();
  shooterLeftEnc = shooterMotorLeft.getEncoder();

  pdp = new PowerDistribution(0, ModuleType.kCTRE);


  

  shooterMotorLeft.setInverted(false);

  
  shooterMotorRight.setInverted(true);

  shooter = new Shooter(shooterMotorRight, shooterMotorLeft, shooterRightEnc, shooterLeftEnc, pidcontrol_shooter_Right, pidcontrol_shooter_Left);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    joy = new Joystick(0);

    shooterTeleop = new JoystickButton(joy, 1);

    shooterTeleop.whileHeld(new MoveShooterTeleop(.5));
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public static Shooter getShooter(){return shooter;}
  public static Joystick getJoy(){return joy;}
  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static AHRS getAHRS(){return ahrs;}
}
