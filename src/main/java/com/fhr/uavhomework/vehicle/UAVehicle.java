package com.fhr.uavhomework.vehicle;

import java.util.Objects;

/**
 * @author FanHuaran
 * @description 无人机模型
 * @create 2018-04-05 11:17
 **/
public class UAVehicle {
    /**
     * 无人机编号
     */
    private final String id;

    /**
     * 无人机X坐标
     */
    private Integer x;

    /**
     * 无人机Y坐标
     */
    private Integer y;

    /**
     * 无人机Z坐标
     */
    private Integer z;

    /**
     * 是否故障
     */
    private boolean isWrong;

    public UAVehicle(String id, Integer x, Integer y, Integer z, boolean isWrong) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.isWrong = isWrong;
    }

    public String getId(){
        return  id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public boolean isWrong() {
        return isWrong;
    }

    public void setWrong(boolean wrong) {
        isWrong = wrong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UAVehicle uaVehicle = (UAVehicle) o;
        return Objects.equals(id, uaVehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UAVehicle{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", isWrong=" + isWrong +
                '}';
    }
}
