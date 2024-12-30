package com.exchange.consultas;

import java.util.Map;

public record Moneda(Map<String, Double> conversion_rates) {

}
