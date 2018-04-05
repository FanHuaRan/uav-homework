package com.fhr.uavhomework.bootstrap;

import com.fhr.uavhomework.context.UAVRuntimeContext;
import com.fhr.uavhomework.core.IUAVRuntimeService;
import com.fhr.uavhomework.core.UAVRuntimeServiceImpl;
import com.fhr.uavhomework.msgdecode.SimpleMessageDecodeStrategy;

import java.util.Scanner;

/**
 * @author FanHuaran
 * @description 命令行驱动程序
 * @create 2018-04-05 14:28
 **/
public class App {
    public static void main(String[] args) {
        IUAVRuntimeService uavRuntimeService = new UAVRuntimeServiceImpl(new UAVRuntimeContext(), new SimpleMessageDecodeStrategy());
        try (Scanner scanner = new Scanner(System.in)) {
            int msgCount = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < msgCount; i++) {
                uavRuntimeService.nextMessage(scanner.nextLine());
            }
            int queryCount = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < queryCount; i++) {
                int msgId = Integer.parseInt(scanner.nextLine());
                System.out.println(uavRuntimeService.outputChangeVehicleAtMoment(msgId));
            }
            System.out.println("bye");
            scanner.next();
        }
    }
}
