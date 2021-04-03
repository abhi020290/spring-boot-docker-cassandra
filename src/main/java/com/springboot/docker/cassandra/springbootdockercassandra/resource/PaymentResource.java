package com.springboot.docker.cassandra.springbootdockercassandra.resource;


import com.springboot.docker.cassandra.springbootdockercassandra.entity.PaymentDetails;
import com.springboot.docker.cassandra.springbootdockercassandra.repository.PaymentRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentResource {

    @Autowired
    public PaymentRepository paymentRepository;

    @PostMapping(value = "/save")
    public ResponseEntity<PaymentDetails> save(@RequestBody PaymentDetails paymentDetails) {
        try {
            PaymentDetails save = paymentRepository.save(paymentDetails);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDetails> fetchPaymentById(@PathVariable("paymentId") String id) {
        Optional<PaymentDetails> paymentById = null;
        try{
            if (StringUtils.isNotBlank(id) || StringUtils.isNotEmpty(id)) {
                paymentById = paymentRepository.findByPaymentId(id);
            }
            if(!paymentById.isPresent()){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(paymentById.get(),HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/transaction/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDetails>> findByTransactionId(@PathVariable("transactionId") String id) {
        List<PaymentDetails> paymentById = new ArrayList<>();
        try{
            if (StringUtils.isNotBlank(id) || StringUtils.isNotEmpty(id)) {
                paymentById = paymentRepository.findByTransactionId(id);
            }
            if(paymentById.size() > 0){
                return new ResponseEntity<>(paymentById,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/auth/{authStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDetails>> fi(@PathVariable("authStatus") String id) {
        List<PaymentDetails> paymentById = new ArrayList<>();
        try{
            if (StringUtils.isNotBlank(id) || StringUtils.isNotEmpty(id)) {
                paymentById = paymentRepository.findByAuthStatus(id);
            }
            if(paymentById.size() > 0){
                return new ResponseEntity<>(paymentById,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/transaction/all/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDetails>> findAllByTransactionId(@PathVariable("transactionId") String id) {
        List<PaymentDetails> paymentById = new ArrayList<>();
        try{
            if (StringUtils.isNotBlank(id) || StringUtils.isNotEmpty(id)) {
                paymentById = paymentRepository.findAllByTransactionId(id);
            }
            if(paymentById.size() > 0){
                return new ResponseEntity<>(paymentById,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/transaction/{transactionId}/auth/{authStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentDetails>> findByTransactionAndAuthStatus(@PathVariable("transactionId") String id,
                                                                               @PathVariable("authStatus") String authStatus) {
        List<PaymentDetails> paymentById = new ArrayList<>();
        try{
            if (StringUtils.isNotBlank(id) || StringUtils.isNotEmpty(id)) {
                paymentById = paymentRepository.findByTransactionAndAuthStatus(id,authStatus);
            }
            if(paymentById.size() > 0){
                return new ResponseEntity<>(paymentById,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
