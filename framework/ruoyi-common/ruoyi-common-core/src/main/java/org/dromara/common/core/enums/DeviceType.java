package org.dromara.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

/**
 * 设备类型
 * 针对一套 用户体系
 *
 * @author Lion Li
 */
@Getter
@AllArgsConstructor
public enum DeviceType {

    /**
     * pc端
     */
    PC("pc"),

    /**
     * app-ios端
     */
    IM_APP_IOS("im_app_ios"),
    /**
     * app-android端
     */
    IM_APP_ANDROID("im_app_android"),
    /**
     * web端
     */
    IM_WEB("im_web"),
    /**
     * pc-win端
     */
    IM_PC_WIN("im_pc_win"),
    /**
     * pc-mac端
     */
    IM_PC_MAC("im_pc_mac"),

    /**
     * 小程序端
     */
    XCX("xcx");

    private final String device;

    // 根据device 获取对应的枚举
    public static DeviceType getDeviceType(String device) {
        return Arrays.stream(DeviceType.values())
            .filter(type -> type.getDevice().equals(device))
            .findFirst()
            .orElse(null);
    }

}
