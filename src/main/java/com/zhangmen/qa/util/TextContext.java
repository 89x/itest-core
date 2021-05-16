package com.zhangmen.qa.util;

import org.apache.http.Header;

/***
 * 上下文，自动传入
 * barry
 */
public class TextContext {

    private Header headerl;

    public void setHeaderl(Header headerl) {
        this.headerl = headerl;
    }

    public Header getHeaderl() {
        return headerl;
    }
}
