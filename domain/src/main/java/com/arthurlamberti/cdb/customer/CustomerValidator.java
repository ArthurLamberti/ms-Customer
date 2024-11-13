package com.arthurlamberti.cdb.customer;

import com.arthurlamberti.cdb.validation.Error;
import com.arthurlamberti.cdb.validation.ValidationHandler;
import com.arthurlamberti.cdb.validation.Validator;

import static java.util.Objects.isNull;

public class CustomerValidator extends Validator {

    private Customer customer;

    protected CustomerValidator(final ValidationHandler aHandler, final Customer aCustomer) {
        super(aHandler);
        this.customer = aCustomer;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDocumentConstraints();
        checkEmailConstraints();
    }

    private void checkNameConstraints(){
        final var name = customer.getName();
        if (isNull(name)){
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }
        if (name.isBlank()){
            this.validationHandler().append(new Error("'name' should not be empty"));
        }
    }

    private void checkDocumentConstraints(){
        final var document = customer.getDocument();
        if (isNull(document)){
            this.validationHandler().append(new Error("'document' should not be null"));
            return;
        }
        if (document.isBlank()){
            this.validationHandler().append(new Error("'document' should not be empty"));
        }
    }

    private void checkEmailConstraints(){
        final var email = customer.getEmail();
        if (isNull(email)){
            this.validationHandler().append(new Error("'email' should not be null"));
            return;
        }
        if (email.isBlank()){
            this.validationHandler().append(new Error("'email' should not be empty"));
        }
    }
}
