package com.lottery.main.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lottery.main.domain.dto.WiningBallotDto;
import com.lottery.main.domain.model.WiningBallot;
import com.lottery.main.domain.repository.WiningBallotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class WiningBallotService {
    @Autowired
    private WiningBallotRepository winingBallotRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public ResponseEntity<String> addWiningBallot(WiningBallotDto winingBallotDto) throws Exception
    {
        WiningBallot winingBallot= jacksonObjectMapper.convertValue(winingBallotDto, new TypeReference<WiningBallot>(){});
        Date date =new Date();
        winingBallot.setCreateDate(date);
        winingBallotRepository.save(winingBallot);
        return ResponseEntity.ok("Category Saved Successfully");
    }

    public Optional<WiningBallot>  getWiningBallotById(int winingBallotId)
    {
        Optional<WiningBallot> winingBallot = winingBallotRepository.findById(winingBallotId);
        return winingBallot;
    }

}
