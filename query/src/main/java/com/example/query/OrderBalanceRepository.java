package com.example.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by msoldevi on 24/02/2017.
 */

@Repository
public interface OrderBalanceRepository extends JpaRepository<OrderBalance, String>{
}
