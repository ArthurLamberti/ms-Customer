package com.arthurlamberti.cdb.infrastructure.customer_wallet;

import com.arthurlamberti.cdb.adapters.feing.CustomerWalletExternal;
import com.arthurlamberti.cdb.adapters.models.CreateWalletDomain;
import com.arthurlamberti.cdb.infrastructure.customer_wallet.feign.CustomerWalletFeign;
import com.arthurlamberti.cdb.infrastructure.customer_wallet.models.CreateWalletRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerWalletGateway implements CustomerWalletExternal {

    private final CustomerWalletFeign customerWalletFeign;

    public CustomerWalletGateway(final CustomerWalletFeign customerWalletFeign) {
        this.customerWalletFeign = customerWalletFeign;
    }

    @Override
    public String createWallet(CreateWalletDomain request) {
        return customerWalletFeign.create(CreateWalletRequest.from(request));
    }
}
