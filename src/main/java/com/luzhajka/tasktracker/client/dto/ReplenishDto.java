package com.luzhajka.tasktracker.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;
import java.util.UUID;

public class ReplenishDto {
    /**
     * сумма (amount) в копейках (учитывать при отображении)
     */
    @Schema(description = "сумма операции")
    private BigInteger amount;

    @Schema(description = "номер клиентского счета")
    private UUID accountNumber;

    public ReplenishDto(BigInteger amount, UUID accountNumber) {
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public ReplenishDto() {
    }


    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(UUID accountNumber) {
        this.accountNumber = accountNumber;
    }
}

