package com.google.zxing;

import android.content.Context;


/**
 * public class c implements a {
 *     private String frW;
 *     private com.yunzhijia.scan.c.c gIe;
 *     private ScanSuccessUtil gIm;
 */
public class ELinkClassK implements ELinkInterfaceC {
    private String frW;
    private ELinkInterfaceD gIe;
    private ScanSuccessUtil gIm;

    public ELinkClassK(Context context, String str, String str2) {
        this.frW = str2;
        aE(context, str);
    }

    private void aE(final Context context, String str) {
        if (!ELinkClassI.mG(str)) {
            ELinkClassE.BD(str).a(new ELinkInterfaceD() {
                @Override // com.yunzhijia.scan.c.c
                public void afY() {
                }

                @Override // com.yunzhijia.scan.c.c
                public void k(int i, Object obj) {
                    if (obj != null) {
                        ELinkClassK cVar = ELinkClassK.this;
                        cVar.gIm = new ScanSuccessUtil(context,
                                ELinkClassI.mG(cVar.frW) ? "IS_FROM_SCAN_LOCAL_IMAGE" : ELinkClassK.this.frW, new ScanSuccessInterface() {
                            @Override
                            public void btC() {
                            }
                        });
                        ELinkClassK.this.gIm.c((TodoResult) obj);
                        ELinkClassK.this.gIe.k(3, obj);
                        return;
                    }
                    Context context2 = context;
//                    ELinkClassL.a(context2, context2.getString(R.string.toast_3));
//                    ELinkClassK.this.gIe.k(3, ELinkToast.np(R.string.toast_3));
                }

                @Override // com.yunzhijia.scan.c.c
                public void kc(String str2) {
                    ELinkClassK.this.gIe.kc(str2);
                }
            });
        }
    }


    @Override
    public void a(ELinkInterfaceD cVar) {
        this.gIe = cVar;
    }

    @Override
    public void buK() {

    }
}