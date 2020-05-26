package com.zx.plugin

import com.android.annotations.NonNull
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * 58 findViewById visitor utils
 *
 * @author John Wayne Kuang on 2019/12/25
 */
class WubaFindViewVisitorUtils {

    /**
     * @see org.objectweb.asm.Opcodes
     *
     * // opcodes // visit method (- = idem)
     *
     *     int NOP = 0; // visitInsn
     *     int ACONST_NULL = 1; // -
     *     int ICONST_M1 = 2; // -
     *     int ICONST_0 = 3; // -
     *     int ICONST_1 = 4; // -
     *     int ICONST_2 = 5; // -
     *     int ICONST_3 = 6; // -
     *     int ICONST_4 = 7; // -
     *     int ICONST_5 = 8; // -
     *     int LCONST_0 = 9; // -
     *     int LCONST_1 = 10; // -
     *     int FCONST_0 = 11; // -
     *     int FCONST_1 = 12; // -
     *     int FCONST_2 = 13; // -
     *     int DCONST_0 = 14; // -
     *     int DCONST_1 = 15; // -
     *     int BIPUSH = 16; // visitIntInsn
     *     int SIPUSH = 17; // -
     *     int LDC = 18; // visitLdcInsn
     *     // int LDC_W = 19; // -
     *     // int LDC2_W = 20; // -
     *     int ILOAD = 21; // visitVarInsn
     *     int LLOAD = 22; // -
     *     int FLOAD = 23; // -
     *     int DLOAD = 24; // -
     *     int ALOAD = 25; // -
     * ...
     */
    static void visit(@NonNull MethodVisitor mv, String idName) {
        mv.visitLdcInsn(idName == null ? "" : idName)
        // 和 Opcodes 的 ICONST_ , BIPUSH 对应
        // 构建 String
        //创建引用类型的数组
        //mv.visitLdcInsn(idName)
        //复制栈顶一个字长的数据，将复制后的数据压栈。
       // mv.visitInsn(Opcodes.DUP)
       // transferVisit(mv, 0)
       // mv.visitLdcInsn("com/wuba/asmdemo")
        //将栈顶引用类型值保存到指定引用类型数组的指定项
       // mv.visitInsn(Opcodes.AASTORE)
    }

    private static void transferVisit(@NonNull MethodVisitor mv, int number) {
        //3(int)值入栈
        if (number < Opcodes.ICONST_3) {
            //0(int)值入栈
            mv.visitInsn(number + Opcodes.ICONST_0)
        } else {
            //valuebyte值带符号扩展成int值入栈
            mv.visitIntInsn(Opcodes.BIPUSH, number)
        }
    }
}
