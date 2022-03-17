package com.google.zxing;

import android.content.Context;

/**
 * public abstract class a {
 *     private boolean czj = false;
 *     private a czk;
 *     private a czl;
 */
public abstract class ELinkClassC {

    private boolean czj = false;
    private ELinkClassC czk;
    private ELinkClassC czl;
    private Object mData;

    public ELinkClassC(Object obj) {
        this.mData = obj;
    }

    public ELinkAbsException P(String str, int i) {
        return new ELinkAbsException(str, i);
    }

    public final void a(int i, ELinkAbsException absException) {
        a(i, this.mData, absException);
    }

    public abstract void a(int i, Object obj, ELinkAbsException absException);

    public void a(ELinkClassC aVar) {
    }

    public void a(ELinkClassC aVar, ELinkAbsException absException) {
    }

    public final void a(ELinkAbsException absException) {
        a(this, absException);
    }

    public abstract void a(Object obj, Context context);

    public final void acG() {
        a(this);
    }

    public boolean acH() {
        return this.czj;
    }

    public ELinkClassC acI() {
        ELinkClassC aVar = this;
        while (aVar.acK() != null) {
            aVar = aVar.acK();
        }
        return aVar;
    }

    public void acJ() {
        ELinkClassC aVar = this;
        while (aVar.acL() != null) {
            aVar = aVar.acL();
        }
        while (true) {
            ELinkClassC acK = aVar.acK();
            aVar.b(null);
            if (acK != null) {
                aVar = acK;
            } else {
                return;
            }
        }
    }

    public ELinkClassC acK() {
        return this.czl;
    }

    public ELinkClassC acL() {
        return this.czk;
    }

    protected void b(ELinkClassC aVar) {
        this.czl = aVar;
    }

    public final void bP(Context context) {
        a(this.mData, context);
    }

    public abstract void e(int i, Object obj);

    public final void kV(int i) {
        e(i, this.mData);
    }
}
