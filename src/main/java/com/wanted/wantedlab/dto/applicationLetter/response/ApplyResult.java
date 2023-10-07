package com.wanted.wantedlab.dto.applicationLetter.response;

import com.wanted.wantedlab.entity.ApplicationLetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApplyResult {
  private Long applyId;
  private Long jobPostId;
  private Long userId;

  public static ApplyResult of(ApplicationLetter letter){
    return new ApplyResult(letter.getId(),letter.getJobPost().getId(),letter.getUser().getId());
  }
}
