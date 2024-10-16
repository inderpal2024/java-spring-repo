package com.gem.customerservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Customer(@Id String id, String name, Integer age) {
}