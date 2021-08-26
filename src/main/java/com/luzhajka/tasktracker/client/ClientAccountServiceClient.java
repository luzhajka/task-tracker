package com.luzhajka.tasktracker.client;

import com.luzhajka.tasktracker.client.dto.DebtDto;
import com.luzhajka.tasktracker.client.dto.PaymentDto;
import com.luzhajka.tasktracker.client.dto.ReplenishDto;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@FeignClient(value = "accountClient", url = "https://127.0.0.1/8082/client-account-service")
public interface ClientAccountServiceClient {

    /**Создать счет клиента
     * @param clientId ID заказчика
     * @return Номер клиентского счета
     */
    @PostMapping("/account/new")
    UUID createAccount(@RequestParam("clientId") Long clientId);


    @PutMapping(value = "/account/deposit")
    void replenishAccount(@RequestBody ReplenishDto replenishDto);

    @PutMapping(value = "/account/pay")
    void payProject(@RequestBody DebtDto debtDto);

    @GetMapping(value = "/account/operations/project")
    List<PaymentDto> getProjectPayment(@RequestParam("projectId") Long projectId);

    @GetMapping(value = "/account/operations")
    List<PaymentDto> getOperations(@RequestParam("clientAccountId") UUID clientAccountId);

    @GetMapping(value = "/account/balance")
    BigInteger getBalance(@RequestParam("clientAccountId") UUID clientAccountId);

}
