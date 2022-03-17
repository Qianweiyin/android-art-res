package com.google.zxing;

public class ELinkAbsException  extends Exception {
    private static final long serialVersionUID = -2623309261327598087L;
    public String msg;
    public int statusCode;

    public ELinkAbsException(Exception exc) {
        super(exc);
        this.statusCode = -1;
        this.msg = "";
    }

    public ELinkAbsException(String str) {
        super(str);
        this.statusCode = -1;
        this.msg = "";
        this.msg = str;
    }

    public ELinkAbsException(String str, int i) {
        super(str);
        this.statusCode = -1;
        this.msg = "";
        this.msg = str;
        this.statusCode = i;
    }

    public ELinkAbsException(String str, Exception exc) {
        super(str, exc);
        this.statusCode = -1;
        this.msg = "";
        this.msg = str;
    }

    public ELinkAbsException(String str, Exception exc, int i) {
        super(str, exc);
        this.statusCode = -1;
        this.msg = "";
        this.msg = str;
        this.statusCode = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
