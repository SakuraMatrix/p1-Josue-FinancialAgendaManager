package com.elwinJ.financialagendamanager.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.io.ByteArrayOutputStream;

public class Mapper {
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static  ByteBuf toByteBuf(Object o) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            OBJECT_MAPPER.writeValue(out,o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }
}
