package com.arthurlamberti.cdb.adapters.feing;

import com.arthurlamberti.cdb.adapters.models.CreateWalletDomain;

public interface CustomerWalletExternal {

    String createWallet(CreateWalletDomain request);

}
