package com.zx.plugin;

import com.android.ddmlib.Log;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @Author : cuiqiang
 * @DATE : 2020-05-24 11:21
 * @Description :
 */
public class ReplaceMethodVisitor extends MethodVisitor {

    private String methodName;

    public ReplaceMethodVisitor(MethodVisitor mv, String methodName) {
        super(Opcodes.ASM6, mv);
        this.methodName = methodName;
    }

    //方法执行前插入
    @Override
    public void visitCode() {
        super.visitCode();
    }

    //方法执行后插入
    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (name.equals("setText") && desc.equals("()V")){
            super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/wuba/asmdemo/ProxyText", "setText",
                    "()V", false);
        } else {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }
}
