package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Status {
    CHUA_XU_LI("Chưa xử lí"),
    DANG_XU_LI("Đang xử lí"),
    DA_XU_LI("Đã xử lí");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public static Map<String, String> type(){
        Map<String, String> listStatus = new LinkedHashMap<>();
        for (Status item : Status.values()) {
            listStatus.put(item.toString(), item.getStatusName());
        }
        return listStatus;
    }

    public static String getValueByKey(String key) {
        for (Status status : Status.values()) {
            if (status.name().equals(key)) {
                return status.getStatusName();
            }
        }
        return null;
    }
}
