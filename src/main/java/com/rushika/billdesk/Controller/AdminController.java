package com.rushika.billdesk.Controller;

import com.rushika.billdesk.DTO.*;
import com.rushika.billdesk.Service.*;
import com.rushika.billdesk.entity.Admin;
import com.rushika.billdesk.entity.Report;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ===== Admin Registration =====
    @PostMapping("/register")
    public ResponseEntity<AdminResponseDTO> register(@RequestBody AdminRequestDTO dto) {
        System.out.println("DTO received: " + dto);
        System.out.println("Name: " + dto.getName());
        System.out.println("Email: " + dto.getEmail());
        System.out.println("Password: " + dto.getPassword());
        return new ResponseEntity<>(adminService.createAdmin(dto), HttpStatus.CREATED);
    }

    // ===== Admin Login =====
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody AdminLoginDTO loginRequest, HttpServletRequest request) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // ✅ Force session creation (important!)
//        request.getSession(true);
//        // ensures JSESSIONID is created
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("Authorities: " + auth.getAuthorities());
//
//
//        return ResponseEntity.ok("Login successful");
//    }




        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody AdminLoginDTO loginRequest, HttpServletRequest request) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // ✅ Set Authentication in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // ✅ Create session and attach SecurityContext
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());

            return ResponseEntity.ok("Login successful");
        }



    // ===== Products =====
    @PostMapping("/products/add")
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO dto) {
        return productService.createProduct(dto);
    }

    @GetMapping("products/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("products/all")
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("products/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // ===== Invoices =====
    @PostMapping("invoices/create/{customerId}")
    public InvoiceResponseDTO createInvoice(@PathVariable Long customerId,
                                            @RequestBody InvoiceRequestDTO dto) {
        return invoiceService.createInvoice(customerId, dto);
    }

    @GetMapping("invoices/{id}")
    public InvoiceResponseDTO getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping("invoices/customer/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesByCustomer(@PathVariable Long customerId) {
        return invoiceService.getInvoicesByCustomer(customerId);
    }

    @DeleteMapping("invoices/delete/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }

    // ===== Payments =====
    @PostMapping("payments/make")
    public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO dto) {
        return paymentService.makePayment(dto);
    }

    @GetMapping("payments/{id}")
    public PaymentResponseDTO getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("payments/invoice/{invoiceId}")
    public List<PaymentResponseDTO> getPaymentsByInvoice(@PathVariable Long invoiceId) {
        return paymentService.getPaymentsByInvoice(invoiceId);
    }

    @GetMapping("payments/customer/{customerId}")
    public List<PaymentResponseDTO> getPaymentsByCustomer(@PathVariable Long customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }

    // ===== Reports =====
   @GetMapping("/generate")
    public Report generateReport(@RequestParam String type,
                                 @RequestParam Long adminId) {
        Admin admin = adminService.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        return reportService.generateReport(type, null, null, admin);
    }
}
