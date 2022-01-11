package com.lottery.main.api;

import com.lottery.main.api.security.JwtTokenUtil;
import com.lottery.main.domain.dto.LotteryDto;
import com.lottery.main.service.JwtUserDetailsService;
import com.lottery.main.service.LotteryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(LotteryService.class)
class LotteryControllerTest {

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