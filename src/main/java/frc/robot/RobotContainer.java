// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.MoveTelescopicArm;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TelescopicArm;

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
  private static Button MoveElevatorUp;
  private static Button MoveElevatorDown;
  private static Button MoveTelescopicArmUp;
  private static Button MoveTelescopicArmDown;
  private static Button MoveTelescopicArmRight;
  private static Button MoveTelescopicArmLeft;
  


  //motors 
  public static CANSparkMax shooterMotorRight;
  public static CANSparkMax shooterMotorLeft;
  public static  WPI_VictorSPX elevatorMotorLeft;
  public static  WPI_VictorSPX elevatorMotorRight;

  //sensors
  private static RelativeEncoder shooterRightEnc;
  private static RelativeEncoder shooterLeftEnc;
  private static Encoder elevatorRightEnc;
  private static Encoder elevatorLeftEnc;

  //subsystems
  private static Shooter shooter;
  private static Elevator elevator;
  private static TelescopicArm telescopicArm;
  private static SparkMaxPIDController pidcontrol_shooter_Right;
  private static SparkMaxPIDController pidcontrol_shooter_Left;

//elevator & TeleOps
private static WPI_VictorSPX elevatorLeft;
private static WPI_VictorSPX elevatorRight;
private static DigitalInput leftLimitSwitch;
private static DigitalInput rightLimitSwitch;

private static WPI_VictorSPX telescopicArmLeft;
private static WPI_VictorSPX telescopicArmRight;
private static Encoder telescopicArmRightEnc;
private static Encoder telescopicArmLefttEnc;
private static DigitalInput leftLimitSwitchTele;
private static DigitalInput rightLimitSwitchTele;

  public static PowerDistribution pdp;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
//elevator
    elevatorLeft= new   WPI_VictorSPX(Constants.ELEVATOR_LEFT_MOTOR);
    elevatorRight = new   WPI_VictorSPX(Constants.ELEVATOR_RIGHT_MOTOR);
    elevatorLeftEnc=  new Encoder(Constants.ELEVATOR_LEFT_PORT1, Constants.ELEVATOR_LEFT_PORT2);
    elevatorRightEnc=  new Encoder(Constants.ELEVATOR_LEFT_PORT1, Constants.ELEVATOR_LEFT_PORT2);
    leftLimitSwitch= new DigitalInput(Constants.ELEVATOR_LIMIT_LEFT);
    rightLimitSwitch= new DigitalInput(Constants.ELEVATOR_LIMIT_RIGHT);
    elevator= new Elevator(elevatorLeft, elevatorRight, leftLimitSwitch, rightLimitSwitch, elevatorLeftEnc, elevatorRightEnc);
    //telescopic Arm
    telescopicArmLeft= new   WPI_VictorSPX(Constants.TELESCOPIC_ARM_LEFT_MOTOR);
    telescopicArmRight = new   WPI_VictorSPX(Constants.TELESCOPIC_ARM_RIGHT_MOTOR);
    leftLimitSwitchTele= new DigitalInput(Constants.TELESCOPIC_ARM_LIMIT_LEFT);
    rightLimitSwitchTele= new DigitalInput(Constants.TELESCOPIC_ARM_LIMIT_RIGHT);
    telescopicArmRightEnc= new Encoder(Constants.TELESCOPIC_ARM_RIGHT_PORT1, Constants.TELESCOPIC_ARM_RIGHT_PORT2);
    telescopicArmLefttEnc= new Encoder(Constants.TELESCOPIC_ARM_LEFT_PORT1,Constants.TELESCOPIC_ARM_LEFT_PORT2);
    telescopicArm= new TelescopicArm(telescopicArmLeft, telescopicArmRight, leftLimitSwitchTele, rightLimitSwitchTele, telescopicArmLefttEnc, telescopicArmRightEnc);

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
  private void configureButtonBindings() {
    joy = new Joystick(0);
    MoveElevatorUp = new JoystickButton(joy, Constants.ELEVATOR_UP_BUTTON);
    MoveElevatorDown = new JoystickButton(joy, Constants.ELEVATOR_DOWN_BUTTON);
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //shooter

  //Elevator
  MoveElevatorUp.whileHeld(new MoveElevator(Constants.ElevatorSpeed));
  MoveElevatorDown.whileHeld(new MoveElevator(-Constants.ElevatorSpeed));

  //Up and Down
  MoveTelescopicArmUp.whileHeld(new MoveElevator(Constants.Telescopic_Arm_Speed));
  MoveTelescopicArmDown.whileHeld(new MoveElevator(-Constants.Telescopic_Arm_Speed));

  //Left and Right
  MoveTelescopicArmRight.whileHeld(new MoveElevator(Constants.Telescopic_Arm_Speed));
  MoveTelescopicArmLeft.whileHeld(new MoveElevator(-Constants.Telescopic_Arm_Speed));


  }
  public static Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public static Shooter getShooter(){return shooter;}
  public static Joystick getJoy(){return joy;}
  public static Elevator getElevator(){return elevator;}
  public static TelescopicArm getTelescopicArm(){return telescopicArm; }

}
