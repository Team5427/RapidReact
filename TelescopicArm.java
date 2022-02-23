// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TelescopicArm extends SubsystemBase {
  /** Creates a new TelescopicArm. */
  WPI_VictorSPX left, right; 
DigitalInput left_Limit, right_Limit;
Encoder left_Encoder, right_Encoder;

  public TelescopicArm(WPI_VictorSPX telescopicArmLeft, WPI_VictorSPX telescopicArmRight,
      DigitalInput leftLimitSwitchTele, DigitalInput rightLimitSwitchTele, Encoder telescopicArmLefttEnc,
      Encoder telescopicArmRightEnc) {
 this.left= left;
this.right= right; 
//Limit Switches
this.left_Limit=left_Limit;
this.right_Limit= right_Limit;
//Encoders
this.left_Encoder=left_Encoder;
this.right_Encoder=right_Encoder;
  }
  public void setTelescopicSpeed(double speed) {
    left.set(speed);
    right.set(speed);
  }

  // Stops both Elevators
  public void stopTelescopicMotors() {
    left.stopMotor();
    right.stopMotor();
  }

  // stops left Motor
  public void stopLeftMotor() {
    left.stopMotor();
  }

  // stops rightMotor
  public void stopRightMotor() {
    right.stopMotor();
  }

  // Left and Right Limits return false if we have the limits and true if it does
  // not.
  public boolean getLeftLimit() {
    return !left_Limit.get();
  }

  public boolean getRightLimit() {
    return !right_Limit.get();
  }

  // Gets the Encoder: LEFT & RIGHT
  public Encoder getLeftEncoder() {
    return left_Encoder;
  }

  public Encoder getRightEncoder() {
    return right_Encoder;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
