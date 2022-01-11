package com.lottery.main.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lottery.main.domain.dto.WiningBallotDto;
import com.lottery.main.domain.model.Lottery;
import com.lottery.main.domain.model.WiningBallot;
import com.lottery.main.repository.LotteryRepository;
import com.lottery.main.repository.WiningBallotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WinningBallotService {
    @Autowired
    private WiningBallotRepository winingBallotRepository;
    @Autowired
    private LotteryRepository lotteryRepository;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public ResponseEntity<String> addWiningBallot(WiningBallotDto winingBallotDto) throws Exception
    {
        WiningBallot winingBallot= jacksonObjectMapper.convertValue(winingBallotDto, new TypeReference<WiningBallot>(){});
        Optional<Lottery> lottery = lotteryRepository.findById(winingBallotDto.getLotteryId());
        if (lottery != null)
        {

            winingBallot.setLottery(lottery.get());
         }
        else
            return ResponseEntity.badRequest().body("lottery Id Not Found");

        winingBallot.setCreateDate(new Date());
        winingBallotRepository.save(winingBallot);
        return ResponseEntity.ok("winingBallot Saved Successfully");
    }

    public List<WiningBallot>  getWiningBallotByDate(Date date)
    {
        List<WiningBallot> winingBallotList = winingBallotRepository.findByCreateDate(date);
        return winingBallotList;
    }

}
