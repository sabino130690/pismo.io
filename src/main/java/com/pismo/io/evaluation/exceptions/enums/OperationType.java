package com.pismo.io.evaluation.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@ToString
public enum OperationType {

	COMPRA_A_VISTA(1L, "COMPRA_A_VISTA"),
	COMPRA_PARCELADA(2L, "COMPRA_PARCELADA"),
	SAQUE(3L, "SAQUE"),
	PAGAMENTO(4L, "PAGAMENTO");

	private Long code;
	private String detail;

	public static OperationType getByCode(final Long code) {
		return Arrays.stream(values()).filter(value -> value.getCode().equals(code)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.valueOf(code)));
	}
}
