package com.wanted.wantedlab.service;

import com.wanted.wantedlab.dto.exception.JobPostException;
import com.wanted.wantedlab.dto.exception.JobPostExceptionInfo;
import com.wanted.wantedlab.dto.exception.UserException;
import com.wanted.wantedlab.dto.exception.UserExceptionInfo;
import com.wanted.wantedlab.entity.Company;
import com.wanted.wantedlab.entity.JobPost;
import com.wanted.wantedlab.entity.User;
import com.wanted.wantedlab.repository.CompanyRepository;
import com.wanted.wantedlab.repository.JobPostRepository;
import com.wanted.wantedlab.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
  @Test
  @DisplayName("validateJobPost 실패 테스트 [Id에 맞는 JobPost 없는 경우]")
  void validateJobPost_failed_invalid_jobPost_id(){
    //given
    when(jobPostRepository.findById(anyLong())).thenReturn(Optional.empty());

    String expectedMessage = "invalid job post id";

    //when
    JobPostException exception = assertThrows(JobPostException.class,
            ()->entityValidator.validateJobPost(1L));
    //then
    assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    assertThat(exception.getExceptionInfo()).isEqualTo(JobPostExceptionInfo.INVALID_JOBPOSTID);
    assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    verify(jobPostRepository,times(1)).findById(anyLong());
  }
  @Test
  @DisplayName("validateCompany 성공 테스트")
  void validateCompany_success(){
    //given
    Company expectedResult = new Company(1L,"sample-company","sample-country","sample-region");
    when(companyRepository.findById(anyLong())).thenReturn(Optional.of(expectedResult));

    //when
    Company result = entityValidator.validateCompany(1L);

    //then
    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    verify(companyRepository,times(1)).findById(anyLong());

  }
  @Test
  @DisplayName("validateCompany 실패 테스트 [Id에 맞는 Company 없는 경우]")
  void validateCompany_failed_invalid_company_id(){
    //given
    String expectedMessage = "invalid company id";
    when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());

    //when
    JobPostException exception = assertThrows(JobPostException.class,
            ()->entityValidator.validateCompany(1L));

    //then
    assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    assertThat(exception.getExceptionInfo()).isEqualTo(JobPostExceptionInfo.INVALID_COMPANY);
    assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    verify(companyRepository,times(1)).findById(anyLong());
  }

  @Test
  @DisplayName("validateUser 성공 테스트")
  void validateUser_success(){
    //given
    User expectedResult = new User("sample-id","sample-nickname");
    when(userRepository.findById(anyString())).thenReturn(Optional.of(expectedResult));

    //when
    User result = entityValidator.validateUser("sample-id");

    //then
    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    verify(userRepository,times(1)).findById(anyString());
  }
  @Test
  @DisplayName("validateUser 실패 테스트 [Id에 맞는 User 없는 경우]")
  void validateUser_failed_invalid_user_id(){
    //given
    String expectedMessage = "invalid user id";
    when(userRepository.findById(anyString())).thenReturn(Optional.empty());

    //when
    UserException exception = assertThrows(UserException.class,
            ()->entityValidator.validateUser("sample-id"));

    //then
    assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    assertThat(exception.getExceptionInfo()).isEqualTo(UserExceptionInfo.INVALID_USERID);
    assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
  }
}
