package com.testone.web.pxy;

import java.util.function.Function;

public class Proxy {

	public String string(Object param) {
        Function<Object, String> f =  String :: valueOf;
        return f.apply(param);
    }
}
