package com.advyteam.sygha.service;

import com.advyteam.sygha.DTO.CtpDTO;
import com.advyteam.sygha.entity.CtpDsn;
import com.advyteam.sygha.entity.User;

import java.util.List;

public interface CtpDsnService {

    List<CtpDsn> getAllCtp();

    CtpDsn updateCtp(CtpDTO ctpDTO, User user);

    CtpDsn addCtp(CtpDTO ctpDTO, User user);

    CtpDsn deleteCtp(CtpDsn ctpDsn);
}
