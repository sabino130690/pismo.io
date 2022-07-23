package com.pismo.io.evaluation.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum OperationType {

	OMPRA_A_VISTA(1, "COMPRA_A_VISTA"),
	COMPRA_PARCELADA(2, "COMPRA_PARCELADA"),
	SAQUE(3, "SAQUE"),
	PAGAMENTO(4, "PAGAMENTO");

	private Integer code;
	private String detail;

	public static OperationType getByCode(final Integer code) {
		for (OperationType modality : values()) {
			if (modality.getCode().equals(code))
				return modality;
		}
		throw new IllegalArgumentException();
	}
}
