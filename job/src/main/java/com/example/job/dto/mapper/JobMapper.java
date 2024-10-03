package com.example.job.dto.mapper;

import com.example.job.dto.JobDto;
import com.example.job.entity.Job;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class JobMapper implements Mapper<Job, JobDto> {

    @Override
    public Job toEntity(JobDto dto) {
        if(isNull(dto)) {
            return null;
        }
        return Job.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .minSalary(dto.getMinSalary())
                .maxSalary(dto.getMaxSalary())
                .location(dto.getLocation())
                .companyId(dto.getCompany().id())
                .build();
    }

    @Override
    public JobDto toDto(Job entity) {
        if (isNull(entity)) {
            return null;
        }
        return JobDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .minSalary(entity.getMinSalary())
                .maxSalary(entity.getMaxSalary())
                .location(entity.getLocation())
                .build();
    }
}


