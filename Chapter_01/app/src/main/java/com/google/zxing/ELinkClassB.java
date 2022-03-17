package com.google.zxing;

import android.graphics.Bitmap;

/**
 *  public class b implements a {
 *  private Bitmap bitmap;
 *
 */
public class ELinkClassB implements ELinkInterfaceC {

    private Bitmap bitmap;
    public ELinkInterfaceD gIe;


    public ELinkClassB(Bitmap bitmap, ELinkInterfaceD cVar) {
        this.bitmap = bitmap;
        this.gIe = cVar;
    }

    public ELinkClassB(String str) {
        BC(str);
    }

    private void BC(final String str) {

    }

    private void L(final Bitmap bitmap) {

    }

    @Override
    public void a(ELinkInterfaceD cVar) {
        this.gIe = cVar;
    }

    @Override
    public void buK() {
        L(this.bitmap);
    }
}
