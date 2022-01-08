package com.lottery.main.api;

import com.lottery.main.api.security.JwtTokenUtil;
import com.lottery.main.domain.dto.LotteryDto;
import com.lottery.main.domain.model.Lottery;
import com.lottery.main.domain.repository.LotteryRepository;
import com.lottery.main.service.JwtUserDetailsService;
import com.lottery.main.service.LotteryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class LotteryControllerTest {

    @Autowired
    LotteryService lotteryService;

    @MockBean
    LotteryRepository lotteryRepository;

    private List<Lottery> lotteryList;


    @Test
    void addNewLottery() {

    }

    @BeforeEach
    public void setUp() {
        lotteryList = new ArrayList<Lottery>();
        lotteryList.add(new Lottery(){
            {setTitle("MegaLottery1"); setBallotLength(10); setBallotPrice(10); setDescription("gfdsgfds");   }
        });

        lotteryList.add(new Lottery(){
                {setTitle("MegaLottery2"); setBallotLength(5); setBallotPrice(5); setDescription("kjhgkj");   }
        });
    }
    @Test
    void getAllLottery() throws Exception {
        when(lotteryRepository.findAll()).thenReturn((List<Lottery>) (lotteryList));
        assertEquals(2,        lotteryService.getAllLotteries().size());
    }

}