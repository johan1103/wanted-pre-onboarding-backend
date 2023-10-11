package com.wanted.wantedlab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.wantedlab.dto.applicationLetter.request.ApplyRequest;
import com.wanted.wantedlab.dto.applicationLetter.response.ApplyResult;
import com.wanted.wantedlab.global.exception.GlobalExceptionController;
import com.wanted.wantedlab.service.ApplicationLetterService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ApplicationLetterControllerTest {
  @InjectMocks
  ApplicationLetterController applicationLetterController;
  @Mock
  ApplicationLetterService applicationLetterService;
  MockMvc mockMvc;
  private final ObjectMapper mapper = new ObjectMapper();
  @BeforeEach
  public void init(){
    mockMvc = MockMvcBuilders.standaloneSetup(applicationLetterController)
            .setControllerAdvice(GlobalExceptionController.class)
            .build();
  }

  @Test
  @DisplayName("apply 성공 테스트")
  void apply_success()throws Exception{
    //given
    ApplyRequest request = new ApplyRequest("sample-id",1L,"sample-portfolio");
    ApplyResult expectedResult = new ApplyResult(1L,1L,"sample-id");
    when(applicationLetterService.apply(any())).thenReturn(expectedResult);

    //when
    ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/apply").content(mapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
    );

    //then
    resultActions.andExpect(status().isOk())
            .andExpect((MvcResult r)->{
              String body = r.getResponse().getContentAsString();
              ApplyResult result = mapper.readValue(body,ApplyResult.class);
              assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
            });
    verify(applicationLetterService,times(1)).apply(any());
  }
}
