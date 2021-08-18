package com.jrasp.agent.script;

import com.jrasp.agent.type.Keys;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentScript {

    public static Map<String, List<Keys>> scriptMap = new HashMap<>();

    // 被目标方法调用
    public static boolean check(Object[] args, String className, String methodName, String methodDesc) {
        // 获取参数
        List<Keys> paramKeys = scriptMap.get(className + methodName + methodDesc);
        if (paramKeys == null || paramKeys.isEmpty()) {
            return true;
        }
        // 遍历所有参数
        for (int i = 0; i < paramKeys.size(); i++) {
            Keys paramKey = paramKeys.get(i);
            int index = paramKey.getIndex();
            String arg = (String) args[index];
            if (!check1(arg, paramKey.getEquals())) {
                return false;
            }
            if (!check2(arg, paramKey.getStartWith())) {
                return false;
            }
            if (!check3(arg, paramKey.getEndsWith())) {
                return false;
            }
            if (!check4(arg, paramKey.getContains())) {
                return false;
            }
            // todo 增加正则
        }
        return true;
    }

    public static boolean check1(String arg, List<String> list) {
        if (list == null) return true;
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.equals(arg, list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean check2(String arg, List<String> list) {
        if (list == null) return true;
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.startsWith(arg, list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean check3(String arg, List<String> list) {
        if (list == null) return true;
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.endsWith(arg, list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean check4(String arg, List<String> list) {
        if (list == null) return true;
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.contains(arg, list.get(i))) {
                return false;
            }
        }
        return true;
    }

}
