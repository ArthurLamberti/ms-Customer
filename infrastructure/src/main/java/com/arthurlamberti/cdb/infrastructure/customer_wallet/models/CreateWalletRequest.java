package com.arthurlamberti.cdb.infrastructure.customer_wallet.models;

import com.arthurlamberti.cdb.adapters.models.CreateWalletDomain;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateWalletRequest(
        @JsonProperty(value = "balance") Double balance,
        @JsonProperty(value = "customer_id") String customerId
) {

    public static CreateWalletRequest from(CreateWalletDomain domain) {
        return new CreateWalletRequest(domain.balance(), domain.customerId());
    }

}
