package com.springboot.docker.cassandra.springbootdockercassandra.repository;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.springboot.docker.cassandra.springbootdockercassandra.entity.PaymentDetails;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Consistency;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends CassandraRepository<PaymentDetails,String> {

    @Consistency(value = DefaultConsistencyLevel.ONE)
    Optional<PaymentDetails> findByPaymentId(String paymentId);

    @AllowFiltering
    @Consistency(value = DefaultConsistencyLevel.ONE)
    List<PaymentDetails> findByTransactionId(String transactionId);

    @AllowFiltering
    @Consistency(value = DefaultConsistencyLevel.ONE)
    List<PaymentDetails> findByAuthStatus(String authStatus);

    @Consistency(value = DefaultConsistencyLevel.ONE)
    @Query( value = "SELECT * FROM payment_details WHERE transaction_id = ?0 ALLOW FILTERING", allowFiltering = true)
    List<PaymentDetails> findAllByTransactionId(String transactionId);

    @Consistency(value = DefaultConsistencyLevel.ONE)
    @Query( value = "SELECT * FROM payment_details WHERE transaction_id = ?0 and auth_status= ?1 ALLOW FILTERING", allowFiltering = true)
    List<PaymentDetails> findByTransactionAndAuthStatus(String transactionId, String authStatus);
}
