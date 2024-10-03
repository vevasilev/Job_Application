package com.example.job.service;

import com.example.job.dto.JobDto;
import com.example.job.dto.mapper.Mapper;
import com.example.job.entity.Company;
import com.example.job.entity.Job;
import com.example.job.entity.Review;
import com.example.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final Mapper<Job, JobDto> mapper;
    private final RestTemplate restTemplate;

    @Override
    public List<JobDto> findAll() {
        return jobRepository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDto getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return job != null ? convertToDto(job) : null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        } else {
            return false;
        }
    }

    private JobDto convertToDto(Job job) {
        JobDto jobDto = mapper.toDto(job);
        Company company = restTemplate
                .getForObject("http://COMPANY:8081/companies/" + job.getCompanyId(), Company.class);
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW:8083/reviews?companyId=" + company.id(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        List<Review> reviews = reviewResponse.getBody();
        jobDto.setCompany(company);
        jobDto.setReview(reviews);
        return jobDto;
    }
}
