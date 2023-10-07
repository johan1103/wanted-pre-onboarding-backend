package com.wanted.wantedlab.controller;

import com.wanted.wantedlab.dto.applicationLetter.response.ApplyResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationLetterController {
  @PostMapping("apply")
  public ApplyResult apply(@RequestParam("user-id") Long userId,@RequestParam("job-post-id") Long jobPostId){
    return null;
  }
}
