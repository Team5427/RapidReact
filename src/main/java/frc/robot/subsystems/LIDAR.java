package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LIDAR extends SubsystemBase{
    I2C m_i2c;
    int fnc_bit = 0;
    
    public LIDAR(I2C.Port port, int addr) {  
        m_i2c = new I2C(port, addr);
        this.m_i2c = m_i2c;
    }

    public int getDistance() {

        byte[] buffer;
        byte[] fnc_arr;	
        buffer = new byte[2];
        fnc_arr = new byte[1];

        m_i2c.write(0x00, 0x04);
        m_i2c.read(0x01, 1, fnc_arr);
        if (isSet(fnc_arr, fnc_bit)) {
            m_i2c.read(0x8f, 2, buffer); 
        }

        return (int)Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);	
    }

    public boolean isSet(byte[] arr, int bit) {
        int index = bit / 8;  // Get the index of the array for the byte with this bit
        int bitPosition = bit % 8;  // Position of this bit in a byte

        return (arr[index] >> bitPosition & 1) == 1;
    }
}
