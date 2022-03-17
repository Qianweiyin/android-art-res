package com.google.zxing;

import android.content.Context;

/**
 * public class d implements a {
 *     private Context context;
 *     public c gIe;
 *     private e gIk;
 */
public class ELinkClassH implements ELinkInterfaceC {
    private Context context;
    public ELinkInterfaceD gIe;
    private TodoResult gIk;
    private ScanSuccessUtil gIp;
    private boolean gIq = false;
    private String gIr;

    public ELinkClassH(TodoResult eVar, Context context, String str) {
        this.context = context;
        this.gIk = eVar;
        this.gIp = new ScanSuccessUtil(context, str, new ScanSuccessInterface() { // from class: com.yunzhijia.scan.b.d.1
            @Override // com.yunzhijia.scan.c.b
            public void btC() {
                ELinkClassH.this.gIe.afY();
            }
        });
        d(eVar);
    }

    private void d(TodoResult eVar) {
        try {
            this.gIp.c(eVar);
            this.gIq = true;
        } catch (Exception e) {
            this.gIq = false;
            this.gIr = e.getMessage();
        }
    }

    @Override
    public void a(ELinkInterfaceD cVar) {
        this.gIe = cVar;
        if (this.gIq) {
            this.gIe.k(2, this.gIk);
        } else {
            this.gIe.kc(this.gIr);
        }
    }

    @Override
    public void buK() {
    }
}