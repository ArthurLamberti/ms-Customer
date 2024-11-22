package com.arthurlamberti.cdb.infrastructure.customer_wallet.feign;

import com.arthurlamberti.cdb.adapters.models.CreateWalletDomain;
import com.arthurlamberti.cdb.infrastructure.customer_wallet.models.CreateWalletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "CustomerWalletFeign",
        url = "http://127.0.0.1:8081/api"
)
public interface CustomerWalletFeign {

    @PostMapping(value = "/wallets")
    String create(@RequestBody CreateWalletRequest request);

}
