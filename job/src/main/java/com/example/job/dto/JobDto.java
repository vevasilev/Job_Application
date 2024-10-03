package com.example.job.dto;

import com.example.job.entity.Company;
import com.example.job.entity.Review;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> review;
}
