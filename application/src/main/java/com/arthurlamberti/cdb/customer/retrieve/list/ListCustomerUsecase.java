package com.arthurlamberti.cdb.customer.retrieve.list;

import com.arthurlamberti.cdb.NullaryUseCase;
import com.arthurlamberti.cdb.Pagination;
import com.arthurlamberti.cdb.UseCase;

public sealed abstract class ListCustomerUsecase
        extends NullaryUseCase<Pagination<ListCustomerOutput>>
        permits DefaultListCustomerUsecase {
}
