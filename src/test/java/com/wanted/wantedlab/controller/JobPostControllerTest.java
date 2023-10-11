package com.wanted.wantedlab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import com.wanted.wantedlab.dto.jobPost.response.JobPostUploadResult;
import com.wanted.wantedlab.global.exception.GlobalExceptionController;
import com.wanted.wantedlab.service.JobPostService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class JobPostControllerTest {
  @InjectMocks
  private JobPostController jobPostController;
  @Mock
  private JobPostService jobPostService;
  private final ObjectMapper mapper = new ObjectMapper();
  private MockMvc mockMvc;
  @BeforeEach
  public void init(){
    mockMvc = MockMvcBuilders.standaloneSetup(jobPostController)
            .setControllerAdvice(GlobalExceptionController.class)
            .build();
  }
  @Test
  @DisplayName("uploadJobPost 성공 테스트")
  void uploadJobPost_success() throws Exception {
    //given
    Long samplePostId = 1L;
    JobPostUploadResult expectedResult = new JobPostUploadResult(samplePostId);
    when(jobPostService.upload(any())).thenReturn(expectedResult);
    JobPostUploadRequest request = new JobPostUploadRequest("sample position","sample content","sample skills",
            100000,1L);

    //when
    ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/job-post").content(mapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
    );

    //then
    resultActions.andExpect(status().isOk())
                    .andExpect((MvcResult r)->{
                      String body = r.getResponse().getContentAsString();
                      JobPostUploadResult response = mapper.readValue(body,JobPostUploadResult.class);
                      Assertions.assertThat(response.getJobPostId()).isEqualTo(expectedResult.getJobPostId());
                    });
    verify(jobPostService,Mockito.times(1)).upload(any());
  }

}
