package com.hpe.itsma.bo.ats.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by xuyuf on 2017/6/23.
 */
public class JSONFormatHelper {

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
