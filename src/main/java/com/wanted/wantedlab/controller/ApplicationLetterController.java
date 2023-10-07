package com.wanted.wantedlab.controller;

import com.wanted.wantedlab.dto.applicationLetter.request.ApplyRequest;
import com.wanted.wantedlab.dto.applicationLetter.response.ApplyResult;
import com.wanted.wantedlab.service.ApplicationLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplicationLetterController {
  private final ApplicationLetterService applicationLetterService;
  @PostMapping("apply")
  public ApplyResult apply(@RequestBody ApplyRequest applyRequest){
    return applicationLetterService.apply(applyRequest);
  }
}
