package com.wanted.wantedlab.dto.jobPost.response;

import com.wanted.wantedlab.dto.jobPost.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobPostInfoList {
  private Integer page;
  private Integer size;
  private Boolean hasNext;
  private List<JobPostInfo> jobPosts;

  public static JobPostInfoList of(Slice<JobPost> jobPostSlice){
    List<JobPostInfo> jobPosts = jobPostSlice.getContent().stream().map(JobPostInfo::of).toList();
    int page = jobPostSlice.getPageable().getPageNumber();
    int size = jobPostSlice.getSize();
    boolean hasNext = jobPostSlice.hasNext();
    return new JobPostInfoList(page,size,hasNext,jobPosts);
  }
}
