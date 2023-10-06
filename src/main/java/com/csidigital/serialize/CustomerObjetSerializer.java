package com.csidigital.serialize;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.csidigital.models.Customer;

import java.io.IOException;

public class CustomerObjetSerializer extends StdSerializer<Customer> {

    public CustomerObjetSerializer()
    {

        this(null);
    }

    public CustomerObjetSerializer(Class<Customer> t) {
        super(t);

    }

    @Override
    public void serialize(Customer item,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {



    }
}
