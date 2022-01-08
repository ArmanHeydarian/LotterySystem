package com.lottery.main.service;

import com.lottery.main.api.security.JwtTokenUtil;
import com.lottery.main.domain.dto.LotteryDto;
import com.lottery.main.domain.repository.LotteryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LotteryService.class)
class LotteryServiceTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    LotteryService lotteryService;

    @MockBean
    JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    private LotteryDto lotteryDto;

    @BeforeEach
    public void setUp() {
        lotteryDto = new LotteryDto(){
            {setTitle("MegaLottery"); setBallotLength(10); setBallotPrice(10); setDescription("gfdsgfds");   }
        };
    }
    @AfterEach
    public void tearDown() {
        lotteryDto =  null;
    }
/*
    @Test
    void addLottery() throws Exception{

        RequestBuilder request = MockMvcRequestBuilders.get("/all").accept(MediaType.APPLICATION_JSON);
        MvcResult result= mockMvc.perform(request).andReturn();
        assertEquals("no" ,result.getResponse().getContentAsString());
    }
*/
    @Test
    void editLottery() {
    }

    @Test
    void getAllLotteries() {
    }

    @Test
    void deleteLottery() {
    }
}