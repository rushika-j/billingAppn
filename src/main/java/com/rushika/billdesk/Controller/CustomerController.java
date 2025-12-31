package com.rushika.billdesk.Controller;

import com.rushika.billdesk.DTO.CustomerLoginDTO;
import com.rushika.billdesk.DTO.CustomerRequestDTO;
import com.rushika.billdesk.DTO.InvoiceResponseDTO;
import com.rushika.billdesk.DTO.PaymentResponseDTO;
import com.rushika.billdesk.Service.CustomerService;
import com.rushika.billdesk.Service.InvoiceService;
import com.rushika.billdesk.Service.PaymentService;
import com.rushika.billdesk.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired private CustomerService customerService;
    @Autowired private InvoiceService invoiceService;
    @Autowired private PaymentService paymentService;
    @Autowired private AuthenticationManager authenticationManager;

    // --- Register ---
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerRequestDTO dto) {
        customerService.register(dto);
        return ResponseEntity.ok("Customer registered successfully");
    }

    // --- Login ---
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomerLoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login successful");
    }

    // --- View Invoices ---
    @GetMapping("/{customerId}/invoices")
    public List<InvoiceResponseDTO> getInvoices(@PathVariable Long customerId) {
        return invoiceService.getInvoicesByCustomer(customerId);
    }

    // --- View Payments ---
    @GetMapping("/{customerId}/payments")
    public List<PaymentResponseDTO> getPaymentsByCustomer(@PathVariable Long customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }
}
