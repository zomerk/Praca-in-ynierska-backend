package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Complaint;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComplaintRepository extends CrudRepository<Complaint,Integer>, PagingAndSortingRepository<Complaint,Integer> {
    Page<Complaint> findAllByStatus(Status status,Pageable pageable);
}
