package com.fhr.uavhomework.core;

import com.fhr.uavhomework.context.UAVRuntimeContext;
import com.fhr.uavhomework.msgdecode.SimpleMessageDecodeStrategy;
import com.fhr.uavhomework.vehicle.UAVehicle;
import org.junit.Test;

import static org.junit.Assert.*;

public class UAVRuntimeServiceImplTest {

    /**
     * 行为测试
     */
    @Test
    public void functionalTest() {
        IUAVRuntimeService uavRuntimeService = getUAVRuntimeService();

        String[] messages = {
                "plane1 1 1 1",
                "plane1 1 1 1 1 2 3",
                "plane1 2 3 4 1 1 1",
                "plane1 3 4 5",
                "plane1 1 1 1 1 2 3"
        };

        for (String message : messages) {
            uavRuntimeService.nextMessage(message);
        }

        assertEquals(uavRuntimeService.outputChangeVehicleAtMomentForStr(2), "plane1 2 3 4 5");
        UAVehicle uaVehicle1 = uavRuntimeService.outputChangeVehicleAtMoment(2);
        assertNotNull(uaVehicle1);
        assertEquals(uaVehicle1.isWrong(), false);
        assertEquals((int) uaVehicle1.getX(), 3);
        assertEquals((int) uaVehicle1.getY(), 4);
        assertEquals((int) uaVehicle1.getZ(), 5);

        assertEquals(uavRuntimeService.outputChangeVehicleAtMomentForStr(4), "Error: 4");
        UAVehicle uaVehicle2 = uavRuntimeService.outputChangeVehicleAtMoment(4);
        assertEquals(uaVehicle2.isWrong(), true);

        assertEquals(uavRuntimeService.outputChangeVehicleAtMomentForStr(100), "Cannot find 100");
        UAVehicle uaVehicle3 = uavRuntimeService.outputChangeVehicleAtMoment(100);
        assertNull(uaVehicle3);
    }

    /**
     * 边界值测试
     */
    @Test
    public void boundValueTest() {
        IUAVRuntimeService uavRuntimeService = getUAVRuntimeService();
        uavRuntimeService.nextMessage("plane1 1 1 1");
        assertEquals(uavRuntimeService.outputChangeVehicleAtMomentForStr(1), "plane1 1 1 1 1");

        IUAVRuntimeService uavRuntimeService2 = getUAVRuntimeService();
        assertEquals(uavRuntimeService2.outputChangeVehicleAtMomentForStr(1), "Cannot find 1");
    }

    /**
     * 特殊值测试
     */
    @Test
    public void spetialValueTest() {
        IUAVRuntimeService uavRuntimeService = getUAVRuntimeService();
        uavRuntimeService.nextMessage("");
        uavRuntimeService.nextMessage(null);
    }

    private IUAVRuntimeService getUAVRuntimeService() {
        return new UAVRuntimeServiceImpl(new UAVRuntimeContext(), new SimpleMessageDecodeStrategy());
    }

//    @Test
//    public void nextMessage() {
//    }
//
//    @Test
//    public void outputChangeVehicleAtMoment() {
//    }
//
//    @Test
//    public void outputChangeVehicleAtMomentForStr() {
//    }
}