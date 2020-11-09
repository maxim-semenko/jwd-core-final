package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.MyHelpfulService;

import java.util.HashMap;
import java.util.Map;

public class MyHelpfulServiceImpl implements MyHelpfulService {


    private static MyHelpfulServiceImpl instance;

    public static MyHelpfulServiceImpl getInstance() {
        if (instance == null) {
            instance = new MyHelpfulServiceImpl();
        }
        return instance;
    }

    @Override
    public MissionResult makeMissionResult(int data) {
        switch (data) {
            case 1:
                return MissionResult.CANCELLED;
            case 2:
                return MissionResult.FAILED;
            case 3:
                return MissionResult.PLANNED;
            case 4:
                return MissionResult.IN_PROGRESS;
            case 5:
                return MissionResult.COMPLETED;
            default:
                throw new UnknownEntityException("Error MissionResult!");
        }
    }

    @Override
    public Map<Role, Short> makeCrew(String data) {
        Map<Role, Short> crew = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder(data);
        stringBuilder.deleteCharAt(0);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        int i = 0;
        int roleId = 0;
        while (i < stringBuilder.length()) {
            if (stringBuilder.charAt(i) == ':') {
                StringBuilder temp = new StringBuilder();
                roleId++;
                while (true) {
                    i++;
                    if (i == stringBuilder.length()) {
                        crew.put(Role.resolveRoleById(roleId), (short) Integer.parseInt(String.valueOf(temp)));
                        break;
                    }
                    if (stringBuilder.charAt(i) == ',') {
                        break;
                    }
                    temp.append(stringBuilder.charAt(i));
                }
                crew.put(Role.resolveRoleById(roleId), (short) Integer.parseInt(String.valueOf(temp)));
            }
            i++;
        }
        return crew;
    }


}
