package com.zb.reservation.store.repository;

import com.zb.reservation.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreName(String storeName);

    @Query(value = "SELECT s.id, s.store_name, s.address, s.description, s.score," +
            "ROUND(ST_DISTANCE(s.location, POINT(:lnt, :lat)), 2) AS distance " +
            "FROM store s " +
            "ORDER BY store_name ", nativeQuery = true)
    List<Object[]> findAllOrderByStoreName(double lnt, double lat);

    @Query(value = "SELECT s.id, s.store_name, s.address, s.description, s.score," +
            "ROUND(ST_DISTANCE(s.location, POINT(:lnt, :lat)), 2) AS distance " +
            "FROM store s " +
            "ORDER BY score desc ", nativeQuery = true)
    List<Object[]> findAllOrderByScore(double lnt, double lat);

    @Query(value = "SELECT s.id, s.store_name, s.address, s.description, s.score," +
            "ROUND(ST_DISTANCE(s.location, POINT(:lnt, :lat)), 2) AS distance " +
            "FROM store s " +
            "ORDER BY distance ", nativeQuery = true)
    List<Object[]> findAllOrderByDistance(double lnt, double lat);

    @Query(value = "SELECT s.id, s.store_name, s.address, s.description, s.score," +
            "ROUND(ST_DISTANCE(s.location, POINT(:lnt, :lat)), 2) AS distance " +
            "FROM store s " +
            "WHERE LOWER(s.store_name) LIKE  LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Object[]> findAllByStoreNameContaining(String keyword, double lnt, double lat);
}
