package com.wanted.wantedlab.service;

import com.wanted.wantedlab.dto.jobPost.request.JobPostDeleteRequest;
import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import com.wanted.wantedlab.dto.jobPost.response.JobPostDeleteResult;
import com.wanted.wantedlab.dto.jobPost.response.JobPostUploadResult;
import com.wanted.wantedlab.entity.*;
import com.wanted.wantedlab.repository.ApplicationLetterRepository;
import com.wanted.wantedlab.repository.DeletedApplicationLetterRepository;
import com.wanted.wantedlab.repository.JobPostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobPostServiceTest {

  @InjectMocks
  JobPostService jobPostService;

  @Mock
  EntityValidator entityValidator;
  @Mock
  JobPostRepository jobPostRepository;
  @Mock
  ApplicationLetterRepository applicationLetterRepository;
  @Mock
  DeletedApplicationLetterRepository deletedApplicationLetterRepository;

  @Test
  @DisplayName("upload 성공 테스트")
  void upload_success(){
    //given
    Company sampleCompany = new Company(1L,"sample-company","sample-country","sample-region");
    JobPost sampleJobPost = new JobPost(1L,"sample-position","sample-content",
            "sample-skills",100000,sampleCompany);
    JobPostUploadRequest request = new JobPostUploadRequest("sample position","sample content",
            "sample skills", 100000,1L);
    when(entityValidator.validateCompany(anyLong())).thenReturn(sampleCompany);
    when(jobPostRepository.save(Mockito.any())).thenReturn(sampleJobPost);

    JobPostUploadResult expectedResult = new JobPostUploadResult(sampleJobPost.getId());

    //when
    JobPostUploadResult result = jobPostService.upload(request);

    //then
    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    verify(entityValidator, times(1)).validateCompany(anyLong());
    verify(jobPostRepository, times(1)).save(Mockito.any());
  }

  @Test
  @DisplayName("delete 성공 테스트 [해당 공고 지원자가 없을 때]")
  void delete_success_empty_letter(){
    //given
    JobPostDeleteRequest request = new JobPostDeleteRequest(1L);
    Company sampleCompany = new Company(1L,"sample-company","sample-country","sample-region");
    JobPost sampleJobPost = new JobPost(1L,"sample-position","sample-content",
            "sample-skills",100000,sampleCompany);
    List<ApplicationLetter> emptyLetters = new ArrayList<>();

    when(entityValidator.validateJobPost(anyLong())).thenReturn(sampleJobPost);
    when(applicationLetterRepository.findDeleteApplicationLetter(anyLong())).thenReturn(emptyLetters);
    when(deletedApplicationLetterRepository.saveAll(any())).thenReturn(null);
    doNothing().when(applicationLetterRepository).deleteLetterAssociatedJobPost(any());
    doNothing().when(jobPostRepository).delete(any());

    JobPostDeleteResult expectedResult = new JobPostDeleteResult(true);

    //when
    JobPostDeleteResult result = jobPostService.delete(request);

    //then
    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    verify(entityValidator,times(1)).validateJobPost(anyLong());
    verify(applicationLetterRepository,times(1)).findDeleteApplicationLetter(anyLong());
    verify(deletedApplicationLetterRepository,times(1)).saveAll(any());
    verify(applicationLetterRepository,times(1)).deleteLetterAssociatedJobPost(any());
    verify(jobPostRepository,times(1)).delete(any());
  }

  @Test
  @DisplayName("delete 성공 테스트 [해당 공고 지원자가 있을 때]")
  void delete_success_any_letter(){
    //given
    JobPostDeleteRequest request = new JobPostDeleteRequest(1L);
    Company sampleCompany = new Company(1L,"sample-company","sample-country","sample-region");
    JobPost sampleJobPost = new JobPost(1L,"sample-position","sample-content",
            "sample-skills",100000,sampleCompany);
    List<ApplicationLetter> sampleLetters = new ArrayList<>();
    for(long i=1;i<=3;i++){
      sampleLetters.add(new ApplicationLetter(i,sampleJobPost, null,"sample-portfolio"));
    }
    when(entityValidator.validateJobPost(anyLong())).thenReturn(sampleJobPost);
    when(applicationLetterRepository.findDeleteApplicationLetter(anyLong())).thenReturn(sampleLetters);
    when(deletedApplicationLetterRepository.saveAll(any())).thenReturn(null);
    doNothing().when(applicationLetterRepository).deleteLetterAssociatedJobPost(any());
    doNothing().when(jobPostRepository).delete(any());

    JobPostDeleteResult expectedResult = new JobPostDeleteResult(true);

    //when
    JobPostDeleteResult result = jobPostService.delete(request);

    //then
    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    verify(entityValidator,times(1)).validateJobPost(anyLong());
    verify(applicationLetterRepository,times(1)).findDeleteApplicationLetter(anyLong());
    verify(deletedApplicationLetterRepository,times(1)).saveAll(any());
    verify(applicationLetterRepository,times(1)).deleteLetterAssociatedJobPost(any());
    verify(jobPostRepository,times(1)).delete(any());
  }
  @Test
  @DisplayName("")
  void name(){
    //given


    //when


    //then
  }
}
