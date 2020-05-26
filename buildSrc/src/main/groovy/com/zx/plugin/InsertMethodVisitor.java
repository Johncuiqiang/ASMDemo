package com.zx.plugin;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @Author : cuiqiang
 * @DATE : 2020-05-24 11:21
 * @Description :
 */
public class InsertMethodVisitor extends MethodVisitor {

    private String methodName;
    String fieldInsnTmp = null;

    public InsertMethodVisitor(MethodVisitor mv, String methodName) {
        super(Opcodes.ASM6, mv);
        this.methodName = methodName;
    }

    //方法执行前插入
    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false);
        mv.visitVarInsn(Opcodes.LSTORE, 1);
    }

    //方法执行后插入
    @Override
    public void visitInsn(int opcode) {
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", false);
        mv.visitVarInsn(Opcodes.LSTORE, 3);
        mv.visitVarInsn(Opcodes.LLOAD, 3);
        mv.visitVarInsn(Opcodes.LLOAD, 1);
        mv.visitInsn(Opcodes.LSUB);
        mv.visitVarInsn(Opcodes.LSTORE, 5);
        //下面两句我们将方法的名称和描述作为常量入栈
        mv.visitLdcInsn("TAG");
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("------->" + methodName + "cost------->");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitVarInsn(Opcodes.LLOAD, 5);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        //调用我们的静态方法，类名的全路径，方法名，参数类型
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "e",
                "(Ljava/lang/String;Ljava/lang/String;)I", false);
        super.visitInsn(opcode);
    }


    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if (owner.contains("R\\$")) {
            fieldInsnTmp = name;
        }
        super.visitFieldInsn(opcode, owner, name, desc);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (name.equals("findViewById")){
           // WubaFindViewVisitorUtils.visit(mv, fieldInsnTmp);
           // super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/wuba/asmdemo/FeatureFindViewCompat",
            //        "findViewById",
            //        "(Ljava/lang/Object;ILjava/lang/String;Ljava/lang/String;)Landroid/view/View;", false);
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        } else {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }
}
