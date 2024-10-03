package com.example.job.service;

import com.example.job.dto.JobDto;
import com.example.job.entity.Job;

import java.util.List;

public interface JobService {
    List<JobDto> findAll();
    void createJob(Job job);

    JobDto getJobById(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
