// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.MoveShooterTeleop;
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

  //sensors
  private static RelativeEncoder shooterRightEnc;
  private static RelativeEncoder shooterLeftEnc;

  //subsystems
  private static Shooter shooter;

  private static SparkMaxPIDController pidcontrol_shooter_Right;
  private static SparkMaxPIDController pidcontrol_shooter_Left;

  public static PowerDistribution pdp;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    shooterMotorRight = new CANSparkMax(Constants.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);
  shooterMotorLeft = new CANSparkMax(Constants.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);

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
  private void configureButtonBindings() {}
  
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
}
