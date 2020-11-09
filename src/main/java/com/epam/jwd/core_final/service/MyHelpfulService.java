package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;

import java.util.Map;

public interface MyHelpfulService {

    MissionResult makeMissionResult(int data);

    Map<Role, Short> makeCrew(String data);
}
