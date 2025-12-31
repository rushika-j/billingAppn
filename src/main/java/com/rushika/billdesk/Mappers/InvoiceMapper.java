package com.rushika.billdesk.Mappers;


import com.rushika.billdesk.DTO.InvoiceItemDTO;
import com.rushika.billdesk.DTO.InvoiceRequestDTO;
import com.rushika.billdesk.DTO.InvoiceResponseDTO;
import com.rushika.billdesk.entity.Invoice;
import com.rushika.billdesk.entity.InvoiceItem;
import com.rushika.billdesk.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class InvoiceMapper {

    public static Invoice toEntity(InvoiceRequestDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setStatus(dto.getStatus());

        List<InvoiceItem> items = dto.getItems().stream().map(itemDTO -> {
            InvoiceItem item = new InvoiceItem();
            Product product = new Product();
            product.setId(itemDTO.getProductId());
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(itemDTO.getUnitPrice());
            item.setTotalPrice(itemDTO.getTotalPrice());
            item.setInvoice(invoice);
            return item;
        }).collect(Collectors.toList());

        invoice.setItems(items);
        return invoice;
    }

    public static InvoiceResponseDTO toDTO(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setId(invoice.getId());
        dto.setStatus(invoice.getStatus());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setTotalAmount(invoice.getTotalAmount());

        dto.setItems(invoice.getItems().stream().map(item -> {
            InvoiceItemDTO itemDTO = new InvoiceItemDTO();
            itemDTO.setProductId(item.getProduct().getId());

            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setUnitPrice(item.getUnitPrice());
            itemDTO.setTotalPrice(item.getTotalPrice());
            return itemDTO;
        }).collect(Collectors.toList()));

        return dto;
    }
}
