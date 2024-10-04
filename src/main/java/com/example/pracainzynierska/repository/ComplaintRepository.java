package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Complaint;
import com.example.pracainzynierska.entity.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComplaintRepository extends CrudRepository<Complaint,Integer>, PagingAndSortingRepository<Complaint,Integer> {
    @Query("select c from Complaint c where c.status = 'Open'")
    Page<Complaint> findAllWithOpenStatus(Pageable pageable);
}
