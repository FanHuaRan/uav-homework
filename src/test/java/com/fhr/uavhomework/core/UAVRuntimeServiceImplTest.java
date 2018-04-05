package com.fhr.uavhomework.core;

import com.fhr.uavhomework.context.UAVRuntimeContext;
import com.fhr.uavhomework.msgdecode.SimpleMessageDecodeStrategy;
import com.fhr.uavhomework.vehicle.UAVehicle;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author FanHuaran
 * @description UAV运行时服务单元测试组件，主要包含行为测试、边界值测试和特殊值测试
 * @create 2018-04-05 15:38
 **/
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

        assertEquals("plane1 2 2 3 4", uavRuntimeService.outputChangeVehicleForStrAtMoment(2));
        UAVehicle uaVehicle1 = uavRuntimeService.outputChangeVehicleAtMoment(2);
        assertNotNull(uaVehicle1);
        assertEquals(false, uaVehicle1.isWrong());
        assertEquals(2, (int) uaVehicle1.getX());
        assertEquals(3, (int) uaVehicle1.getY());
        assertEquals(4, (int) uaVehicle1.getZ());

        assertEquals("plane1 3 3 4 5", uavRuntimeService.outputChangeVehicleForStrAtMoment(3));
        UAVehicle uaVehicle11 = uavRuntimeService.outputChangeVehicleAtMoment(3);
        assertNotNull(uaVehicle11);
        assertEquals(false, uaVehicle11.isWrong());
        assertEquals(3, (int) uaVehicle11.getX());
        assertEquals(4, (int) uaVehicle11.getY());
        assertEquals(5, (int) uaVehicle11.getZ());

        assertEquals("Error: 4", uavRuntimeService.outputChangeVehicleForStrAtMoment(4));
        UAVehicle uaVehicle2 = uavRuntimeService.outputChangeVehicleAtMoment(4);
        assertEquals(true, uaVehicle2.isWrong());

        assertEquals("Cannot find 100", uavRuntimeService.outputChangeVehicleForStrAtMoment(100));
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
        assertEquals("plane1 1 1 1 1", uavRuntimeService.outputChangeVehicleForStrAtMoment(1));

        IUAVRuntimeService uavRuntimeService2 = getUAVRuntimeService();
        assertEquals("Cannot find 1", uavRuntimeService2.outputChangeVehicleForStrAtMoment(1));
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

}