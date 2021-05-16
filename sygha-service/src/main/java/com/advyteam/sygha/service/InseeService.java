package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.InseeDTO;
import com.advyteam.sygha.entity.Insee;
import com.advyteam.sygha.entity.User;

import java.util.List;

public interface InseeService {

    List<Insee> getAllInsee();

    Insee updateInsee(InseeDTO inseeDTO, User user);

    Insee addInsee(InseeDTO inseeDTO, User user);

    Insee deleteInsee(Insee insee);
}
