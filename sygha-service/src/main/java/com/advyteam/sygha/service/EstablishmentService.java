package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.CompanyDTO;
import com.advyteam.sygha.DTO.EstablishmentDTO;
import com.advyteam.sygha.entity.Company;
import com.advyteam.sygha.entity.Establishment;
import com.advyteam.sygha.entity.Response;
import com.advyteam.sygha.entity.User;

import java.util.List;

public interface EstablishmentService {
//
    Response getAllEstablishment(int pageNo, int pageSize);

    Establishment updateEstablishment(EstablishmentDTO establishment, User user);

    Establishment addEstablishment(EstablishmentDTO establishment, User user);

    Establishment deleteEstablishment(Establishment establishment);
}
