package com.luzhajka.tasktracker.client.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;
import java.util.UUID;

public class DebtDto {
    /**
     * сумма (amount) в копейках (учитывать при отображении)
     */
    @Schema(description = "сумма операции")
    private BigInteger amount;

    @Schema(description = "номер лицевого счета")
    private UUID accountNumber;

    @Schema(description = "ID прокта")
    private Long projectId;


    public DebtDto() {
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectID) {
        this.projectId = projectID;
    }
}
