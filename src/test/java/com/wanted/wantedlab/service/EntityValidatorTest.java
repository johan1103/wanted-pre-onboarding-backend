package com.wanted.wantedlab.service;

import com.wanted.wantedlab.entity.Company;
import com.wanted.wantedlab.entity.JobPost;
import com.wanted.wantedlab.repository.CompanyRepository;
import com.wanted.wantedlab.repository.JobPostRepository;
import com.wanted.wantedlab.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntityValidatorTest {
  @InjectMocks
  EntityValidator entityValidator;
  @Mock
  JobPostRepository jobPostRepository;
  @Mock
  CompanyRepository companyRepository;
  @Mock
  UserRepository userRepository;

  @Test
  @DisplayName("validateJobPost 성공 테스트")
  void validateJobPost_success(){
    //given
    Company sampleCompany = new Company(1L,"sample-company","sample-country","sample-region");
    JobPost expectedResult = new JobPost(1L,"sample-position","sample-content",
            "sample-skills",100000,sampleCompany);
    when(jobPostRepository.findById(anyLong())).thenReturn(Optional.of(expectedResult));

    //when
    JobPost result = entityValidator.validateJobPost(1L);

    //then
    assertThat(result).usingRecursiveComparison().isEqualTo(result);
    verify(jobPostRepository,times(1)).findById(anyLong());
  }
}
