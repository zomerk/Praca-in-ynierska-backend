package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.Segment;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.postgresql.util.LruCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends CrudRepository<Segment, Integer> {
}
