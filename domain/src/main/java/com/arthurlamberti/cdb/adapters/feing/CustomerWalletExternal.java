package com.arthurlamberti.cdb.adapters.feing;

import com.arthurlamberti.cdb.adapters.models.CreateWalletDomain;

public interface CustomerWalletExternal {

    String createWallet(final CreateWalletDomain request);

    Double getBalance(final String customerId);
}
