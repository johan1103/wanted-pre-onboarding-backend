package com.wanted.wantedlab.service;

import com.wanted.wantedlab.dto.jobPost.request.JobPostUploadRequest;
import com.wanted.wantedlab.dto.jobPost.response.JobPostUploadResult;
import com.wanted.wantedlab.entity.Company;
import com.wanted.wantedlab.entity.JobPost;
import com.wanted.wantedlab.repository.ApplicationLetterRepository;
import com.wanted.wantedlab.repository.DeletedApplicationLetterRepository;
import com.wanted.wantedlab.repository.JobPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
